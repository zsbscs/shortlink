package org.zsbscs.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.admin.dao.entity.GroupDO;
import org.zsbscs.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupName);

    List<ShortLinkGroupRespDTO> listGroup();
}
