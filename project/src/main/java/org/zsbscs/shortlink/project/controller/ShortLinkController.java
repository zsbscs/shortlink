package org.zsbscs.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zsbscs.shortlink.project.common.convention.result.Result;
import org.zsbscs.shortlink.project.common.convention.result.Results;
import org.zsbscs.shortlink.project.dto.req.ShortLinkAddReqDTO;
import org.zsbscs.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import org.zsbscs.shortlink.project.service.ShortLinkService;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/api/short-link/project/v1/link")
    public Result<ShortLinkAddRespDTO> addShortLink(@RequestBody ShortLinkAddReqDTO addReqDTO) {

    return Results.success(shortLinkService.addShortLink(addReqDTO));
    }
}
