package com.kframe.common;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kframe.entity.UserInfo;
/**
 * 用户登录授权对象
 * @author fk
 */
public class UserLoginBean extends UserInfo implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4482273596715491223L;

	public UserLoginBean(String username, String password) {
		super(username, password);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


}
