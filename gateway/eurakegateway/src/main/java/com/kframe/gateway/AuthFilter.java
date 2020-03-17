package com.kframe.gateway;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;
/**
 * 过滤器配置
 * @author fk
 *
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

	/**
	 * 日志处理
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
	/**
	 * 鉴权配置
	 */
	@Resource
	private IAuthSevice authSevice;

	private Serializable fetchSerializableId(ServerWebExchange exchange) {
        String auth = exchange.getRequest().getHeaders().getFirst("Authorization");
        Serializable userId = null;
        if ((auth != null) && (auth.length() > 7)) {
        	Optional<Claims> optional = JwtFactory.parseJWT(auth);
            if (optional.isPresent()) {
            	Claims claims = optional.get();
                 userId = claims.get("userid") == null ? "" : claims.get("userid").toString();
                if (!authSevice.checkTokenExists(auth, (Serializable) userId)) {
                    return null;
                }
               exchange.getAttributes().put("userid",userId);
            }
        }
        return userId;
	}
	
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 响应对象
        ServerHttpResponse response = exchange.getResponse();
        Serializable userid = fetchSerializableId(exchange);
        LOGGER.info("userid is {}", userid);
        return chain.filter(exchange);
    }

	public static void main(String[] args) {
		System.out.println(JwtFactory.createJWT("test", 1));
	}
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1000;
	}

}
