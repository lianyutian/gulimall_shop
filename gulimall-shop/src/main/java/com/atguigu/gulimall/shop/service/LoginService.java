package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.dao.user.LoginDao;
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

    public User login(User user) {
        User user1 = loginDao.queryUser(user);
        user1.setToken(JWTUtils.getToken(user.getUserName(), new HashMap<>()));
        return user1;
    }
}
