package com.atguigu.gulimall.shop.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回数据封装
 *
 * @author lm
 * @since 2020-10-12
 */
@Setter
@Getter
public class DataResult<T> {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应提示语
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;
}
