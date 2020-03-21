package com.kframe.exceptions;

import com.kframe.annotations.Comment;

/**
 * 异常
 * @author fk
 */
@Comment("全局异常处理")
public class BizException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1809139411927439555L;

	public int code;

	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static BizException create(int code, String message) {
		BizException exception = new BizException(code, message);
		return exception;
	}

	public BizException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
