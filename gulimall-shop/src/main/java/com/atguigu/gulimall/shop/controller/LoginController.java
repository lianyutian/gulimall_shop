package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.service.LoginService;
import com.atguigu.gulimall.shop.utils.DataResult;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 登录controller
 *
 * @author lm
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/login")
    public DataResult loing(@RequestBody LoginForm loginForm) {
        loginService.login(loginForm);
        // 生成token
        String token = JWTUtil.getToken(loginForm.getUserName(), new HashMap<>());
        return DataResult.success(1);
    }

    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public DataResult queryList() {
        return DataResult.success();
    }
}
