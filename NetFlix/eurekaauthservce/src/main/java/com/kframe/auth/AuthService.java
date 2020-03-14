package com.kframe.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

	@Override
	public <T> RetResult<T> auth(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return IAuthService.super.auth(userInfo);
	}
}
