package org.zsbscs.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.common.convention.result.Results;
import org.zsbscs.shortlink.admin.dto.req.UserLoginReqDTO;
import org.zsbscs.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.zsbscs.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserActualRespDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;
import org.zsbscs.shortlink.admin.service.UserServie;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServie userService;

    /**
     * 根据用户名查找用户
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }
    /**
     * 根据用户名查找用户真实信息
     */
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return  Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    @GetMapping("/api/short-link/v1/user/available-username")
    public Result<Boolean> availableUsername(@RequestParam("username") String username) {
        return Results.success(!userService.hasUsername(username));
    }

    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        userService.registerUser(requestParam);
        return Results.success();
    }
    @PutMapping("/api/short-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam){
        userService.updateUser(requestParam);
        return  Results.success();
    }
    @PostMapping("/api/short-link/v1/user/login")
    public Result<UserLoginRespDTO> Login(@RequestBody UserLoginReqDTO requestParam){
        UserLoginRespDTO userLoginRespDTO = userService.login(requestParam);
            return Results.success(userLoginRespDTO);
    }

    @GetMapping("api/short-link/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username")String username,@RequestParam("token")String token){
        return Results.success(userService.check_login(username,token));
    }

    @DeleteMapping("/api/short-link/v1/user/logout")
    public Result<Void> logout(@RequestParam("username")String username,@RequestParam("token")String token){
        userService.logout(username,token);
        return Results.success();
    }

}

