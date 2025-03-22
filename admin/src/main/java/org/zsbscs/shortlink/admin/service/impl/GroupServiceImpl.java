package org.zsbscs.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsbscs.shortlink.admin.common.convention.exception.ServiceException;
import org.zsbscs.shortlink.admin.dao.entity.GroupDO;
import org.zsbscs.shortlink.admin.dao.mapper.GroupMapper;
import org.zsbscs.shortlink.admin.service.GroupService;
import org.zsbscs.shortlink.admin.util.RandomGenerator;

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
                .build();
        try {
            baseMapper.insert(groupDO);
        }
       catch (Exception e) {
            throw new ServiceException("短链接分组新增失败");
       }
    }

    public boolean availableGid(String gid) {

        Wrapper<GroupDO> wrapper = Wrappers.<GroupDO>lambdaQuery().eq(GroupDO::getGid, gid);

        return !baseMapper.exists(wrapper);
    }
}
