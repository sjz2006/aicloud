package com.cn.spring.jpa.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cn.spring.jpa.system.domain.SystemRole;

public interface SystemRoleJpaRepository  extends JpaRepository<SystemRole,Long> {
	
	/**
	 * 根据角色名称获取角色信息
	 * @param roleName
	 * @return
	 */
	public SystemRole findFirstByRoleName(String roleName);
	
	/**
	 * 根据角色名称统计
	 * @param roleName
	 * @return
	 */
	public long countByRoleName(String roleName);
	
	/**
	 * 根据角色编号获取角色信息
	 * @param roleCode
	 * @return
	 */
	public SystemRole findFirstByRoleCode(String roleCode);
	
	/***
	 * 根据角色编号统计
	 * @param roleCode
	 * @return
	 */
	public long countByRoleCode(String roleCode);
	
	/***
	 * 根据角色id统计
	 * @param id
	 * @return
	 */
	public long countById(Long id);

}
