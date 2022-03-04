package com.lihao.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihao.system.entity.po.User;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:41
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名 查找用户
     *
     * @param username 用户名
     * @return
     */
    User getByUserName(String username);
}
