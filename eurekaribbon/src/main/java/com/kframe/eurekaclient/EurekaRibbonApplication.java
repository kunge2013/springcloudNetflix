package com.kframe.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages= {"com.netflix.client.config.IClientConfig"} )
@EnableDiscoveryClient
@RestController
public class EurekaRibbonApplication {
	
	@Autowired
    private RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaRibbonApplication.class, args);
	}
	
	@GetMapping(value = "/")
    @LoadBalanced
    public String getService() {
        return restTemplate.getForObject("http://EUREKACLIENT",String.class);
    }

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}