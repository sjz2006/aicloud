package com.cn.spring.jpa.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cn.spring.jpa.system.domain.SystemUser;
import com.cn.spring.jpa.system.vo.ISystemRoleUser;
import com.cn.spring.jpa.system.vo.ISystemUserOrgunit;

public interface SystemUserJpaRepository extends JpaRepository<SystemUser,Long>,JpaSpecificationExecutor<SystemUser> {
	/**
	 * 根据用户查找用户信息
	 * @param userName
	 * @return
	 */
	public SystemUser findByUserName(String userName);
	/**
	 * 根据用户查找已启用的用户信息
	 * @param userName
	 * @param enabled
	 * @return
	 */
	public SystemUser findByUserNameAndEnabled(String userName,String enabled);
	/**
	 * 根据用户的uuid获取用户信息
	 * @param name
	 * @return
	 */
	@Query("from SystemUser u where u.uuid=:uuid")
	public SystemUser findUserByUuid(@Param("uuid") String uuid);
	/**
	 * 根据用户全程分页查询用户信息
	 * @param fullName
	 * @param pageable
	 * @return
	 */
	public Page<SystemUser> findByFullName(String fullName,Pageable pageable);
	/**
	 * 根据机构名称获取用户信息
	 * @param unitName
	 * @param pageable
	 * @return
	 */
	@Query(value=" select u.id,u.userName,u.fullName,u.enabled,u.orgUnitId as orgunitId,o.unitName,u.createdDate from system_user u "
		    +" left join system_orgunit o on u.orgUnitId = o.id "
            +" where o.unitName =?1 ",nativeQuery=true)
	public Page<ISystemUserOrgunit> findByUnitName(String unitName,Pageable pageable);
	/**
	 * 根据用户全程查询前5的用户信息
	 * @param fullName
	 * @param sort
	 * @return
	 */
	public List<SystemUser> findFirst5ByFullName(String fullName, Sort sort);
	
	/**
	 * 根据用户全称【去重】查询用户信息
	 * @param fullName
	 * @return
	 */
	public List<SystemUser> findDistinctByFullName(String fullName);
	/**
	 * 根据用户id倒序获取第一个用户信息
	 * @return
	 */
	public SystemUser findTopByOrderByIdDesc();
	/**
	 * 按照用户名计数
	 * @param userName
	 * @return
	 */
	public Long countByUserName(String userName);
	
	/**
	 * 按照全称计数
	 * @param fullName
	 * @return
	 */
	public Long countByFullName(String fullName);
	
	/**
	 * 按照用户id计数
	 * @param id
	 * @return
	 */
	public Long countById(Long id);
	/**
	 * 根据用户名修改用户全程
	 * @param fullName
	 * @param userName
	 */
	@Transactional
	@Modifying
	@Query("update SystemUser u set u.fullName = ?1 where u.userName = ?2")
	public void modifyByUserName(String fullName,String userName);
	
	@Query(value=" select ru.id,r.roleName,u.userName from system_role_user ru "
            +" left join system_role r on ru.roleId = r.id "
		    +" left join system_user u on ru.userId = u.id "
            +" where u.userName =?1 ",nativeQuery=true)
	public Page<ISystemRoleUser> findByUserName(String userName,Pageable pageable);

}
