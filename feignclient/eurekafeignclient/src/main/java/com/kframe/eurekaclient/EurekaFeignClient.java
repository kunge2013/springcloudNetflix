package com.kframe.eurekaclient;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.kframe.eurekaclient"})
@RestController
public class EurekaFeignClient {

	static final Logger LOGGER = LoggerFactory.getLogger(EurekaFeignClient.class);
	

	 @Autowired
     private HttpServletRequest request;
	 
	 @Autowired
	 private ConsumerClient client;
	 
	@RequestMapping("/")
    public String home() {
		LOGGER.info("Cookies {}, Header [Accept {} , Cookie {}, User-Agent {}] ",
					request.getCookies(),
					request.getHeader("Accept"),
					request.getHeader("Cookie"),
					request.getHeader("User-Agent"));
        return "........Hello world 1111,  Cookie =" + request.getHeader("Cookie");
    }
	 
  
	@RequestMapping("/comsumerHome")
	public String comsumerHome() {
		return client.home();
	}

    public static void main(String[] args) {
    	SpringApplication.run(EurekaFeignClient.class);
    }
}