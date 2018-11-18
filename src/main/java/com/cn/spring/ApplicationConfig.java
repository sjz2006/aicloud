package com.cn.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource(value= {"classpath:application.yml"},ignoreResourceNotFound = true,encoding = "utf-8")
@ConfigurationProperties
public class ApplicationConfig {
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
