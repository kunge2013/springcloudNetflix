package com.kframe.eurekaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kframe.loadbalanced.conf.BalanceRule1;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class LoadBalanced {

	@Bean
	public IRule chooseRule() {
		return new BalanceRule1();
//		return new RoundRobinRule();
	}
}
