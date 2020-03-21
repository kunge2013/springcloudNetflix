package com.kframe.oss.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码样式配置
 * @author fk
 */
@Configuration
public class KaptchaConfig {

	@Value("${kaptcha.image.width}")
	private String kaptchaimagewidth = "120";

	@Value("${kaptcha.image.height}")
	private String kaptchaimageheight = "45";

	@Bean
	public DefaultKaptcha getDefaultKaptcha() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", "no");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.font.color", "red");
		properties.setProperty("kaptcha.noise.color", "red");
		properties.setProperty("kaptcha.textproducer.char.space", "4");

		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.image.width", kaptchaimagewidth);
		properties.setProperty("kaptcha.image.height", kaptchaimageheight);
//        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

}
