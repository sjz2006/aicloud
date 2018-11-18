package com.cn.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cn.spring.jpa.system.comm.SystemConfig;

@SpringBootApplication
@EnableConfigurationProperties({SystemConfig.class})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.cn.spring.jpa") // 多个Spring数据模块的jpa存储库
//@EnableRedisRepositories(basePackages = "com.cn.spring.redis") //多个Spring数据模块redis存储库
public class MegoaiApplication {
	
	private static Logger logger = LoggerFactory.getLogger(MegoaiApplication.class);

	public static void main(String[] args) {
		logger.info("cc");
		SpringApplication.run(MegoaiApplication.class, args);
		logger.info("bbb");
	}
}
