package com.atguigu.gulimall.shop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT工具类
 */
public class JWTUtils {
    /**
     * 秘钥
     */
    private static final String SING = "lsadihgsdh";

    /**
     * 生成token header.payload.sing
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        // 设置过期时间
        calendar.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        return builder.withExpiresAt(calendar.getTime()) // 设置过期时间
                .sign(Algorithm.HMAC256(SING)); // 设置加密算法
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(token)).build().verify(token);
    }
}
