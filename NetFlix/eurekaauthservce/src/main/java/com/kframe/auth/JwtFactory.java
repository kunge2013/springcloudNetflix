package com.kframe.auth;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;

import com.kframe.entity.UserInfo;
import com.kframe.exceptions.BizException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwtFactory JwtFactory生成token(身份令牌):代码部分
 * 
 * @author fk
 */
public class JwtFactory {
	public JwtFactory() {
	}

	public static final String CLAIM_USERINFO = "USERINFO";

	public static final String secret = "Mpk4ZjZim2Q0Nj0xZDMpM2NhZlU0ZTgzMrYyN2IpZjY";
	
	public static final String issure = "pc.api.yobtc.com";
	
	public static final String audience = "098f6bcd4621d373cade4e832627b4f6";
	
	private static SecretKey generalKey() {
		byte[] encodedKey = Base64.getEncoder().encode(secret.getBytes());
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public static Optional<Claims> parseJWT(String token) {
		try {
			SecretKey key = generalKey();
			Claims claims = (Claims) Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			return Optional.of(claims);
		} catch (Exception var3) {
			return Optional.of(null);
		}
	}

	/**
	 * 解析成用户信息
	 * 
	 * @param token
	 * @return
	 */
	public static UserInfo parseUserInfo(String token) {
		Optional<Claims> optional = parseJWT(token);
		if (optional.isPresent()) return (UserInfo) optional.get().get(CLAIM_USERINFO);
		throw new BizException(-1, "un validate token !");
	}

	
	public static String createJWT(UserInfo userinfo) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey signingKey = generalKey();
		JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT").claim(CLAIM_USERINFO, userinfo)
				.setIssuer(issure)
				.setAudience(audience)
				.setIssuedAt(now)
				.signWith(signatureAlgorithm, signingKey);
		if (JwtConstant.JWT_REFRESH_TTL >= 0L) {
			long expMillis = nowMillis + JwtConstant.JWT_REFRESH_TTL;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp).setNotBefore(now);
		}

		return builder.compact();
	}

	/**
	 * 校验有效性
	 * @param token
	 * @param userDetails
	 * @return
	 */
	   public static boolean validateToken(String token, UserDetails userDetails) {
	        UserInfo userDetail = (UserInfo) userDetails;
	        UserInfo userInfo = parseUserInfo(token);
	        final long userId = userInfo.getId();
	        final String username = userInfo.getUsername();
	        return (userId == userDetail.getId()
	                && username.equals(userDetail.getUsername())
	                && !isTokenExpired(token)
	        );
	    }
	   
	   /**
	    * 获取过期时间
	    * @param token
	    * @return
	    */
	    public static Date getExpirationDateFromToken(String token) {
	        Date expiration;
	        try {
	            final Claims claims = parseJWT(token).get();
	            expiration = claims.getExpiration();
	        } catch (Exception e) {
	            expiration = null;
	        }
	        return expiration;
	    }

	    private static boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }
}
