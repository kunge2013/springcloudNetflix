package com.kframe.eurekaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListSubsetFilter;
/**
 * ribbon 基本信息配置 策略配置  ping 配置
 * @author fk
 */
@Configuration
public class CustomConfiguration {


	@Bean
	public IRule ribbonRule() {
		return new BestAvailableRule();
	}

	@Bean
	public IPing ribbonPing() {
		return new PingUrl(false,"/health");
	}

	@Bean
	public ServerList<Server> ribbonServerList(IClientConfig config) {
		return new TestConfiguration.BazServiceList(config);
	}

	@Bean
	public ServerListSubsetFilter<?> serverListFilter() {
		return new ServerListSubsetFilter();
	}
}
