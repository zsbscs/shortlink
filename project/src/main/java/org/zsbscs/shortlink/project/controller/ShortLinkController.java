package org.zsbscs.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zsbscs.shortlink.project.common.convention.result.Result;
import org.zsbscs.shortlink.project.common.convention.result.Results;
import org.zsbscs.shortlink.project.dto.req.ShortLinkAddReqDTO;
import org.zsbscs.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.zsbscs.shortlink.project.service.ShortLinkService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/api/short-link/project/v1/link")
    public Result<ShortLinkAddRespDTO> addShortLink(@RequestBody ShortLinkAddReqDTO addReqDTO) {

    return Results.success(shortLinkService.addShortLink(addReqDTO));
    }

    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
    @GetMapping("api/short-link/project/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> groupShortLinkCount(@RequestParam("requestParam") List<String>requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }

}
