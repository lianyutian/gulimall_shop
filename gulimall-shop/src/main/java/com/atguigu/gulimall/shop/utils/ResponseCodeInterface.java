package com.atguigu.gulimall.shop.utils;

/**
 * 返回状态接口
 *
 * @author lm
 * @since 2020-10-12
 */
public interface ResponseCodeInterface {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 获取返回消息
     *
     * @return 返回消息
     */
    String getMsg();
}
