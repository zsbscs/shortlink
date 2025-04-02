package org.zsbscs.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.zsbscs.shortlink.admin.common.convention.exception.ClientException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private static final List<String>IGNORE_URL = Lists.newArrayList(
            "/api/short-link/v1/admin/user/login",
            "/api/short-link/v1/admin/user/available-username"

    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URL.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            if(!(Objects.equals(requestURI,"api/short-link/v1/admin/user")&&Objects.equals(method,"POST")))
            {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if(!StrUtil.isAllNotBlank(username,token)){
                    throw new ClientException("登录信息错误！");
                }
                Object userInfoJsonStr = null;
                try {
                    userInfoJsonStr = stringRedisTemplate.opsForValue().get(token);
                }
                catch (Exception e) {
                    throw new ClientException("用户token信息错误！");
                }
                if(userInfoJsonStr != null){
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
                }
            }

        }


        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}
