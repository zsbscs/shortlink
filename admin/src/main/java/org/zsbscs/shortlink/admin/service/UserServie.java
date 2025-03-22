package org.zsbscs.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.admin.dao.entity.UserDO;
import org.zsbscs.shortlink.admin.dto.req.UserLoginReqDTO;
import org.zsbscs.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.zsbscs.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;


public interface UserServie extends IService<UserDO> {

    UserRespDTO getUserByUsername(String username);

    Boolean hasUsername(String username);

    void registerUser(UserRegisterReqDTO requestParam);

    void updateUser(UserUpdateReqDTO requestParam);

    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    Boolean check_login(String username,String token);

    void logout(String username, String token);
}
