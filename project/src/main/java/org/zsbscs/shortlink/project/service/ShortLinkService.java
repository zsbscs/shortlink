package org.zsbscs.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.project.dao.entity.ShortLinkDO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkAddReqDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkAddRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {


    ShortLinkAddRespDTO addShortLink(ShortLinkAddReqDTO addReqDTO);
}
