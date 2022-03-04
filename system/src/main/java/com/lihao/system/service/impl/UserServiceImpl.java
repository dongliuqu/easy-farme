package com.lihao.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihao.system.entity.po.User;
import com.lihao.system.mapper.UserMapper;
import com.lihao.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:42
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getByUserName(String username) {
        return this.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }
}
