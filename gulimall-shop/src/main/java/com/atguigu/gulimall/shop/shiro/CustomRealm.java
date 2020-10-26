package com.atguigu.gulimall.shop.shiro;

import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * 自定义Reaml
 *
 * @author lm
 * @since 2020/10/20 20:00
 */
public class CustomRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principalCollection 身份信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String accessToken = (String) principalCollection.getPrimaryPrincipal();
        Claims claimsFromToken = JWTUtil.getClaimsFromToken(accessToken);
        // 用户权限信息
        String permissions = (String) claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY) != null) {
            simpleAuthorizationInfo.addStringPermissions(Collections.singleton(permissions));
        }
        // 用户角色信息
        String roles = (String) claimsFromToken.get(Constant.ROLES_INFOS_KEY);
        if (!StringUtils.isEmpty(roles)) {
            simpleAuthorizationInfo.addRoles(Collections.singleton(roles));
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomToken token = (CustomToken) authenticationToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), this.getName());
    }
}
