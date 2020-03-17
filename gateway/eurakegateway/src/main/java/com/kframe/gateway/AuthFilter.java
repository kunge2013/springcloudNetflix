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
        String auth =exchange.getRequest().getHeaders().getFirst("Authorization");
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
        // 只有综合路由才添加这个全局过滤器（routesId：route_all）
        // 如果请求路径中不存在 routeAll 字符串
//        if(request.getURI().toString().indexOf("routeAll") == -1) {
//        	LOGGER.info("filter -> return");
//            // 直接跳出
//            return chain.filter(exchange);
//        }
//
//        // 从请求中获取 token 参数
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        // 如果为空，那么将返回 401
//        if (token == null || token.isEmpty()) {
//            // 响应消息内容对象
//            Map<String, Object> message = new HashMap();
//            // 响应状态 
//            message.put("code", -1);
//            // 响应内容
//            message.put("msg", "缺少凭证");
//            // 转换响应消息内容对象为字节
//            byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            // 设置响应对象状态码 401
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            // 返回响应对象
//            return response.writeWith(Mono.just(buffer));
//        }
//        // 获取请求地址
//        String beforePath = request.getPath().pathWithinApplication().value();
//        // 获取响应状态码
//        HttpStatus beforeStatusCode = response.getStatusCode();
//        System.out.println("响应码：" + beforeStatusCode + "，请求路径：" + beforePath);
//        // 请求前
//        System.out.println("filter -> before");
        // 如果不为空，就通过
        return chain.filter(exchange);
    }

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1000;
	}

}
