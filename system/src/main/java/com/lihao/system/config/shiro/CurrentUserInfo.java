package com.lihao.system.config.shiro;

import com.lihao.system.entity.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lihao
 * @description: 当前登录用户信息
 * @date 2022/3/4 16:53
 */
@Data
@NoArgsConstructor
public class CurrentUserInfo implements Serializable {

    private Long id;
    private String name;
    private String mobile;
    private String username;
    private String mail;
    private String password;
    private String description;
    private String nickName;
    private String position;
    private String headerImg;

    /**
     * 联系电话
     */
    private String contactPhone;


    public CurrentUserInfo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.username = user.getUsername();
//        this.mail = user.getMail();
        this.password = user.getPassword();
        this.description = user.getDescription();
        this.nickName = user.getNickName();
//        this.position = user.getPosition();
        this.headerImg = user.getHeaderImg();
    }
}
