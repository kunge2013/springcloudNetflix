package org.springframework.cloud.gateway.sample;


import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.Route.AsyncBuilder;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
 class AdditionalRoutes {

    @Value("${test.uri:http://httpbin.org:80}")
    String uri;

    @Bean
    void additionalRouteLocator(RouteLocatorBuilder builder)  {
    	builder.routes().route("test-kotlin", new Function<PredicateSpec, Route.AsyncBuilder>() {
			
			@Override
			public AsyncBuilder apply(PredicateSpec t) {
				// TODO Auto-generated method stub
				return null;
			}
		});
    }

}
