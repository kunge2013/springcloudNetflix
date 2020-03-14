package com.kframe.eurekaclient;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaclientApplication {

	static final Logger LOGGER = LoggerFactory.getLogger(EurekaclientApplication.class);
	@Autowired
	private EurekaClient discoveryClient;

	 @Autowired
     private HttpServletRequest request;
	 
	@RequestMapping("/")
    public String home() {
		LOGGER.info("Cookies {}, Header [Accept {} , Cookie {}, User-Agent {}] ",
					request.getCookies(),
					request.getHeader("Accept"),
					request.getHeader("Cookie"),
					request.getHeader("User-Agent"));
        return "........Hello world 1111,  Cookie =" + request.getHeader("Cookie");
    }
	 
    @RequestMapping("/ediscoveryClient")
    public String ediscoveryClient() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("eurekaclient", false);
        return instance.getHomePageUrl() + "........Hello world11111";
    }
    
    @RequestMapping("/discoveryClient")
    public String discoveryClient() {
    	Applications apps = discoveryClient.getApplications();
        return apps.toString() + "111111........list";
    }

    public static void main(String[] args) {
    	SpringApplication.run(EurekaclientApplication.class);
    }
}