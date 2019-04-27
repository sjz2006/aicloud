package com.cn.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cn.spring.jpa.system.comm.SystemConfig;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties({SystemConfig.class})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.cn.spring.jpa") // 多个Spring数据模块的jpa存储库
//@EnableRedisRepositories(basePackages = "com.cn.spring.redis") //多个Spring数据模块redis存储库
public class MegoaiApplication extends SpringBootServletInitializer{
	
	private static Logger logger = LoggerFactory.getLogger(MegoaiApplication.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MegoaiApplication.class);
	}

	public static void main(String[] args) {
		logger.info("ccdd");
		SpringApplication.run(MegoaiApplication.class, args);
		logger.info("bbb");
	}
}
