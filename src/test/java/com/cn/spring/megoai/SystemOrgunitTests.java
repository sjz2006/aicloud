package com.cn.spring.megoai;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.spring.jpa.system.domain.SystemOrgunit;
import com.cn.spring.jpa.system.repository.SystemOrgunitJpaRepository;
import com.cn.spring.jpa.system.service.SystemOrgunitService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemOrgunitTests {
	
	@Autowired
	private SystemOrgunitJpaRepository orgunitJpaRepository;
	
	@Autowired
	private SystemOrgunitService systemOrgunitService;
	
	@Test
	public void testAddOrgUnit() throws Exception {
		Map<String,Object> message = new HashMap<String,Object>();
		SystemOrgunit systemOrgunit = new SystemOrgunit();
		systemOrgunit.setEnabled(SystemOrgunit.EnabledEnum.启用.getValue());
		systemOrgunit.setParentId(3L);
		systemOrgunit.setUnitName("富力中心");
		systemOrgunit.setUnitType(SystemOrgunit.UnitTypeEnum.门店.getValue());
		systemOrgunitService.saveSystemOrgunit(systemOrgunit, message);
	}
	
	@Test
	public void testUpdateOrgUnit() throws Exception {
		Map<String,Object> message = new HashMap<String,Object>();
		SystemOrgunit systemOrgunit = new SystemOrgunit();
		systemOrgunit.setId(3L);
		systemOrgunit.setEnabled(SystemOrgunit.EnabledEnum.启用.getValue());
		systemOrgunit.setParentId(2L);
		systemOrgunit.setUnitName("金沙洲城西花园");
		systemOrgunit.setUnitType(SystemOrgunit.UnitTypeEnum.门店.getValue());
		systemOrgunitService.updateSystemOrgunit(systemOrgunit, message);
	}
	
	@Test
	public void testFind() throws Exception {
//		Optional<SystemOrgunit> systemOrgunit = orgunitJpaRepository.findById(1L);
		
		SystemOrgunit systemOrgunit = new SystemOrgunit();
		systemOrgunit.setUnitName("泰有超市");
		
		Example<SystemOrgunit> example = Example.of(systemOrgunit);
		
		Optional<SystemOrgunit> findOne = orgunitJpaRepository.findOne(example);
		
		if(findOne.isPresent()==true) {
			SystemOrgunit ty = findOne.get();
			System.out.println(ty.getUnitName());
		}
		
		
		
		SystemOrgunit orgunit = new SystemOrgunit();                          
		orgunit.setUnitName("米果智能设备有限公司");                           

		ExampleMatcher matcher = ExampleMatcher.matching()     
		  .withIgnorePaths("unitName")                         
		  .withIncludeNullValues();                     

		Example<SystemOrgunit> example1 = Example.of(orgunit, matcher); 
		
		Optional<SystemOrgunit> findtwo = orgunitJpaRepository.findOne(example1);
	}
	
	
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		int value = now.getDayOfWeek().getValue();
		System.out.println(value);
	}

}
