package com.cn.spring.jpa.system.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cn.spring.jpa.system.domain.SystemUser;
import com.cn.spring.jpa.system.vo.ISystemUserOrgunit;
import com.cn.spring.jpa.system.vo.QuerySystemUser;
import com.cn.spring.jpa.system.vo.SystemUserOrgunitVo;

public interface SystemUserService {
	/**
	 * 新增用户
	 * @param user
	 * @param message
	 */
	public void saveSystemUser(SystemUser user, Map<String, Object> message);
	/**
	 * 更新用户
	 * @param user
	 * @param message
	 */
	public void updateSystemUser(SystemUser user, Map<String, Object> message);
	/**
	 * 用户信息管理列表
	 * @param query
	 * @return
	 */
	public Page<SystemUserOrgunitVo> findByCondition(QuerySystemUser query);
	/**
	 * 根据用户名查询用户信息
	 * @param unitName
	 * @param pageable
	 * @return
	 */
	public Page<ISystemUserOrgunit> findByUnitName(String unitName, Pageable pageable);

}
