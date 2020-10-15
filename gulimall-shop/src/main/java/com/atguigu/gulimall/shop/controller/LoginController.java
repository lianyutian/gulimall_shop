package com.atguigu.gulimall.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gulimall.shop.model.User;
import com.atguigu.gulimall.shop.service.LoginService;
import com.atguigu.gulimall.shop.utils.DataResult;
import com.atguigu.gulimall.shop.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/userlogin")
    public DataResult loing(@RequestBody User user) {
        return DataResult.success(loginService.login(user));
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setUserName("laowagn");
        return user;
    }
}
