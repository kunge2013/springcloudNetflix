package com.kframe.gateway;

import java.io.Serializable;

public interface IAuthSevice {

	/**
	 * 保存 token
	 * @param token
	 * @param id
	 */
	public void saveToken(String token, Serializable id);
	
	/**
	 * 校验token
	 * @param token
	 * @param id
	 * @return
	 */
	public boolean checkTokenExists(String token, Serializable id);
}
