package com.cn.spring.jpa.system.service;

import java.util.Map;

import com.cn.spring.jpa.system.domain.SystemResource;

public interface SystemResourceService {
	
	/**
	 * 新增资源
	 * @param SystemResource
	 */
	public void saveSystemResource(SystemResource systemResource,Map<String,Object> message);
	
	/**
	 * 更新资源
	 * @param systemResource
	 */
	public void updateSystemResource(SystemResource systemResource,Map<String,Object> message);

}
