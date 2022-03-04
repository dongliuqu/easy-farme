package com.lihao.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihao.system.entity.po.RoleMenu;
import com.lihao.system.mapper.RoleMenuMapper;
import com.lihao.system.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:46
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
}
