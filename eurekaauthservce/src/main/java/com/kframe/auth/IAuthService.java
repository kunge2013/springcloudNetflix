package com.kframe.auth;
/**
 * 用户服务接口
 * @author fk
 *
 */
public interface IAuthService {

	/**
	 * 鉴权
	 * @param userInfo
	 * @return
	 */
	default public <T> RetResult<T> auth(UserInfo userInfo) {
		throw new UnsupportedOperationException("unsupport operation ....");
	}
	
	
}
