package com.kframe.auth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kframe.common.RetResult;
import com.kframe.common.UserInfo;

@RestController
public class LoginController {

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
	public RetResult checkLoginStatus(@RequestParam int userid, @RequestParam String token) {
		return authService.checkTokenExists(token, userid);
	}

	@ResponseBody
	@GetMapping("/")
	public String index() {
		return "auth-service: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
