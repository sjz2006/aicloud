package com.cn.spring.megoai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.spring.jpa.system.domain.SystemRole;
import com.cn.spring.jpa.system.service.SystemRoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemRoleTests {
	
	@Autowired
	private SystemRoleService roleService;

	@Test
	public void testAddRole() {
		
		Map<String,Object> message = new HashMap<String,Object>();
		
		SystemRole systemRole = new SystemRole();
		systemRole.setRoleName(SystemRole.RoleCodeEnum.运维管理员.getLabel());
		systemRole.setRoleCode(SystemRole.RoleCodeEnum.运维管理员.getValue());
		systemRole.setCreatedDate(LocalDateTime.now());
		roleService.saveSystemRole(systemRole, message);
	}
	

	
	
	
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		int value = now.getDayOfWeek().getValue();
		System.out.println(value);
	}

}
