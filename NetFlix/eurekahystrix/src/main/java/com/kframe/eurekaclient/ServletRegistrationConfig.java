package com.kframe.eurekaclient;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
/**
 * 配置 hystrix.stream 访问路径  依赖于 hystrix-metrics-event-stream
 * @category  When connecting to a /hystrix.stream endpoint that uses HTTPS, 
 * 	the certificate used by the server must be trusted by the JVM. 
 * 	If the certificate is not trusted, 
 * 	you must import the certificate into the JVM 
 * 	in order for the Hystrix Dashboard to make a successful connection to the stream endpoint.
 * {http:// host:port/hystrix.stream}
 * @author fk
 * 
 */
@Configuration
public class ServletRegistrationConfig {

	@Bean
	public ServletRegistrationBean getServlet() {
		 HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
	}
}
