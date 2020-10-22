package com.atguigu.gulimall.shop.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 *
 * @author lm
 * @since 2020/10/21 23:43
 */
@Slf4j
class PasswordEncodeUtil {
    private final static String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    private final static String MD5 = "MD5";
    private final static String SHA = "SHA";

    private Object salt;
    private String algorithm;

    /**
     * 构造方法
     *
     * @param salt 盐值
     */
    PasswordEncodeUtil(String salt) {
        this(salt, MD5);
    }

    /**
     * 构造方法
     *
     * @param salt      盐值
     * @param algorithm 加密算法
     */
    private PasswordEncodeUtil(String salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * 密码加密
     *
     * @param passWord 密码
     * @return 加密后密码
     */
    String encode(String passWord) {
        String result = null;
        // 获取加密算法
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串转为16进制
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(passWord).getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException exception) {
            log.error(exception.getMessage(), exception);
        }
        return result;
    }

    /**
     * 密码匹配验证
     *
     * @param encPass 密文
     * @param rawPass 明文
     * @return 是否匹配
     */
    boolean matches(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    /**
     * 合并密码和盐值
     *
     * @param passWord 密码
     * @return 密码+盐值
     */
    private String mergePasswordAndSalt(String passWord) {
        if (passWord == null) {
            passWord = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return passWord;
        } else {
            return passWord + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param bytes 字节数组
     * @return 16进制字串
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aByte : bytes) {
            resultSb.append(byteToHexString(aByte));
        }
        return resultSb.toString();
    }

    /**
     * 将字节转换为16进制
     *
     * @param b 字节
     * @return 16进制码
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }
}
