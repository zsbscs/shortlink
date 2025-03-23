package org.zsbscs.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.zsbscs.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.zsbscs.shortlink.admin.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("api/short-link/v1/group")
    public Result<Void> addGroup(@RequestBody ShortLinkGroupSaveReqDTO groupParam) {
        groupService.saveGroup(groupParam.getName());
        return Results.success();
    }

    @GetMapping("api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> getListGroup() {
        List<ShortLinkGroupRespDTO> respGroupList = groupService.listGroup();
        return Results.success(respGroupList);
    }
}
