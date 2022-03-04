package com.lihao.system.service;

import com.lihao.system.entity.dto.LoginDTO;
import com.lihao.system.entity.vo.LoginVO;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 15:34
 */
public interface ILoginService {
    /**
     * 登录
     *
     * @param dto
     * @return
     */
    LoginVO login(LoginDTO dto);

    /**
     * 登出
     */
    void logout();

}
