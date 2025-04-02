package org.zsbscs.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.project.dao.entity.ShortLinkDO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkAddReqDTO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDO> {


    ShortLinkAddRespDTO addShortLink(ShortLinkAddReqDTO addReqDTO);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);


    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}
