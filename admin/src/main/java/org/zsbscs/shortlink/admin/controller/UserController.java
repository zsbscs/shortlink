package org.zsbscs.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.dto.resp.UserActualRespDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;
import org.zsbscs.shortlink.admin.service.UserServie;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServie userService;

    /**
     * 根据用户名查找用户
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }
    /**
     * 根据用户名查找用户真实信息
     */
    @GetMapping("/api/shortlink/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return  Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }
}

