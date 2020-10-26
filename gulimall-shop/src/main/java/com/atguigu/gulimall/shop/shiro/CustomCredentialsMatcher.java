package com.atguigu.gulimall.shop.shiro;

import com.atguigu.gulimall.shop.common.exception.GuliException;
import com.atguigu.gulimall.shop.common.exception.ResponseCode;
import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.RedisUtil;
import com.atguigu.gulimall.shop.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 自定义token匹配器
 *
 * @author lm
 * @since 2020/10/22 21:34
 */
@Slf4j
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomToken customToken = (CustomToken) token;
        String accessToken = (String) customToken.getCredentials();
        //String userId = JWTUtil.getUserId(accessToken);
        //log.info("userId: {}", userId);
        //RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
        // 判断用户是否退出登录
        if (redisUtil.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken)) {
            throw new GuliException(ResponseCode.TOKEN_ERROR);
        }
        // 校验token（是否过期/token中数据为空)
        if (!JWTUtil.validateToken(accessToken)) {
            throw new GuliException(ResponseCode.TOKEN_PAST_DUE);
        }
        return true;
    }
}
