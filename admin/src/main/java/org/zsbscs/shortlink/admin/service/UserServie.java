package org.zsbscs.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zsbscs.shortlink.admin.dao.entity.UserDO;
import org.zsbscs.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;


public interface UserServie extends IService<UserDO> {

    UserRespDTO getUserByUsername(String username);

    Boolean hasUsername(String username);

    void registerUser(UserRegisterReqDTO requestParam);
}
