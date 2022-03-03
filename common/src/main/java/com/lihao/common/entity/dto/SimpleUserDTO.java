package com.lihao.common.entity.dto;

import lombok.Data;

/**
 * 简要用户信息  
 * @author liangjinquan
 * */
@Data
public class SimpleUserDTO{

    private Integer id;
    private String name;
    private String mobile;
    private String username;
    private String mail;
    private String description;
    private String nickName;
    private String position;
    private String headerImg;
    private Integer userType;
}
