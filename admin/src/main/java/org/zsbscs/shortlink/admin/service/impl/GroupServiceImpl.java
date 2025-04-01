package org.zsbscs.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsbscs.shortlink.admin.common.biz.user.UserContext;
import org.zsbscs.shortlink.admin.common.convention.exception.ServiceException;
import org.zsbscs.shortlink.admin.dao.entity.GroupDO;
import org.zsbscs.shortlink.admin.dao.mapper.GroupMapper;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
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
                .username(UserContext.getUsername())
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
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByAsc(GroupDO::getSortOrder,GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(groupDOLambdaQueryWrapper);
        return BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);

    }

    public boolean availableGid(String gid) {

        LambdaQueryWrapper<GroupDO> wrapper = Wrappers.lambdaQuery(GroupDO.class).eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername,UserContext.getUsername());

        return !baseMapper.exists(wrapper);
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> queryWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag,0);
        GroupDO groupDO = GroupDO.builder().name(requestParam.getName()).build();
        try{
            baseMapper.update(groupDO, queryWrapper);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }



    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> deleteWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);

        GroupDO groupDO = GroupDO.builder().delFlag(1).build();
        try {
            baseMapper.update(groupDO,deleteWrapper);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }



    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each -> {
          LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                  .set(GroupDO::getSortOrder,each.getSortOrder())
                  .eq(GroupDO::getGid, each.getGid())
                  .eq(GroupDO::getUsername, UserContext.getUsername())
                  .eq(GroupDO::getDelFlag, 0);
          update(updateWrapper);
        });
    }
}
