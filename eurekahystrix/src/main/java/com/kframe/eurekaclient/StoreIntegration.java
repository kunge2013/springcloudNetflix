package com.kframe.eurekaclient;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class StoreIntegration {
	
	@HystrixCommand(fallbackMethod = "defaultStores")
	public Object getStores(Map<String, Object> parameters) {
		System.out.println(parameters);
		if(parameters == null) {
			return "xxxxxx";
		} else {
			throw new RuntimeException("call service fail.");
		}
	}

	public Object defaultStores(Map<String, Object> parameters) {
		/* something useful */
		return "Sorry, error!!! fallback defaultStores service！";
	}
}
