package com.kframe.auth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kframe.annotations.Comment;
import com.kframe.auth.service.IAuthSevice;
import com.kframe.common.RetResult;
import com.kframe.entity.UserInfo;
import com.kframe.entity.VerifyCode;
import com.kframe.exceptions.BizException;

@RestController
public class UserController {

	@Autowired
	private IAuthSevice authService;

	/**
	 * 登录认证
	 * 
	 * @param username 用户名
	 * @param password 密码
	 */
	@ResponseBody
	@GetMapping("/login")
	public RetResult<String> login(@RequestParam String username, @RequestParam String password) {
		return authService.login(new UserInfo(username, password));
	}
	

	@ResponseBody
	@GetMapping("/checkLoginStatus")
	public RetResult checkLoginStatus(@RequestHeader(name = "userid") int userid,
			@RequestHeader(name = "token") String token) {
		return authService.checkTokenExists(token, userid);
	}

	@Comment("注册")
	@ResponseBody
	@GetMapping("/register")
	public RetResult<UserInfo> register(@RequestParam int nation, @RequestParam String mobile, @RequestParam String verifycode) {
		return authService.register(nation, mobile, verifycode);
	}

	@ResponseBody
	@GetMapping("/")
	public String index() {
		return "auth-service: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	@Comment("校验码生成")
	@ResponseBody
	@GetMapping("/api/verifyCode")
	public RetResult<VerifyCode> generVerifyCode() {
		return authService.generVerifyCode();	
	}
	
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		throw new BizException(1000, "test exception !");
	}
}
