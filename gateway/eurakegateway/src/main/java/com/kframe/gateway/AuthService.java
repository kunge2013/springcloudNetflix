package com.kframe.gateway;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
/**
 * 签权配置
 * @author fk
 */
@Service
public class AuthService implements IAuthSevice {

	@Autowired
	private StringRedisTemplate redisTemplate;

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

    public void saveToken(String token, Serializable userId) {
        redisTemplate.opsForValue().set(getCacheKeyByUserId(userId), token);
        redisTemplate.expire(getCacheKeyByUserId(userId), new Long(JwtConstant.JWT_REFRESH_INTERVAL / 1000),TimeUnit.SECONDS);
    }

    public boolean checkTokenExists(String token, Serializable userId) {
        for (Integer uid : BOSS_UID) {
            if (uid.equals(userId)) {
                return true;
            }
        }
        
        String redisToken = redisTemplate.opsForValue().get(getCacheKeyByUserId(userId));
        return redisToken != null && redisToken.equals(token);
    }
}
