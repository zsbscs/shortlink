package org.zsbscs.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.zsbscs.shortlink.admin.common.database.BaseDO;

import java.util.Date;

@Data
@TableName("t_link")
public class ShortLinkDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * domain
     */
    private String domain;

    /**
     * short_uri
     */
    private String shortUri;

    /**
     * full_short_url
     */
    private String fullShortUrl;

    /**
     * orgin_url
     */
    private String originUrl;

    /**
     * click_num
     */
    private Integer clickNum;

    /**
     * gid
     */
    private String gid;

    /**
     * enable_status
     */
    private Integer enableStatus;

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
    @TableField("`describe`")
    private String describe;

    private String favicon;


}
