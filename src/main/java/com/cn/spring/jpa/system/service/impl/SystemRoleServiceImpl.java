package com.cn.spring.jpa.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.spring.UUIDEnum;
import com.cn.spring.jpa.system.comm.KeysEnum;
import com.cn.spring.jpa.system.comm.StatusCodeEnum;
import com.cn.spring.jpa.system.comm.SystemConfig;
import com.cn.spring.jpa.system.domain.SystemRole;
import com.cn.spring.jpa.system.repository.SystemRoleJpaRepository;
import com.cn.spring.jpa.system.service.SystemRoleService;

@Service(value="SystemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {
	
	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	private SystemRoleJpaRepository systemRoleJpaRepository;

	@Transactional
	@Override
	public void saveSystemRole(SystemRole systemRole, Map<String, Object> message) {
		
		String roleName = systemRole.getRoleName();
		
		long countByRoleName = systemRoleJpaRepository.countByRoleName(roleName);
		
		if(countByRoleName>0) {
			message.put(KeysEnum.statusCode.getValue(), StatusCodeEnum.失败.getValue());
			message.put(KeysEnum.message.getValue(), systemConfig.getErr002());
			return;
		}
		
		String roleCode = systemRole.getRoleCode();
		
		long countByRoleCode = systemRoleJpaRepository.countByRoleCode(roleCode);
		
		if(countByRoleCode>0) {
			message.put(KeysEnum.statusCode.getValue(), StatusCodeEnum.失败.getValue());
			message.put(KeysEnum.message.getValue(), systemConfig.getErr003());
			return;
		}
		
		systemRole.setCreatedDate(LocalDateTime.now());
		systemRole.setUuid(UUIDEnum.UuidEnum.角色.getValue()+UUID.randomUUID().toString());
		systemRoleJpaRepository.save(systemRole);
		
	}

	@Transactional
	@Override
	public void updateSystemRole(SystemRole systemRole, Map<String, Object> message) {
		
		Long roleId = systemRole.getId();
		
		// 获取数据库原始值
		Optional<SystemRole> systemRolePOT = systemRoleJpaRepository.findById(roleId);
		if(systemRolePOT.isPresent()==false) {
			return;
		}
		
		SystemRole systemRolePOJO = systemRolePOT.get();
		
		try {
			
			BeanUtils.copyProperties(systemRole, systemRolePOJO);
			systemRole.setModifiedDate(LocalDateTime.now());
			systemRoleJpaRepository.save(systemRole);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
