package com.cn.spring.jpa.system.service;

import java.util.Map;

import com.cn.spring.jpa.system.domain.SystemOrgunit;

public interface SystemOrgunitService {
	/**
	 * 新增机构
	 * @param systemOrgunit
	 */
	public void saveSystemOrgunit(SystemOrgunit systemOrgunit,Map<String,Object> message);
	
	/**
	 * 更新机构
	 * @param systemOrgunit
	 */
	public void updateSystemOrgunit(SystemOrgunit systemOrgunit,Map<String,Object> message);

}
