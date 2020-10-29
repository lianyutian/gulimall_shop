package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.LoginForm;

/**
 * 用户相关service
 *
 * @author lm
 * @since 2020-10-12
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param loginForm 表单数据
     * @return 用户信息
     */
    String login(LoginForm loginForm);
}
