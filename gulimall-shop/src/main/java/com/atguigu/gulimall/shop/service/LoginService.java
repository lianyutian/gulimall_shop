package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.dao.user.LoginDao;
import com.atguigu.gulimall.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return loginDao.queryUser(user);
    }
}
