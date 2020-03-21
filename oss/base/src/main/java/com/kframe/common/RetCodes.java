package com.kframe.common;

import com.kframe.annotations.Comment;

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
	
	@Comment("用户不存在")
	public static final int USER_NOT_EXIST = 20001;
	
	@Comment("校验码生成失败")
	public static final int VERIFY_CODE_CREATE_ERROR = 30001;
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
