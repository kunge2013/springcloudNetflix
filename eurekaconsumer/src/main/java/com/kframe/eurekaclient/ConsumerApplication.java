package com.kframe.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kframe.loadbalanced.conf.BalanceRule1;
import com.netflix.loadbalancer.IRule;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerApplication {
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	/**
	 * 实例化RestTemplate
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	/**
	 * Rest服务端使用RestTemplate发起http请求,然后得到数据返回给前端----gotoUser是为了区分getUser怕小伙伴晕头
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/home")
	@ResponseBody
	public Object home() {
		Object data = null;
		/**
		 * 小伙伴发现没有，地址居然是http://service-provider 居然不是http://127.0.0.1:8082/
		 * 因为他向注册中心注册了服务，服务名称service-provider,我们访问service-provider即可
		 */
		data = restTemplate.getForObject("http://EUREKACLIENT/", String.class);
		return data;
	}
}