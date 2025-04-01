package org.zsbscs.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.zsbscs.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
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

    @PutMapping("api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }
    @DeleteMapping("api/short-link/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }
    @PostMapping("api/short-link/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}
