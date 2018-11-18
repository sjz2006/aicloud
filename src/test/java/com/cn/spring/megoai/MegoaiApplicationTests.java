package com.cn.spring.megoai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.spring.UUIDEnum;
import com.cn.spring.jpa.system.domain.SystemOrgunit;
import com.cn.spring.jpa.system.domain.SystemUser;
import com.cn.spring.jpa.system.repository.SystemOrgunitJpaRepository;
import com.cn.spring.jpa.system.repository.SystemUserJpaRepository;
import com.cn.spring.jpa.system.service.SystemOrgunitService;
import com.cn.spring.jpa.system.service.SystemRoleUserService;
import com.cn.spring.jpa.system.service.SystemUserService;
import com.cn.spring.jpa.system.vo.ISystemRoleUser;
import com.cn.spring.jpa.system.vo.ISystemUserOrgunit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MegoaiApplicationTests {
	
	@Autowired
	private SystemUserJpaRepository userJpaRepository;
	
	@Autowired
	private SystemOrgunitJpaRepository orgunitJpaRepository;
	
	@Autowired
	private SystemOrgunitService systemOrgunitService;
	
	@Autowired
	private SystemRoleUserService systemRoleUserService;
	
	@Autowired
	private SystemUserService systemUserService;
	
	


	@Test
	public void contextLoads() {
		
		SystemUser user = new SystemUser();
		
		user.setEnabled(SystemUser.EnabledEnum.启用.getValue());
		user.setUserName("sjz2");
		user.setFullName("苏坚昭");
		user.setPassword("123456");
		user.setUserDesc("苏坚昭是谁");
		user.setUuid(UUIDEnum.UuidEnum.用户.getValue()+UUID.randomUUID().toString());
		user.setCreatedDate(LocalDateTime.now());
		userJpaRepository.save(user);
		
		SystemUser user11 = new SystemUser();
		
		user11.setEnabled(SystemUser.EnabledEnum.启用.getValue());
		user11.setUserName("sjz21");
		user11.setFullName("苏坚昭");
		user11.setPassword("123456");
		user11.setUserDesc("苏坚昭是谁");
		user11.setUuid(UUIDEnum.UuidEnum.用户.getValue()+UUID.randomUUID().toString());
		user11.setCreatedDate(LocalDateTime.now());
		userJpaRepository.save(user11);
		
		SystemUser user12 = new SystemUser();
		
		user12.setEnabled(SystemUser.EnabledEnum.启用.getValue());
		user12.setUserName("sjz212");
		user12.setFullName("苏坚昭");
		user12.setPassword("123456");
		user12.setUserDesc("苏坚昭是谁");
		user12.setUuid(UUIDEnum.UuidEnum.用户.getValue()+UUID.randomUUID().toString());
		user12.setCreatedDate(LocalDateTime.now());
		userJpaRepository.save(user12);
		
		SystemUser user1 = userJpaRepository.findByUserName("sjz");
		
		System.out.println("FullName="+user1.getFullName());
		
		SystemUser user2 = userJpaRepository.findByUserNameAndEnabled("sjz", SystemUser.EnabledEnum.启用.getValue());
		
		System.out.println("FullName2="+user2.getFullName());
		
		SystemUser user3 = userJpaRepository.findUserByUuid("SYSUSER-2fadc6ba-c7c3-43cb-a3b6-5a5d0df54d8c");
		
		System.out.println("userName="+user3.getUserName());
	}
	
	
	@Test
	public void testPageQuery() throws Exception {
		int page=1,size=4;
		//创建时间降序排序
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(page, size, sort);
	    
	    Page<SystemUser> systemUsers = userJpaRepository.findByFullName("苏坚昭", pageable);
	    
	    systemUsers.forEach(user -> {
	    	String userName = user.getUserName();
	    	System.out.println("userName="+userName);
	    });
	    
	    List<SystemUser> systemUser5s = userJpaRepository.findFirst5ByFullName("苏坚昭", sort);
	    systemUser5s.forEach(user -> {
	    	String fullName = user.getFullName();
	    	System.out.println("fullName="+fullName);
	    });
	    
	    SystemUser systemUser1s = userJpaRepository.findTopByOrderByIdDesc();
	    
	    System.out.println("uuid="+systemUser1s.getUuid());
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		userJpaRepository.modifyByUserName("苏坚昭112", "sjz112");
	}
	
	@Test
	public void testCount() throws Exception {
//		Long count = userJpaRepository.countByFullName("苏坚昭");
//		System.out.println("count="+count);
		
		LocalDate now = LocalDate.now();
		int value = now.getDayOfWeek().getValue();
		System.out.println(value);
	}
	
	@Test
	public void testFind() throws Exception {
//		Optional<SystemOrgunit> systemOrgunit = orgunitJpaRepository.findById(1L);
		
		SystemOrgunit systemOrgunit = new SystemOrgunit();
		systemOrgunit.setUnitName("米果智能设备有限公司");
		
		Example<SystemOrgunit> example = Example.of(systemOrgunit);
		
		Optional<SystemOrgunit> findOne = orgunitJpaRepository.findOne(example);
		
		
		
		SystemOrgunit orgunit = new SystemOrgunit();                          
		orgunit.setUnitName("米果智能设备有限公司");                           

		ExampleMatcher matcher = ExampleMatcher.matching()     
		  .withIgnorePaths("unitName")                         
		  .withIncludeNullValues();                     

		Example<SystemOrgunit> example1 = Example.of(orgunit, matcher); 
		
		Optional<SystemOrgunit> findtwo = orgunitJpaRepository.findOne(example1);
	}
	
	
	@Test
	public void testAddOrgUnit() throws Exception {
		Map<String,Object> message = new HashMap<String,Object>();
		SystemOrgunit systemOrgunit = new SystemOrgunit();
		systemOrgunit.setEnabled(SystemOrgunit.EnabledEnum.启用.getValue());
		systemOrgunit.setParentId(5L);
		systemOrgunit.setUnitName("自由心岸菜吧");
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
	public void testSaveSystemRoleUser() throws Exception {
		Map<String,Object> message = new HashMap<String,Object>();
		systemRoleUserService.saveSystemRoleUser(1l, 4l, message);
	}
	
	@Test
	public void testFindByUserName() throws Exception {
		
		int page=0,size=4;
		//创建时间降序排序
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(page, size, sort);
	    String userName = "sjz";
	    
		Page<ISystemRoleUser> pages = systemRoleUserService.findByUserName(userName, pageable);
		
		pages.forEach(u -> {
			Long id = u.getId();
			String roleName = u.getRoleName();
			String userName2 = u.getUserName();
			System.out.println(id + roleName + userName2);
		});
		
		
	}
	
	@Test
	public void testFindByUnitName() throws Exception {
		
		int page=0,size=4;
		//创建时间降序排序
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(page, size, sort);
	    String unitName = "广州易鲜坊食品有限公司";
	    
	    Page<ISystemUserOrgunit> iSystemUserOrgunits = systemUserService.findByUnitName(unitName, pageable);
	    
	    iSystemUserOrgunits.forEach(u -> {
	    	String fullName = u.getFullName();
	    	String unitName2 = u.getUnitName();
	    	LocalDateTime createdDate = u.getCreatedDate();
	    	
	    	System.out.println(fullName +" "+ unitName2 +" "+ createdDate);
	    });
	
	}
	
	
	
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		int value = now.getDayOfWeek().getValue();
		System.out.println(value);
	}

}
