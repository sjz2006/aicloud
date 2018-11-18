package com.cn.spring.megoai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.spring.jpa.system.comm.PageInfo;
import com.cn.spring.jpa.system.domain.SystemOrgunit;
import com.cn.spring.jpa.system.domain.SystemRole;
import com.cn.spring.jpa.system.domain.SystemUser;
import com.cn.spring.jpa.system.repository.SystemOrgunitJpaRepository;
import com.cn.spring.jpa.system.repository.SystemRoleJpaRepository;
import com.cn.spring.jpa.system.repository.SystemUserJpaRepository;
import com.cn.spring.jpa.system.service.SystemUserService;
import com.cn.spring.jpa.system.vo.ISystemUserOrgunit;
import com.cn.spring.jpa.system.vo.QuerySystemUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemUserTests {
	
	@Autowired
	private SystemUserJpaRepository userJpaRepository;
	
	@Autowired
	private SystemOrgunitJpaRepository systemOrgunitJpaRepository;
	
	@Autowired
	private SystemRoleJpaRepository systemRoleJpaRepository;
	
	
	@Autowired
	private SystemUserService systemUserService;

	@Test
	public void testSave() {
		
		Map<String,Object> message = new HashMap<String,Object>();
		
		SystemUser user = new SystemUser();
		
		user.setEnabled(SystemUser.EnabledEnum.启用.getValue());
		user.setUserName("wzy");
		user.setFullName("王志勇");
		user.setPassword("123456");
		user.setUserDesc("王志勇是谁");
		
		SystemOrgunit orgunit = systemOrgunitJpaRepository.findByUnitName("泰有超市");
		
		
		user.setOrgunit(orgunit);
		
		Set<SystemRole> roles = new HashSet<SystemRole>();
		
		SystemRole admin = systemRoleJpaRepository.findFirstByRoleName("超级管理员");
		roles.add(admin);
		
		SystemRole p_admin = systemRoleJpaRepository.findFirstByRoleName("平台管理员");
		roles.add(p_admin);
		
		user.setRoles(roles);
		
		systemUserService.saveSystemUser(user, message);
		
	}
	
	@Test
	public void testUpdate() {
		
		Map<String,Object> message = new HashMap<String,Object>();
		
		
		SystemUser updateUser = userJpaRepository.findByUserName("wzy");
		
		Set<SystemRole> roles = new HashSet<SystemRole>();
		
		
		SystemRole p_admin = systemRoleJpaRepository.findFirstByRoleName("平台管理员");
		roles.add(p_admin);
		
		updateUser.setRoles(roles);
		
		systemUserService.updateSystemUser(updateUser, message);
		
	}
	
	
	@Test
	public void testFindByCondition() {
		
		QuerySystemUser query = new QuerySystemUser();
		query.setEnabled(SystemUser.EnabledEnum.启用.getValue());
		query.setPage(0);
		query.setSize(2);
		query.setDirection(PageInfo.DirectionEnum.升序.getValue());
		query.setDirname("id");
		query.setFullName("苏");
		query.setUnitName("米果运营中心");
		
		query.setBeginDate(LocalDateTime.of(2018, 10, 01, 12, 00));
		query.setEndDate(LocalDateTime.of(2018, 10, 03, 12, 00));
		
		systemUserService.findByCondition(query);
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
	public void testCount() throws Exception {
//		Long count = userJpaRepository.countByFullName("苏坚昭");
//		System.out.println("count="+count);
		
		LocalDate now = LocalDate.now();
		int value = now.getDayOfWeek().getValue();
		System.out.println(value);
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
