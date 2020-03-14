package com.kframe.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurakaAuthServiceApplication {

	static final Logger LOGGER = LoggerFactory.getLogger(EurakaAuthServiceApplication.class);

    public static void main(String[] args) {
    	SpringApplication.run(EurakaAuthServiceApplication.class);
    }
}