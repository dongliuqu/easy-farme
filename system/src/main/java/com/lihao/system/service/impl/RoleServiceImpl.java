package com.lihao.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihao.system.entity.po.Role;
import com.lihao.system.mapper.RoleMapper;
import com.lihao.system.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:43
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
