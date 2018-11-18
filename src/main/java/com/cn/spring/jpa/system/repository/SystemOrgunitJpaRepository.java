package com.cn.spring.jpa.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cn.spring.jpa.system.domain.SystemOrgunit;

public interface SystemOrgunitJpaRepository extends JpaRepository<SystemOrgunit,Long>,JpaSpecificationExecutor<SystemOrgunit> {
	/**
	 * 根据机构名称获取结果集
	 * @param unitName
	 * @return
	 */
	public SystemOrgunit findByUnitName(String unitName);
	/**
	 * 根据机构名称统计
	 * @param unitName
	 * @return
	 */
	public long countByUnitName(String unitName);
	
	/**
	 * 根据机构的编号
	 * @param orgUnitCode
	 * @param id
	 */
	@Modifying
	@Query("update SystemOrgunit o set o.orgUnitCode = ?1 where o.id = ?2")
	public void modifyOrgUnitCodeById(String orgUnitCode,Long id);
	/**
	 * 根据机构名称删除
	 * @param unitName
	 * @return
	 */
	public long deleteByUnitName(String unitName);
}
