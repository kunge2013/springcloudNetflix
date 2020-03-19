package com.kframe.auth.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kframe.auth.JwtService;
import com.kframe.common.RetResult;
import com.kframe.entity.UserInfo;
/**
 * 登陆成功 返回凭证
 * @author fk
 *
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Resource
	private JwtService jwtService;
	
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	
		 	UserInfo userinfo = (UserInfo) authentication.getPrincipal();
		 	String token = jwtService.createJWT(userinfo);
		 	response.setCharacterEncoding("utf-8");
		 	response.setContentType("application/json; charset=utf-8");
		 	response.addHeader("token", token);
		 	response.addCookie(new Cookie("token", token));
	        response.getWriter().write(JSON.toJSONString(RetResult.success(token)));
	    }
}
