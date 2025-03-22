package org.zsbscs.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.admin.dao.entity.GroupDO;

public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupName);
}
