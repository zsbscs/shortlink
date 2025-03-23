package org.zsbscs.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsbscs.shortlink.admin.common.convention.exception.ServiceException;
import org.zsbscs.shortlink.admin.dao.entity.GroupDO;
import org.zsbscs.shortlink.admin.dao.mapper.GroupMapper;
import org.zsbscs.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.zsbscs.shortlink.admin.service.GroupService;
import org.zsbscs.shortlink.admin.util.RandomGenerator;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateCode();
        } while (!availableGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .sortOrder(0)
                .build();
        try {
            baseMapper.insert(groupDO);
        }
       catch (Exception e) {
            throw new ServiceException("短链接分组新增失败");
       }
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> groupDOLambdaQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag,0)
                .eq(GroupDO::getUsername,"zs")
                .orderByAsc(GroupDO::getSortOrder,GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(groupDOLambdaQueryWrapper);
        return BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);

    }

    public boolean availableGid(String gid) {

        Wrapper<GroupDO> wrapper = Wrappers.<GroupDO>lambdaQuery().eq(GroupDO::getGid, gid);

        return !baseMapper.exists(wrapper);
    }
}
