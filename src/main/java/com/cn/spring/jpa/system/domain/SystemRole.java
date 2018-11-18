package com.cn.spring.jpa.system.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cn.spring.jpa.system.comm.EnumDictable;
/**
 * 角色表
 * @author 苏坚昭
 *
 */
@Entity
@Table(name="system_role")
public class SystemRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4967909979408379171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * 角色编号
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(name = "roleCode", unique = true, nullable = false)
	private String roleCode;
	/**
	 * 角色名称
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(name = "roleName", unique = true, nullable = false)
	private String roleName;
	/**
	 * 角色描述
	 */
	@Column(name = "roleDesc")
	private String roleDesc;

	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	/**
	 * 角色uuid
	 */
	@Column(name="uuid")
	private String uuid;
	
	/**
	 * 角色与用户的关联关系  由用户维护用户与角色的关系
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="roles")
	private Set<SystemUser> users = new HashSet<SystemUser>();
	
	/**
	 * 角色与资源的的关联关系  由资源维护资源与角色的关系
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="roles")
	private Set<SystemResource> resources = new HashSet<SystemResource>();
	
	
	public enum RoleCodeEnum implements EnumDictable {
		超级管理员("admin"),
		平台管理员("p_admin"),
		运维管理员("OPS"),
		API通信用户("API");

		private String value;

		RoleCodeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
