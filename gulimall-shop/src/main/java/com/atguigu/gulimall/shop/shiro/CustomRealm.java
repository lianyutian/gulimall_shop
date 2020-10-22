package com.atguigu.gulimall.shop.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义Reaml
 *
 * @author lm
 * @since 2020/10/20 20:00
 */
public class CustomRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.getPrincipal();
        String principal = (String) authenticationToken.getPrincipal();
        if ("zhangsan".equals(principal)) {
            return new SimpleAuthenticationInfo(principal, "5102067e840233163f98c85d8ee605f3", ByteSource.Util.bytes("axco"), this.getName());
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123", "axco", 1024);
        System.out.println(md5Hash.toHex());
    }
}
