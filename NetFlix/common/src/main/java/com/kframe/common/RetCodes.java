package com.kframe.common;
/**
 * 錯誤編碼
 * @author fk
 */
public class RetCodes {
	/**
	 * 登錄失敗
	 */
	public static final int LOGIN_FAIL = 10000;
	
	/**
	 * 注册失败
	 */
	public static final int USER_REGISTER_FAIL = 20000;
	
	/**
	 * 錯誤嗎
	 * @param retcode
	 * @return
	 */
	public static  RetResult retCode(int retcode) {
		RetResult result = new RetResult();
		result.setRetcode(retcode);
		return result;
	}
}
