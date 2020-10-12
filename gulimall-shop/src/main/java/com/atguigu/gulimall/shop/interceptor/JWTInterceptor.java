package com.atguigu.gulimall.shop.interceptor;

import com.atguigu.gulimall.shop.utils.JWTUtils;
import com.atguigu.gulimall.shop.utils.Result;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException exception) {
            Result.error(-1, "无效签名");
        } catch (TokenExpiredException exception) {
            Result.error(-2, "token过期");
        }
        return false;
    }
}
