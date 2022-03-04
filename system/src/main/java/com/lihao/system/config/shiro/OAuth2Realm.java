/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lihao.system.config.shiro;

import com.google.common.collect.Sets;
import com.lihao.common.exception.BusinessException;
import com.lihao.common.utils.Constant;
import com.lihao.redis.utils.RedisUtils;
import com.lihao.system.entity.po.User;
import com.lihao.system.service.IUserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * 认证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
@RequiredArgsConstructor
public class OAuth2Realm extends AuthorizingRealm {

    final IUserService userService;
    final JwtUtils jwtUtils;
    final RedisUtils redisUtils;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CurrentUserInfo user = (CurrentUserInfo) principals.getPrimaryPrincipal();
        Long userId = user.getId();

        //用户权限列表
        Set<String> permsSet = Sets.newHashSet();
        //todo 查询用户权限装载
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        Claims claim = jwtUtils.getClaimByToken(accessToken);
        String cacheKey = claim.getSubject();
        Object o = redisUtils.get(Constant.CACHE_TOKEN_PREFIX + cacheKey);
        if (Objects.isNull(o)) {
            throw new BusinessException("token错误或过期");
        }
        Long userid = (Long) o;
        User user = userService.getById(userid);
        CurrentUserInfo currentUserInfo = new CurrentUserInfo(user);
        //todo 账号锁定

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUserInfo, accessToken, getName());
        return info;
    }
}
