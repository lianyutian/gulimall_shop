package com.atguigu.gulimall.shop.common;

import com.atguigu.gulimall.shop.utils.BaseResponseCode;
import com.atguigu.gulimall.shop.utils.ResponseCodeInterface;
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

    public DataResult(int code, T data) {
        this.code = code;
        this.msg = null;
        this.data = data;
    }

    public DataResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 无参构造
     */
    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    /**
     * 构造函数
     *
     * @param data 数据
     */
    public DataResult(T data) {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 构造函数
     *
     * @param responseCodeInterface 状态
     */
    public DataResult(ResponseCodeInterface responseCodeInterface) {
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = null;
    }

    /**
     * 构造函数
     *
     * @param responseCodeInterface 状态
     */
    public DataResult(ResponseCodeInterface responseCodeInterface, T data) {
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
        this.data = data;
    }

    /**
     * 操作成功 data为null
     *
     * @return DataResult
     */
    public static DataResult success() {
        return new DataResult();
    }

    /**
     * 操作成功 data为不为null
     *
     * @return DataResult
     */
    public static <T> DataResult success(T data) {
        return new DataResult(data);
    }

    /**
     * 操作失败
     *
     * @param code 状态码
     * @param msg 错误信息
     * @return DataResult
     */
    public static <T> DataResult error(int code, String msg) {
        return new DataResult(code, msg);
    }

    /**
     * 自定义返回操作
     *
     * @param code 状态码
     * @param msg 返回消息
     * @param data 返回数据
     * @param <T> 返回类型
     * @return DataResult
     */
    public static <T> DataResult getResutl(int code, String msg, T data) {
        return new DataResult(code, msg, data);
    }

    /**
     * 自定义返回 data为null
     *
     * @param code 状态码
     * @param msg 返回消息
     */
    public static DataResult getResult(int code, String msg){
        return new DataResult(code, msg);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data为空
     *
     * @param responseCode 返回状态码
     * @return DataResult
     */
    public static DataResult getResult(BaseResponseCode responseCode){
        return new DataResult(responseCode);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data 可控

     * @param responseCode 返回状态码
     * @param data 返回数据
     * @return DataResult
     */
    public static <T> DataResult getResult(BaseResponseCode responseCode, T data){
        return new DataResult(responseCode, data);
    }
}
