package com.yscredit.ys.indchain.common.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OperateLog {

    /**
     * id
     */
    private String id;

    /**
     * 方法全名
     */
    private String methodName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 来源ip
     */
    private String originIp;

    /**
     * Http方法
     */
    private String httpMethod;

    /**
     * 操作时间
     */
    private String operateDatetime;

    /**
     * 请求参数
     */
    private String requestArgs;

    /**
     * 响应参数
     */
    private String responseArgs;

    /**
     * 用户名
     */
    private String username;
}