package com.lihao.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 15:31
 */
@Data
public class LoginDTO {
    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("用户")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
