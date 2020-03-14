package com.kframe.eurekaclient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessUserNameFilter extends ZuulFilter {

	public static Logger LOGGER = LoggerFactory.getLogger(AccessUserNameFilter.class);

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
	    String agent = request.getHeader("User-Agent");
		String username = request.getParameter("username");// 获取请求的参数
		LOGGER.info("{} AccessUserNameFilter request to {}  headers [Accept {}, Host {}] ,  cookies {} , agent {}", request.getMethod(), request.getRequestURL().toString(),request.getHeader("Accept"), request.getHeader("Host"),  
					request.getCookies(), agent);
		
		if (null != username && username.equals("fk")) {// 如果请求的参数不为空，且值为chhliu时，则通过
			ctx.setSendZuulResponse(true);// 对该请求进行路由
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
			ctx.getResponse().addCookie(new Cookie("UNF", "XXXXXXXXXXXXXXXXXXXXX")); //唯一登录标志
			ctx.addZuulRequestHeader("User-Agent", agent);
			return null;
		} else {
			ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
			ctx.setResponseStatusCode(401);// 返回错误码
			ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
			ctx.set("isSuccess", false);
			return null;
		}
	}

	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}

	@Override
	public int filterOrder() {
		return 0;// 优先级为0，数字越大，优先级越低
	}

	@Override
	public String filterType() {
		return "pre";// 前置过滤器
	}

}
