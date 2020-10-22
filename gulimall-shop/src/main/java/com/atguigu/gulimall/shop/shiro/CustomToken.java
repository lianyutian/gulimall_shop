package com.atguigu.gulimall.shop.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token
 *
 * @author lm
 * @since 2020/10/22 19:59
 */
public class CustomToken extends UsernamePasswordToken {
    private String token;

    public CustomToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
