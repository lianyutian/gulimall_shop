package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.service.UserService;
import com.atguigu.gulimall.shop.common.DataResult;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 用户controller
 *
 * @author lm
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/login")
    public DataResult loing(@RequestBody LoginForm loginForm) {
        return DataResult.success(userService.login(loginForm));
    }

    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public DataResult queryList() {
        return DataResult.success();
    }


}
