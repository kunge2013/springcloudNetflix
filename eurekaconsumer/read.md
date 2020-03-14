### ribbon 基本配置
1.eureka 负载均衡策略配置
## 步骤
1.实现 Irule 接口 返回 Irule Bean 
2.在restTemplate 调用时候 配置 @LoadBalanced 即可实现负载均衡
3.eureka 添加 @EnableDiscoveryClient作为客户端 需要依赖的 maven 配置如下

		<!-- eureka client -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

	<!-- spring mvc -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>