package com.lihao.common.entity.dto;

import lombok.Data;

/**
 * 简要用户权限信息
 * @author liangjinquan
 */
@Data
public class SimpleRoleDTO {
    private Integer id;
    private String code;
    private String name;
    private String description;
}
