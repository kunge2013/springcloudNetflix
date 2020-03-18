package com.kframe.auth.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * 密码登录
 * @author fk
 *
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		LOGGER.info("url = {}", url);
		String username = "";
		String password = "";
		/**
		 * 登录校验过滤
		 */
		if ("/login".equalsIgnoreCase(url)) {
			UsernamePasswordAuthenticationToken authRequest = null;
			username =request.getParameter("username");
			password =request.getParameter("password");
			authRequest = new UsernamePasswordAuthenticationToken(username, password);
			setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
		}
		return super.attemptAuthentication(request, response);
	}

}
