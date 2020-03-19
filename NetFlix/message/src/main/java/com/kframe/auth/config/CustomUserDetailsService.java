package com.kframe.auth.config;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kframe.common.BaseService;
import com.kframe.common.RetCodes;
import com.kframe.entity.UserInfo;
import com.kframe.exceptions.BizException;
import com.kframe.repositorys.UserRepository;

@Component(value = "CustomUserDetailsService")
public class CustomUserDetailsService extends BaseService implements UserDetailsService {
	UserRepository userRepository;

	/**
	 * 查询当前用户是否存在
	 */
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<UserInfo> list = userRepository.queryByUsername(username);
		if (list.isEmpty())
			throw new BizException(RetCodes.USER_NOT_EXIST, "user not exist !");
		UserInfo user = list.get(0);
		LOGGER.info("authorities {} " + user.getAuthorities());
		return user;
	}

}
