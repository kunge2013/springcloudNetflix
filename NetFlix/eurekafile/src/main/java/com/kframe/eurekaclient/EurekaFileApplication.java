package com.kframe.eurekaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaFileApplication {

	static final Logger LOGGER = LoggerFactory.getLogger(EurekaFileApplication.class);

    public static void main(String[] args) {
    	SpringApplication.run(EurekaFileApplication.class);
    }
}