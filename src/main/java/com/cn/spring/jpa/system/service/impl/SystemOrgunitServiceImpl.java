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
import com.cn.spring.jpa.system.domain.SystemOrgunit;
import com.cn.spring.jpa.system.repository.SystemOrgunitJpaRepository;
import com.cn.spring.jpa.system.service.SystemOrgunitService;

@Service(value="SystemOrgunitService")
public class SystemOrgunitServiceImpl implements SystemOrgunitService {
	
	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	private SystemOrgunitJpaRepository systemOrgunitJpaRepository;

	@Transactional
	@Override
	public void saveSystemOrgunit(SystemOrgunit systemOrgunit,Map<String,Object> message) {
		
		String unitName = systemOrgunit.getUnitName();
		
		long countByUnitName = systemOrgunitJpaRepository.countByUnitName(unitName);
		if(countByUnitName>0) {
			message.put(KeysEnum.statusCode.getValue(), StatusCodeEnum.失败.getValue());
			message.put(KeysEnum.message.getValue(), systemConfig.getErr001());
			return;
		}
		
		systemOrgunit.setCreatedDate(LocalDateTime.now());
		systemOrgunit.setUuid(UUIDEnum.UuidEnum.机构.getValue()+UUID.randomUUID().toString());
		systemOrgunitJpaRepository.save(systemOrgunit);
		
		// 计算机构的编号
		
		Long orgUnitId = systemOrgunit.getId();
		// 机构父级id
		Long parentId = systemOrgunit.getParentId();
		String orgUnitCode ="0000";
		if(parentId!=null) {
			
			Optional<SystemOrgunit> parent = systemOrgunitJpaRepository.findById(parentId);
			
			if(parent.isPresent()==true) {
				SystemOrgunit parentOrgunit = parent.get();
				String parentOrgUnitCode = parentOrgunit.getOrgUnitCode();
				orgUnitCode = parentOrgUnitCode+String.format(",%04d",orgUnitId);
			}else {
				orgUnitCode = String.format("%04d",orgUnitId);
			}
			
		}else {
			orgUnitCode = String.format("%04d",orgUnitId);
		}
		
		systemOrgunitJpaRepository.modifyOrgUnitCodeById(orgUnitCode, orgUnitId);
	}

	@Transactional
	@Override
	public void updateSystemOrgunit(SystemOrgunit systemOrgunit, Map<String, Object> message) {
		
		Long orgUnitId = systemOrgunit.getId();
		
		// 获取数据库原始值
		Optional<SystemOrgunit> systemOrgunitPOT = systemOrgunitJpaRepository.findById(orgUnitId);
		if(systemOrgunitPOT.isPresent()==false) {
			return;
		}
		
		SystemOrgunit systemOrgunitPOJO = systemOrgunitPOT.get();
		
		try {
			
			BeanUtils.copyProperties(systemOrgunit, systemOrgunitPOJO);
			
			// 机构父级id
			Long parentId = systemOrgunit.getParentId();
			
			String orgUnitCode ="0000";
			if(parentId!=null){
				
				Optional<SystemOrgunit> parent = systemOrgunitJpaRepository.findById(parentId);
				
				if(parent.isPresent()==true) {
					SystemOrgunit parentOrgunit = parent.get();
					String parentOrgUnitCode = parentOrgunit.getOrgUnitCode();
					orgUnitCode = parentOrgUnitCode+String.format(",%04d",orgUnitId);
				}else {
					orgUnitCode = String.format("%04d",orgUnitId);
				}
			}else{
				orgUnitCode = String.format("%04d",orgUnitId);
			}
			systemOrgunit.setOrgUnitCode(orgUnitCode);
			
			systemOrgunit.setModifiedDate(LocalDateTime.now());
			systemOrgunitJpaRepository.save(systemOrgunit);
			
			//更新本机构子机构
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
