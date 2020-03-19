package com.kframe.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
/**
 * 登录失败
 * @author fk
 *
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

	   public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
	        httpServletResponse.getWriter().write("登陆失败");
	    }

}
