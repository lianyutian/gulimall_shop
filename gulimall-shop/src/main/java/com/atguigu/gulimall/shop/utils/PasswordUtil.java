package com.atguigu.gulimall.shop.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 密码工具类
 *
 * @author lm
 * @since 2020/10/21 23:35
 */
public class PasswordUtil {
    /**
     * 匹配密码
     *
     * @param salt    盐
     * @param rawPass 明文
     * @param encPass 密文
     * @return 是否匹配
     */
    public static boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncodeUtil(salt).matches(encPass, rawPass);
    }

    /**
     * 明文密码加密
     *
     * @param rawPass 明文
     * @param salt 盐值
     * @return 密文
     */
    public static String encode(String rawPass, String salt) {
        return new PasswordEncodeUtil(salt).encode(rawPass);
    }

    /**
     * 获取加密盐
     *
     * @return 加密盐
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}
