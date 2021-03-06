package com.cn.spring.jpa.system.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cn.spring.ApplicationConfig;
import com.cn.spring.jpa.system.comm.SystemConfig;

@RestController
public class SystemOrgunitCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(SystemOrgunitCtrl.class);
	
	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	@RequestMapping("/")
	public Map<String,Object> index(){
		
		List<String> services = discoveryClient.getServices();
		
		logger.info("AAA Services="+services.size());
		
		
		Map<String,Object> jsonMessage =  new HashMap<String,Object>();
		
		jsonMessage.put("Err001",systemConfig.getErr001());
		
		System.out.println(applicationConfig.getUrl());
		
		
		return jsonMessage;
		
	}
	
	/**
	 * 整个ctrl发生异常进行捕获
	 * @param ex
	 * @return
	 */
	@ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleIOException(RuntimeException ex) {
		Map<String,Object> jsonMessage =  new HashMap<String,Object>();
		return jsonMessage;
	}

}
