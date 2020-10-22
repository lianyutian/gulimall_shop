package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录表单
 *
 * @author lm
 * @since 2020-10-15
 */
@Setter
@Getter
public class LoginForm {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * token
     */
    private String accessToken;
}
