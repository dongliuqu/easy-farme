package com.lihao.common.entity.dto;

import lombok.Data;

/**
 * 组织信息
 * @author liangjinquan
 * */
@Data
public class SimpleGroupDTO{
    private Integer id;
    private String parentId;
    private String name;
    private String description;
    private String type;
    private String areaCode;
    private String departmentManager;
    private Integer managerId;
    private Integer orderNum;
    // 机构挂靠 @liangjq 2020-01-12
    private String jggk;
    // 是否默认组织
    private boolean hasDefaultGroup = false;

}
