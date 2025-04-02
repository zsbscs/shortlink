package org.zsbscs.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.zsbscs.shortlink.project.common.convention.exception.ServiceException;
import org.zsbscs.shortlink.project.dao.entity.ShortLinkDO;
import org.zsbscs.shortlink.project.dao.mapper.ShortLinkMapper;
import org.zsbscs.shortlink.project.dto.req.ShortLinkAddReqDTO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.zsbscs.shortlink.project.service.ShortLinkService;
import org.zsbscs.shortlink.project.tookit.HashUtil;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    private final RBloomFilter<String> shortUriCachePenetrationBloomFilter;

    @Override
    public ShortLinkAddRespDTO addShortLink(ShortLinkAddReqDTO addReqDTO) {
        String shortLinkSuffix = generateSuffix(addReqDTO);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(addReqDTO, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(addReqDTO.getDomain()+"/"+shortLinkSuffix);
        try{
            baseMapper.insert(shortLinkDO);
        }
        catch (DuplicateKeyException e){
            throw new ServiceException("短链接重复");
        }
        shortUriCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkAddRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(addReqDTO.getOriginUrl())
                .gid(addReqDTO.getGid())
                .build();
    }


    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        IPage<ShortLinkDO> resultPage = baseMapper.pageLink(requestParam);
        return resultPage.convert(each -> {
            ShortLinkPageRespDTO result = BeanUtil.toBean(each, ShortLinkPageRespDTO.class);
            result.setDomain("http://" + result.getDomain());
            return result;
        });
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO())
                .select("gid as gid, count(*) as shortLinkCount")
                .in("gid", requestParam)
                .eq("enable_status", 0)
                .eq("del_flag", 0)
                .groupBy("gid");
        List<Map<String, Object>> shortLinkDOList = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(shortLinkDOList, ShortLinkGroupCountQueryRespDTO.class);
    }

    private String generateSuffix(ShortLinkAddReqDTO requestParam) {
        String originUrl = requestParam.getOriginUrl();
        int customGenerateCount = 0;
        String shortUri;
        while(true){
            if(customGenerateCount >10){
                throw new ServiceException("短链接频繁生成，请稍后再试！");
            }
            originUrl += UUID.randomUUID().toString();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCachePenetrationBloomFilter.contains(shortUri)){
                break;
            }
            else {
                customGenerateCount++;
            }
        }
        return shortUri;
    }
}
