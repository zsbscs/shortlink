package org.zsbscs.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.zsbscs.shortlink.admin.common.convention.result.Result;
import org.zsbscs.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.zsbscs.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

public interface ShortLinkActualRemoteService {
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("gid",requestParam.getGid());
        requestMap.put("current",requestParam.getCurrent());
        requestMap.put("size",requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/admin/v1/page", requestMap);

        return JSON.parseObject(resultPageStr, new TypeReference<>() {});
    }

}