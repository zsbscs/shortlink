package org.zsbscs.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.zsbscs.shortlink.project.dao.entity.ShortLinkDO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkPageReqDTO;

public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);
}
