package com.lihao.system.controller;

import com.lihao.common.entity.vo.Result;
import com.lihao.system.entity.dto.LoginDTO;
import com.lihao.system.entity.vo.LoginVO;
import com.lihao.system.service.ILoginService;
import com.lihao.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 15:29
 */
@RequestMapping("sys")
@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    final IUserService userService;
    final ILoginService loginService;


    @PostMapping("login")
    public Result<LoginVO> login(@RequestBody LoginDTO dto){
        LoginVO loginVO= loginService.login(dto);
        return Result.success(loginVO);
    }


    @PostMapping("logout")
    public Result<?> logout(){
        loginService.logout();
        return Result.success();
    }



}
