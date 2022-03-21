package com.example.demo;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@SpringBootApplication
public class MisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisApplication.class, args);
	}
	
	/**
	 * 验证码
	 * @return
	 */
	@Bean  
	public DefaultKaptcha captchaProducer() {  
	    DefaultKaptcha captchaProducer = new DefaultKaptcha();  
	    Properties properties = new Properties();  
//	    properties.setProperty(Constants.KAPTCHA_BORDER, "yes");  
//	    properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "red");  
//	    properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");  
	    properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");  
	    properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");  
	    properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");  
	    properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");  
	    properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "120");  
	    properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "50");  
	    properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "code");  
	    Config config = new Config(properties);  
	    captchaProducer.setConfig(config);  
	    return captchaProducer;  
	}  

}
