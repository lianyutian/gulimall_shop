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
     * 异常信息
     */
    UNKNOWN_ERR(-1, "未知错误"),
    TOKEN_NOT_NULL(4010001,"token不能为空"),
    SYSTEM_ERROR(5000001,"系统异常请稍后再试"),
    TOKEN_ERROR(4010001,"用户未登录，请重新登录"),
    USERNAME_OR_PASSWORD_ERR(-100, "用户名或密码错误"),
    ACCOUNT_LOCK_TIP(4010012,"该账号被锁定,请联系系统管理员"),
    TOKEN_PAST_DUE(4010002,"token失效,请刷新token"),
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
