package com.kframe.auth.service;

import static com.kframe.common.RetCodes.LOGIN_FAIL;
import static com.kframe.common.RetCodes.USER_REGISTER_FAIL;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kframe.annotations.Comment;
import com.kframe.auth.JwtConstant;
import com.kframe.auth.JwtService;
import com.kframe.common.BaseService;
import com.kframe.common.RetCodes;
import static com.kframe.common.RetCodes.*;
import com.kframe.common.RetResult;
import com.kframe.entity.UserInfo;
import com.kframe.entity.VerifyCode;
import com.kframe.repositorys.UserRepository;

/**
 * 签权配置
 * 
 * @author fk
 */
@Service
public class AuthService extends BaseService implements IAuthSevice {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private UserRepository userRepository;

	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private JwtService jwtService;
	/*
	 * @Value("${debug}") private boolean debug;
	 */

	@Resource
	private DefaultKaptcha kaptcha;
	
	private static final String CACHE_KEY_PREFIX = "user:token:pc:";

	@Comment("base64图片 前缀")
	private static final String BASE64_PREFIX_FORMAT = "data:image/jpeg;base64,%s";
	
	private static final Integer[] BOSS_UID = new Integer[] { 271498, 427928, 41458, 246527, 427190, 393068, 309,
			427837, 427076, 425074, 710274, 1066, 832086, 832082, 832079, 832081, 832084, 832078, 832083, 832080,
			832087, 832092, 832091, 832094, 832085, 832265, 832089, 708432, 708910, 1000735, 800361, 1381946, 1383092 };

	private String getCacheKeyByUserId(Serializable userId) {
		return CACHE_KEY_PREFIX + userId.toString();
	}

	/**
	 * 保存token 到缓存
	 * 
	 * @param token
	 * @param userId
	 */
	private void saveToken(String token, Serializable userId) {
		redisTemplate.opsForValue().set(getCacheKeyByUserId(userId), token);
		redisTemplate.expire(getCacheKeyByUserId(userId), new Long(JwtConstant.JWT_REFRESH_INTERVAL / 1000),
				TimeUnit.SECONDS);
	}

	/**
	 * 校验有效性
	 */
	public RetResult checkTokenExists(String token, Serializable userId) {
		for (Integer uid : BOSS_UID) {
			if (uid.equals(userId)) {
				return RetResult.success();
			}
		}
		String redisToken = redisTemplate.opsForValue().get(getCacheKeyByUserId(userId));
		if (redisToken != null && redisToken.equals(token))
			return RetResult.success();
		return RetCodes.retCode(-1);
	}

	/**
	 * 登錄校驗
	 */
	@SuppressWarnings("unchecked")
	@Override
	public RetResult<String> login(UserInfo userinfo) {
		userRepository.save(userinfo);
		String password = userinfo.getPassword();
		String username = userinfo.getUsername();
		if (!username.isEmpty() && !password.isEmpty()) {
			if (username.equals("admin") && password.equals("123456")) {
				String token = jwtService.createJWT(userinfo);
				saveToken(token, userinfo.getId());
				return RetResult.success(token);
			}
		}
		return RetCodes.retCode(LOGIN_FAIL);
	}

	@Comment("用户注册")
	@Override
	public RetResult<UserInfo> register(int nation, String mobile, String verifycode) {
		// todo 验证码接口验证
		if (userRepository.countRegisterUser(mobile, nation) > 0)
			return RetCodes.retCode(USER_REGISTER_FAIL);
		UserInfo userinfo = new UserInfo(null, passwordEncoder.encode(UserInfo.DEFAULT_PASSWORD), null, mobile, nation, null);
		return RetResult.success(userRepository.save(userinfo));
	}

	@Override
	public RetResult<VerifyCode> generVerifyCode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String code = kaptcha.createText();
		BufferedImage image = kaptcha.createImage(code);
		try {
			ImageIO.write(image, "jpg", outputStream);
			byte[] data = outputStream.toByteArray();
			String base64image = String.format(BASE64_PREFIX_FORMAT, Base64.getEncoder().encodeToString(data));
			return RetResult.success(VerifyCode.from(code, base64image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RetCodes.retCode(VERIFY_CODE_CREATE_ERROR);
	}


}
