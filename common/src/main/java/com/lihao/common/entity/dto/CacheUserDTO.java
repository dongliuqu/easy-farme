package com.lihao.common.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 缓存用户信息封装类
 * @author liangjinquan
 */
@Data
public class CacheUserDTO {

    /**
     * key
     * 其实这key不应该暴露给其他服务，应该限制在网关层使用
     * fix ：需要优化，后面实现了刷新token后去掉;目前用于切换部门
     */
    private String cacheId;

    /**
     * 用户信息
     */
    private SimpleUserDTO user;

    /**
     * 用户组织信息
     */
    private List<SimpleGroupDTO> groups;

    /**
     * 用户权限信息
     */
    private List<SimpleRoleDTO> roles;
}
