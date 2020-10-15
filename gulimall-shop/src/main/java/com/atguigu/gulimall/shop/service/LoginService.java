package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.common.exception.GuliException;
import com.atguigu.gulimall.shop.common.exception.ResponseCode;
import com.atguigu.gulimall.shop.dao.user.LoginDao;
import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.model.User;
import com.atguigu.gulimall.shop.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * 登录相关service
 *
 * @author lm
 * @since 2020-10-12
 */
@Service
public class LoginService {
    /**
     * 登录相关dao
     */
    @Autowired
    private LoginDao loginDao;

    public long login(LoginForm loginForm) {
        User user = loginDao.queryUser(loginForm.getUserName());
        if (user == null) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        // 密码错误
        if (!user.getPassWord().equals(loginForm.getPassWord())) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        return user.getId();
    }
}
