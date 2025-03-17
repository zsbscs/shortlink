package org.zsbscs.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;
import org.zsbscs.shortlink.admin.service.UserServie;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServie userService;
    /**
     *
     * 根据用户名查找用户
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if(result == null) {
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        else {
            return Results.success(result);
        }

    }
}
