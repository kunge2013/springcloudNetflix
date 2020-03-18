package com.kframe.exceptions;

import com.kframe.annotations.Comment;

/**
 * 异常
 * @author fk
 */
@Comment("全局异常处理")
public class BusException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1809139411927439555L;

	public int code;

	private String message;

	public static BusException create(int code, String message) {
		BusException exception = new BusException(code, message);
		return exception;
	}

	public BusException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BusException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BusException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BusException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
