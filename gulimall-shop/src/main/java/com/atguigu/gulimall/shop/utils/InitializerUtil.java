package com.atguigu.gulimall.shop.utils;

import org.springframework.stereotype.Component;

/**
 * 配置初始化代理类
 *
 * @author lm
 * @since 2020-10-15
 */
@Component
public class InitializerUtil {
    /**
     * 初始化配置
     *
     * @param tokenConfig token配置
     */
    public InitializerUtil(TokenConfig tokenConfig) {
        JWTUtil.setTokenConfig(tokenConfig);
    }
}
