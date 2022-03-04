package com.lihao.system.service.impl;

import com.lihao.common.exception.BusinessException;
import com.lihao.common.utils.Constant;
import com.lihao.redis.utils.RedisUtils;
import com.lihao.system.config.shiro.JwtUtils;
import com.lihao.system.config.shiro.OAuth2Token;
import com.lihao.system.entity.dto.LoginDTO;
import com.lihao.system.entity.po.User;
import com.lihao.system.entity.vo.LoginVO;
import com.lihao.system.service.ILoginService;
import com.lihao.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @author lihao
 * @description:
 * @date 2022/3/4 15:35
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    final IUserService userService;
    final JwtUtils jwtUtils;
    final RedisUtils redisUtils;

    @Override
    public LoginVO login(LoginDTO dto) {
        //todo 判断验证码

        String username = dto.getUsername();
        String password = dto.getPassword();
        User user = userService.getByUserName(username);
        if (Objects.isNull(user)) {
            throw new BusinessException("找不到该用户");
        }
        String dbPassword = user.getPassword();
        //比较密码
        Boolean flag = this.comparePassword(password, dbPassword);
        if (!flag) {
            throw new BusinessException("密码错误");
        }
        //生成token
        String cacheKey = UUID.randomUUID().toString();
        String token = jwtUtils.generateToken(cacheKey);
        putCacheKey(cacheKey, user.getId());
        SecurityUtils.getSubject().login(new OAuth2Token(token));
        return new LoginVO(token);
    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 将token中的subject存入到缓存中
     *
     * @param cacheKey
     */
    public String putCacheKey(String cacheKey, Long userid) {
        redisUtils.set(Constant.CACHE_TOKEN_PREFIX + cacheKey, userid, jwtUtils.getExpire());
        return cacheKey;
    }

    /**
     * 删除redis用户token缓存
     *
     * @param cacheKey
     */
    public void clearCacheKey(String cacheKey) {
        redisUtils.del(Constant.CACHE_TOKEN_PREFIX + cacheKey);
    }


    /**
     * @param password   用户传递过来的密码
     * @param dbPassword 数据库中保存的密码
     * @return
     */
    private Boolean comparePassword(String password, String dbPassword) {
        //todo 如果数据库密码二次加密请修改实现
        return Objects.equals(password, dbPassword);
    }
}
