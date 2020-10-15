package com.atguigu.gulimall.shop.common.exception;

import lombok.Getter;

/**
 * 返回状态码
 *
 * @author lm
 * @since 2020-10-15
 */
@Getter
public enum ResponseCode {
    /**
     * 未知错误
     */
    UNKNOWN_ERR(-1, "未知错误"),

    /**
     *  用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERR(-100, "用户名或密码错误"),
    ;

    /**
     * 状态码
     */
    private int code;

    /**
     * 异常消息
     */
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
