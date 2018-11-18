package com.cn.spring.jpa.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cn.spring.jpa.system.domain.SystemResource;

public interface SystemResourceRepository extends JpaRepository<SystemResource,Long> {
	/**
	 * 根据资源名称获取结果集
	 * @param resCode
	 * @return
	 */
	public SystemResource findFirstByResCode(String resCode);
	/**
	 * 根据资源名称统计
	 * @param unitName
	 * @return
	 */
	public long countByResCode(String resCode);
}
