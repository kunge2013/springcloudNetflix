package com.kframe.eurekaclient;

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
public class EurekaclientApplication2 {

	@Autowired
	private EurekaClient discoveryClient;

	@RequestMapping("/")
    public String home() {
        return "........Hello world2 2222222";
    }
	 
    @RequestMapping("/ediscoveryClient")
    public String ediscoveryClient() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("eurekaclient", false);
        return instance.getHomePageUrl() + "........Hello world2 2222222";
    }
    
    @RequestMapping("/discoveryClient")
    public String discoveryClient() {
    	Applications apps = discoveryClient.getApplications();
//    	List<InstanceInfo>  list = discoveryClient.getInstancesById("eurekaclient");
        return apps.toString() + "........list";
    }

    public static void main(String[] args) {
    	SpringApplication.run(EurekaclientApplication2.class);
    }
}