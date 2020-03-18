package com.kframe.auth.config;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kframe.auth.JwtFactory;
import com.kframe.entity.UserInfo;
import com.kframe.repositorys.UserRepository;

import io.jsonwebtoken.Claims;

/**
 * token过滤器来验证token有效性
 * 
 * @author fk
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Value("${jwt.header}")
	private String tokenheader;

	@Resource
	private UserRepository userRepository;
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String auth = request.getHeader(this.tokenheader) == null ? "" : request.getHeader(this.tokenheader);
		String username = "";
		UserInfo userinfo = null;
		LOGGER.info("headers  {}  , auth = {} ", request.getHeaderNames(), auth);
		if (auth.isEmpty()) {
			 username = request.getParameter("username");
			 String password = request.getParameter("password");
			 Optional<UserInfo> optional = userRepository.queryOneByUsername(username);
			 if (optional.isPresent()) {
				 userinfo = optional.get();
				 if(!password.equalsIgnoreCase(optional.get().getPassword())) {
					 return;
				 }
				 
			 } else {
				 return;
			 }
		}
		Optional<Claims> optional = JwtFactory.parseJWT(auth);
		if (optional.isPresent()) {
			Claims claims = optional.get();
			userinfo = (UserInfo) claims.get(JwtFactory.CLAIM_USERINFO);
			username = userinfo.getUsername();
		}

		LOGGER.info("Checking authentication for user {}.", username);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (JwtFactory.validateToken(auth, userinfo)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userinfo,
						null, userinfo.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				LOGGER.info("Authenticated user {}, setting security context", userinfo);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
}
