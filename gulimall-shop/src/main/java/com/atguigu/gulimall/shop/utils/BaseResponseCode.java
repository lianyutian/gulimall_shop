package com.atguigu.gulimall.shop.utils;

/**
 * 返回状态枚举
 *
 * @author lm
 * @since 2020-10-12
 */
public enum BaseResponseCode implements ResponseCodeInterface {
    /**
     * 返回操作状态
     */
    SUCCESS(200, "操作成功")
    ;

    /**
     * 状态码
     */
    private final int code;

    /**
     * 返回消息
     */
    private final String msg;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg 返回消息
     */
    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
