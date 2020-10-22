package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.common.exception.GuliException;
import com.atguigu.gulimall.shop.common.exception.ResponseCode;
import com.atguigu.gulimall.shop.dao.user.LoginDao;
import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.model.User;
import com.atguigu.gulimall.shop.service.LoginService;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author lm
 * @since 2020/10/21 23:21
 */
public class LoginServiceImpl implements LoginService {
    /**
     * 登录相关dao
     */
    @Autowired
    private LoginDao loginDao;

    @Override
    public String login(LoginForm loginForm) {
        User user = loginDao.queryUser(loginForm.getUserName());
        if (user == null) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        // 密码错误
        if (PasswordUtil.matches(user.getSalt(), loginForm.getPassWord(), user.getPassWord())) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        // 生成token
        String token = JWTUtil.getToken(user.getId(), new HashMap<>());
        return token;
    }
}
