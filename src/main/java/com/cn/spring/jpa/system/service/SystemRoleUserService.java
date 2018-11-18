package com.cn.spring.jpa.system.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cn.spring.jpa.system.vo.ISystemRoleUser;

public interface SystemRoleUserService {
	/**
	 * 新增角色与用户的关系
	 * @param roleId
	 * @param userId
	 * @param message
	 */
	public void saveSystemRoleUser(Long roleId,Long userId,Map<String,Object> message);
	/**
	 * 根据用户列出用户参与的角色
	 * @param userName
	 * @param pageable
	 * @return
	 */
	public Page<ISystemRoleUser> findByUserName(String userName,Pageable pageable); 

}
