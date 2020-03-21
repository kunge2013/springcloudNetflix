package com.kframe.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.kframe.*"})
@EnableDiscoveryClient//服务法系发现
@EntityScan(basePackages = "com.kframe.entity")// 扫描实体
@EnableJpaRepositories(basePackages = "com.kframe.repositorys")// 扫描 jpa接口
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class}) //取消spring 权限框架扫描
@EnableTransactionManagement // 开启事物
public class OssApplication {

	static final Logger LOGGER = LoggerFactory.getLogger(OssApplication.class);
    public static void main(String[] args) {
    	SpringApplication.run(OssApplication.class);
    }
}