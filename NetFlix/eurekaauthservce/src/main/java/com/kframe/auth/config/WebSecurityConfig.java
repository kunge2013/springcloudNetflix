package com.kframe.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kframe.auth.codec.KframePasswordEncoder;
import com.kframe.auth.service.AuthService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	UserDetailsService customUserService() {
        return new AuthService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserService()).passwordEncoder(new KframePasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .anyRequest().authenticated()
	        .and().formLogin().loginPage("/login1")
	        //设置默认登录成功跳转页面
	        .defaultSuccessUrl("/main").failureUrl("/login1?error").permitAll()
	        .and()
	        .logout()
	        //默认注销行为为logout，可以通过下面的方式来修改
	        .logoutUrl("/custom-logout")
	        //设置注销成功后跳转页面，默认是跳转到登录页面
	        .logoutSuccessUrl("/login1")
	        .permitAll()
	        .and()
	        .exceptionHandling()
	        .accessDeniedPage("/myerror");//无权访问
	}
}
