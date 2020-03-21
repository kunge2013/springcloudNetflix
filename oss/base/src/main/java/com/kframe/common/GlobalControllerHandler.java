package com.kframe.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kframe.exceptions.BizException;

/**
 * 全局异常处理
 * 
 * @author fk
 *
 */
@ControllerAdvice
@RestControllerAdvice
@ResponseBody
public class GlobalControllerHandler {

	/**
	 * 全局異常統一
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	RetResult<?>  errorHandler(Exception ex) {
		if (ex instanceof BizException) {
			BizException biz = (BizException) ex;
			return RetResult.error(biz);
		}
		return RetResult.error(ex);
	}

	@ResponseBody
	@ExceptionHandler(value = BizException.class)
	RetResult<?> errorHandler(BizException ex) {
		return RetResult.error(ex);
	}

}
