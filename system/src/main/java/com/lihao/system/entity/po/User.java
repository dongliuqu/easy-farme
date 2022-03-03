package com.lihao.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lihao.sql.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User extends BasePo {
    private String name;
    private String mobile;
    private String username;
    private String mail;
    private String password;
    private String description;
    private String nickName;
    private String position;
    private String headerImg;

    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;

    /**
     * 联系电话
     */
    private String contactPhone;






}
