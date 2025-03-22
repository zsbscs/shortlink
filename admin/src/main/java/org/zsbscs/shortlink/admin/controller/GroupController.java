package org.zsbscs.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.zsbscs.shortlink.admin.service.GroupService;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("api/short-link/v1/group")
    public Result<Void> addGroup(@RequestBody ShortLinkGroupSaveReqDTO groupParam) {
        groupService.saveGroup(groupParam.getName());
        return Results.success();
    }
}
