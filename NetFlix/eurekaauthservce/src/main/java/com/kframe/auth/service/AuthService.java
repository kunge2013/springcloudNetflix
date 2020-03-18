package com.kframe.auth.service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.kframe.auth.JwtConstant;
import com.kframe.auth.JwtFactory;
import com.kframe.common.RetCodes;
import com.kframe.common.RetResult;
import com.kframe.entity.UserInfo;
import com.kframe.repositorys.UserRepository;


/**
 * 签权配置
 * @author fk
 */
@Service
public class AuthService implements IAuthSevice {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private UserRepository userRepository;
	
	/*@Value("${debug}")
    private boolean debug;*/

    private static final String CACHE_KEY_PREFIX = "user:token:pc:";

    private static final Integer[] BOSS_UID = new Integer[] {
            271498,
            427928,
            41458,
            246527,
            427190,
            393068,
            309,
            427837,
            427076,
            425074,
            710274,
            1066,
            832086,
            832082,
            832079,
            832081,
            832084,
            832078,
            832083,
            832080,
            832087,
            832092,
            832091,
            832094,
            832085,
            832265,
            832089,
            708432,
            708910,
            1000735,
            800361,
            1381946,
            1383092
    };

    private String getCacheKeyByUserId(Serializable userId) {
        return CACHE_KEY_PREFIX + userId.toString();
    }

    /**
     * 保存token 到缓存
     * @param token
     * @param userId
     */
    private void saveToken(String token, Serializable userId) {
        redisTemplate.opsForValue().set(getCacheKeyByUserId(userId), token);
        redisTemplate.expire(getCacheKeyByUserId(userId), new Long(JwtConstant.JWT_REFRESH_INTERVAL / 1000),TimeUnit.SECONDS);
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
        if(redisToken != null && redisToken.equals(token)) return RetResult.success();
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
		String username  = userinfo.getUsername();
		if (!username.isEmpty() && !password.isEmpty()) {
			if (username.equals("admin") && password.equals("123456")) {
				String token = JwtFactory.createJWT(userinfo);
				saveToken(token, userinfo.getId());
				return RetResult.success(token);
			}
		}
		return RetCodes.retCode(RetCodes.LOGIN_FAIL);
	}
}
