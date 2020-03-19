package com.kframe.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 序列化配置
 * @author fk
 */
@Configuration
public class HttpMessageConvertersConfig {
	
	@Bean
	HttpMessageConverters fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig connfig = new FastJsonConfig();
		connfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(connfig);
		return new HttpMessageConverters(converter);
	}
	
}
