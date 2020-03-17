package com.kframe.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

	@Bean
	public GlobalFilter createTokenFilter() {
		return new TokenFilter();
	}
}
