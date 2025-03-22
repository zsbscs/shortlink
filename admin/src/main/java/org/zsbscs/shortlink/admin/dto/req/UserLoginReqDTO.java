package org.zsbscs.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class UserLoginReqDTO {

   /**
    *用户登录请求参数
    */
    private String username;

    private String password;
}
