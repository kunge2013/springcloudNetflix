package com.kframe.gateway;

import java.io.Serializable;

public interface IAuthSevice {

	public void saveToken(String token, Serializable id);
	
	public boolean checkTokenExists(String token, Serializable id);
}
