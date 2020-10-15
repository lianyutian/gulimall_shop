package com.atguigu.gulimall.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.model.User;
import com.atguigu.gulimall.shop.service.LoginService;
import com.atguigu.gulimall.shop.utils.DataResult;
import com.atguigu.gulimall.shop.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public DataResult loing(@RequestBody LoginForm loginForm) {
        long userId = loginService.login(loginForm);
        // 生成token
        String token = JWTUtils.getToken(String.valueOf(userId), new HashMap<>());
        return DataResult.success(token);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setUserName("laowagn");
        return user;
    }
}
