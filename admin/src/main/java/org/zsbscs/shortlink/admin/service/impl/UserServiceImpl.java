package org.zsbscs.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zsbscs.shortlink.admin.common.convention.exception.ClientException;
import org.zsbscs.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.zsbscs.shortlink.admin.dao.entity.UserDO;
import org.zsbscs.shortlink.admin.dao.mapper.UserMapper;
import org.zsbscs.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.zsbscs.shortlink.admin.dto.resp.UserRespDTO;
import org.zsbscs.shortlink.admin.service.UserServie;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserServie {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;



    @Override
    public UserRespDTO getUserByUsername(String username) {
         LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);


        if(userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void registerUser(UserRegisterReqDTO requestParam) {
        if(hasUsername(requestParam.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        if(inserted != 1) {
            throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());

    }
}
