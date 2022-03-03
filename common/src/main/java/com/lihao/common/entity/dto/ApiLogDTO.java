package com.lihao.common.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApiLogDTO {

    /**
     * 服务名称
     */
    private String application;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 调用方式
     */
    private String method;

    /**
     * 参数
     */
    private String param;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;
}
