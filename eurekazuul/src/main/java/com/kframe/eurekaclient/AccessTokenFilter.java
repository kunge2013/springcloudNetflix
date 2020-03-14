package com.kframe.eurekaclient;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;  
import com.netflix.zuul.context.RequestContext;  
  
public class AccessTokenFilter extends ZuulFilter {  
	public static Logger LOGGER = LoggerFactory.getLogger(AccessTokenFilter.class);

    @Override  
    public Object run() {  
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();  
        request.getCookies();
        LOGGER.info("{} AccessTokenFilter request to {}", request.getMethod(),  request.getRequestURL().toString());
        ctx.setSendZuulResponse(true);  
        ctx.setResponseStatusCode(200);  
        ctx.setResponseBody("{\"name\":\"chhliu\"}");// 输出最终结果  
        return null;  
    }  
  
    @Override  
    public boolean shouldFilter() {  
        return true;  
    }  
  
    @Override  
    public int filterOrder() {  
        return 0;  
    }  
  
    @Override  
    public String filterType() {  
        return "post";// 在请求被处理之后，会进入该过滤器  
    }  
}
