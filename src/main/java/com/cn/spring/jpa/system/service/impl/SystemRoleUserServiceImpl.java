package com.cn.spring.jpa.system.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cn.spring.jpa.system.service.SystemRoleUserService;
import com.cn.spring.jpa.system.vo.ISystemRoleUser;

public class SystemRoleUserServiceImpl implements SystemRoleUserService {

	@Override
	public void saveSystemRoleUser(Long roleId, Long userId, Map<String, Object> message) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<ISystemRoleUser> findByUserName(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
