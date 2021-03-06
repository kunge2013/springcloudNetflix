package com.kframe.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	 	@Override
	    public void commence(HttpServletRequest request,
	                         HttpServletResponse response,
	                         AuthenticationException authException)
	            throws IOException, ServletException {
	 		logger.info("JwtAuthenticationEntryPoint {}", authException.getMessage());
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
	    }
}
