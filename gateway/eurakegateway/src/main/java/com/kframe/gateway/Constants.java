package com.kframe.gateway;

public class Constants {
	/*
	 * token Key
	 */
	public static final String TOKEN_KEY_PERFIX = "AUTH_TOKEN_";

	/**
	 * 拦截器返回值:token错误
	 */
	public static final int ERROR_RESPONSE_TOKEN_CODE = 1000;
	public static final String ERROR_RESPONSE_AUTH_TOKEN_TIMEOUT = "token过期，请重新登录";
	public static final String ERROR_RESPONSE_AUTH_PARSE = "无效的token";
}
