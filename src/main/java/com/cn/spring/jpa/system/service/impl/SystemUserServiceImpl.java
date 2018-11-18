package com.cn.spring.jpa.system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.spring.UUIDEnum;
import com.cn.spring.jpa.system.comm.KeysEnum;
import com.cn.spring.jpa.system.comm.PageInfo;
import com.cn.spring.jpa.system.comm.StatusCodeEnum;
import com.cn.spring.jpa.system.comm.SystemConfig;
import com.cn.spring.jpa.system.domain.SystemRole;
import com.cn.spring.jpa.system.domain.SystemUser;
import com.cn.spring.jpa.system.repository.SystemUserJpaRepository;
import com.cn.spring.jpa.system.service.SystemUserService;
import com.cn.spring.jpa.system.vo.ISystemUserOrgunit;
import com.cn.spring.jpa.system.vo.QuerySystemUser;
import com.cn.spring.jpa.system.vo.SystemUserOrgunitVo;
@Service(value="SystemUserService")
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	private SystemUserJpaRepository systemUserJpaRepository;
	
	@Transactional
	@Override
	public void saveSystemUser(SystemUser user, Map<String, Object> message) {
		
		String userName = user.getUserName();
		
		Long countByUserName = systemUserJpaRepository.countByUserName(userName);
		
		if(countByUserName>0) {
			message.put(KeysEnum.statusCode.getValue(), StatusCodeEnum.失败.getValue());
			message.put(KeysEnum.message.getValue(), systemConfig.getErr002());
			return;
		}
		
		user.setUuid(UUIDEnum.UuidEnum.用户.getValue()+UUID.randomUUID().toString());
		user.setCreatedDate(LocalDateTime.now());
		systemUserJpaRepository.save(user);
		
	}
	
	@Transactional
	@Override
	public void updateSystemUser(SystemUser user, Map<String, Object> message) {

		Long id = user.getId();
		
		Optional<SystemUser> systemUserPOT = systemUserJpaRepository.findById(id);
		
		if(systemUserPOT.isPresent()==false) {
			return;
		}
		
		SystemUser systemUserPOJO = systemUserPOT.get();
		
		Set<SystemRole> roles = systemUserPOJO.getRoles();
		roles.forEach(u->{
			System.out.println("aa" + u.getRoleName());
		});
		
		user.setModifiedDate(LocalDateTime.now());
		systemUserJpaRepository.save(user);
		
	
	}
	

	@Override
	public Page<ISystemUserOrgunit> findByUnitName(String unitName, Pageable pageable) {
		Page<ISystemUserOrgunit> iSystemUserOrgunits = systemUserJpaRepository.findByUnitName(unitName, pageable);
		return iSystemUserOrgunits;
	}

	@Transactional(readOnly=true)
	@Override
	public Page<SystemUserOrgunitVo> findByCondition(QuerySystemUser query) {
		
		int page = query.getPage();
		int size = query.getSize();
		String direction = query.getDirection();
		String dirname = query.getDirname();
		
		Sort sort = null;
		if(PageInfo.DirectionEnum.倒序.getValue().equals(direction)) {
			sort = new Sort(Direction.DESC,dirname);
		}else {
			sort = new Sort(Direction.ASC,dirname);
		}
		
	    Pageable pageable = PageRequest.of(page, size, sort);
	    
	    
	    Page<SystemUser> systemuserPage = systemUserJpaRepository.findAll(new Specification<SystemUser>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8826217331278769378L;

			@Override
			public Predicate toPredicate(Root<SystemUser> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Selection<?> selection = cq.getSelection();
				
				System.out.println(selection.getAlias());
				
				if(query.getUserName()!=null) {
					predicates.add(cb.equal(root.get("userName").as(String.class), query.getUserName()));
				}
				
				if(query.getUnitName()!=null) {
					predicates.add(cb.equal(root.get("orgunit").get("unitName").as(String.class), query.getUnitName()));
				}
				
				
				if(query.getEnabled()!=null) {
					predicates.add(cb.equal(root.get("enabled").as(String.class), query.getEnabled()));
				}
				
				if(query.getFullName()!=null) {
					predicates.add(cb.like(root.get("fullName").as(String.class), "%"+query.getFullName()+"%"));
				}
				
				if(query.getBeginDate()!=null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), query.getBeginDate()));
				}
				
				if(query.getEndDate()!=null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), query.getEndDate()));
				}
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
	    	
	    }, pageable);
	    
	    
	    List<SystemUser> content = systemuserPage.getContent();
	    
	    content.forEach(c -> {
	    	System.out.println("aa" + c.getOrgunit().getOrgUnitCode());
	    });
	    
	    
		return null;
	}
}
