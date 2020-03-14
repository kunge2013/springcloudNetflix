package com.kframe.eurekaclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ConfigurationBasedServerList;

@Configuration
@RibbonClient(name = "custom", configuration = CustomConfiguration.class)
public class TestConfiguration {

	public static class BazServiceList extends ConfigurationBasedServerList {

		public BazServiceList(IClientConfig config) {
			super.initWithNiwsConfig(config);
		}

	}

}
