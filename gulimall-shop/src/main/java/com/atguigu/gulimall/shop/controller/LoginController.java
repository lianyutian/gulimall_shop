package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.service.LoginService;
import com.atguigu.gulimall.shop.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录controller
 *
 * @author lm
 * @since 2020-10-16
 */
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public DataResult loing(@RequestBody LoginForm loginForm) {
        loginService.login(loginForm);
        // 生成token
//        String token = JWTUtils.getToken(String.valueOf(userId), new HashMap<>());
        return DataResult.success(1);
    }
}
