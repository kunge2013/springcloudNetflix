package com.kframe.auth.service;

import java.io.Serializable;

import com.kframe.common.RetResult;
import com.kframe.common.UserInfo;
/**
 * 用户认证
 * @author fk
 *
 */
public interface IAuthSevice {
	
	/**
	 * 用户信息
	 * @param userinfo
	 * @return 返回token
	 */
	public RetResult<String> login(UserInfo userinfo);
	
	/**
	 * 校验token
	 * @param token
	 * @param id
	 * @return
	 */
	public RetResult checkTokenExists(String token, Serializable id);
	
	
}
