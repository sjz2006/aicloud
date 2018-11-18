package com.cn.spring.jpa.system.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cn.spring.jpa.system.comm.EnumDictable;
/**
 * 用户表
 * @author 苏坚昭
 *
 */
@Entity
@Table(name="system_user")
public class SystemUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6454567350405178117L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * 用户登录用户名
	 */
	@NotNull
	@Column(name = "userName", unique = true, nullable = false)
	private String userName;
	/**
	 * 用户登录密码
	 */
	@NotNull
	@Column(name = "password")
	private String password;
	/**
	 * 用户全称
	 */
	@Column(name = "fullName")
	private String fullName;
	/**
	 * 用户启用状态
	 */
	@Column(name = "enabled")
	private String enabled;
	/**
	 * 用户描述
	 */
	@Column(name = "userDesc")
	private String userDesc;
	/**
	 * 用户创建时间
	 */
	@Column(name = "createdDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdDate;
	/**
	 * 用户修改时间
	 */
	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	
	/**
	 * 用户的uuid
	 */
	@Column(name = "uuid")
	private String uuid;
	
	/**
	 * 所在组织id
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="orgunitId")
	private SystemOrgunit orgunit;
	
	/**
	 * 角色id 负责维护用户与角色的关系
	 */
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="system_role_user",
	joinColumns= {@JoinColumn(name="userId")},
	inverseJoinColumns= {@JoinColumn(name="roleId")})
	private Set<SystemRole> roles = new HashSet<SystemRole>();
	
	public enum EnabledEnum implements EnumDictable {
		未启用("未启用"),启用("启用");

		private String value;

		EnabledEnum(String value) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
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

	public SystemOrgunit getOrgunit() {
		return orgunit;
	}

	public void setOrgunit(SystemOrgunit orgunit) {
		this.orgunit = orgunit;
	}

	public Set<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SystemRole> roles) {
		this.roles = roles;
	}
	
}
