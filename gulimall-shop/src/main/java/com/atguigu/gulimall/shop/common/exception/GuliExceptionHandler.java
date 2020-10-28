package com.atguigu.gulimall.shop.common.exception;

import com.atguigu.gulimall.shop.common.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestControllerAdvice
@Slf4j
public class GuliExceptionHandler {
	/**
	 * 处理自定义异常
	 *
	 * @param exception 异常消息
	 * @return DataResult
	 */
	@ExceptionHandler(GuliException.class)
	public DataResult handleRRException(GuliException exception){
		log.error(exception.getMessage(), exception);
		return DataResult.error(exception.getCode(), exception.getMessage());
	}

	/**
	 * 其他异常
	 *
	 * @param exception 异常消息
	 * @return DataResult
	 */
	@ExceptionHandler(Exception.class)
	public DataResult handleException(Exception exception){
		log.error(exception.getMessage(), exception);
		return DataResult.error(ResponseCode.SYSTEM_ERROR.getCode(), exception.getMessage());
	}


}
