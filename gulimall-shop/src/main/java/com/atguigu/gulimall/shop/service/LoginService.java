package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.model.User;
import org.springframework.stereotype.Service;

/**
 * 登录相关service
 *
 * @author lm
 * @since 2020-10-12
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param loginForm 表单数据
     * @return 用户信息
     */
    String login(LoginForm loginForm);
}
