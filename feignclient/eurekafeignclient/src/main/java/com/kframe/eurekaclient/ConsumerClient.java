package com.kframe.eurekaclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eurekaclient")
public interface ConsumerClient {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home();
}
