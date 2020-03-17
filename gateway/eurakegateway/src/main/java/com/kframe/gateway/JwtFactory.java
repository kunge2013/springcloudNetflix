package com.kframe.gateway;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


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
	
	 private static SecretKey generalKey() {
	        String stringKey = "Mpk4ZjZim2Q0Nj0xZDMpM2NhZlU0ZTgzMrYyN2IpZjY";
	        byte[] encodedKey = Base64.getEncoder().encode(stringKey.getBytes());
	        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	        return key;
	    }

	    public static Claims parseJWT(String jsonWebToken) {
	        try {
	            SecretKey key = generalKey();
	            Claims claims = (Claims) Jwts.parser().setSigningKey(key).parseClaimsJws(jsonWebToken).getBody();
	            return claims;
	        } catch (Exception var3) {
	            return null;
	        }
	    }

	    public static String createJWT(String name, String userId) {
	        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	        long nowMillis = System.currentTimeMillis();
	        Date now = new Date(nowMillis);
	        SecretKey signingKey = generalKey();
	        JwtBuilder builder = Jwts.builder()
	        			.setHeaderParam("typ", "JWT")
	        			.claim("unique_name", name)
	        			.claim("userid", userId)
	        			.setIssuer("pc.api.yobtc.com")
	        			.setAudience("098f6bcd4621d373cade4e832627b4f6")
	        			.setIssuedAt(now)
	        			.signWith(signatureAlgorithm, signingKey);
	        if (JwtConstant.JWT_REFRESH_TTL >= 0L) {
	            long expMillis = nowMillis + JwtConstant.JWT_REFRESH_TTL;
	            Date exp = new Date(expMillis);
	            builder.setExpiration(exp).setNotBefore(now);
	        }

	        return builder.compact();
	    }
}
