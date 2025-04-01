package org.zsbscs.shortlink.project.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkAddReqDTO {
    /**
     * domain
     */
    private String domain;


    /**
     * orgin_url
     */
    private String originUrl;
    /**
     * gid
     */
    private String gid;

    /**
     * created_type
     */
    private Integer createdType;

    /**
     * valid_date_type
     */
    private Integer validDateType;

    /**
     * valid_date
     */
    private Date validDate;

    /**
     * describe
     */
    private String describe;
}
