package com.cn.spring.jpa.system.service;

import java.util.Map;

import com.cn.spring.jpa.system.domain.SystemRole;

public interface SystemRoleService {
	
	/**
	 * 新增角色
	 * @param systemRole
	 */
	public void saveSystemRole(SystemRole systemRole,Map<String,Object> message);
	
	/**
	 * 更新角色
	 * @param systemRole
	 */
	public void updateSystemRole(SystemRole systemRole,Map<String,Object> message);

}
