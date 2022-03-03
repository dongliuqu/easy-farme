package com.lihao.common.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OAUserLoginLogDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 登录时间
     */
    private Date createTime;
}
