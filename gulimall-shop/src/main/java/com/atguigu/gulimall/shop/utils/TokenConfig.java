package com.atguigu.gulimall.shop.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * token配置类
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class TokenConfig {
    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 访问令牌过期时间
     */
    private Duration accessTokenExpireTime;

    /**
     * 刷新令牌过期时间
     */
    private Duration refreshTokenExpireTime;

    /**
     * 刷新令牌过期应用时间
     */
    private Duration refreshTokenExpireAppTime;
}
