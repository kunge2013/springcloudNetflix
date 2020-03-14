package com.kframe.eurekaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ZuulConfig {
	/**
	 * 用户名称验证拦截
	 * @return
	 */
	@Bean  
    public AccessUserNameFilter accessUserNameFilter() {  
        return new AccessUserNameFilter();  
    }  
      
	/**
	 * 密码验证拦截
	 * @return
	 */
    @Bean  
    public AccessPasswordFilter accessPasswordFilter(){  
        return new AccessPasswordFilter();  
    }

    /**
     * 浏览器 跨域 配置
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/path-1/**")
                        .allowedOrigins("https://allowed-origin.com")
                        .allowedMethods("GET", "POST");
            }
        };
    }
}
