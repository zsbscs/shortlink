package org.zsbscs.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zsbscs.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

@Data

public class UserRespDTO {
        /**
         * ID
         */
        private Long id;

        /**
         * 用户名
         */
        private String username;


        /**
         * 真实姓名
         */
        private String realName;

        /**
         * 手机号
         */
        @JsonSerialize(using = PhoneDesensitizationSerializer.class)
        private String phone;

        /**
         * 邮箱
         */
        private String mail;

    }
