package com.atguigu.gulimall.shop.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author lm
 * @since 2020-10-15
 */
@Setter
@Getter
public class GuliException extends RuntimeException {
	/**
	 * 异常信息
	 */
    private String msg;

	/**
	 * 状态码
	 */
	private int code;
    
    public GuliException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public GuliException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public GuliException(int code, String msg) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public GuliException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public GuliException(ResponseCode responseCode) {
    	super(responseCode.getMsg());
    	this.code = responseCode.getCode();
    	this.msg = responseCode.getMsg();
	}
}
