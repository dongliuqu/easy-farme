package com.lihao.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihao.system.entity.po.UserRole;
import com.lihao.system.mapper.UserRoleMapper;
import com.lihao.system.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:48
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
