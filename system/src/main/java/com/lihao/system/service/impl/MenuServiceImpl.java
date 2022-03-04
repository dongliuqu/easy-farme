package com.lihao.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihao.system.entity.po.Menu;
import com.lihao.system.mapper.MenuMapper;
import com.lihao.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 14:44
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
