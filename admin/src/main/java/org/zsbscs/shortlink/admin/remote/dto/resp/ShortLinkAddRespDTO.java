package org.zsbscs.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkAddRespDTO {

    private String gid;
    /**
     * orgin_url
     */
    private String originUrl;

    private String fullShortUrl;
}
