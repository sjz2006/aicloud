package com.cn.spring.jpa.system.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cn.spring.jpa.system.comm.EnumDictable;
/**
 * 资源表
 * @author 苏坚昭
 *
 */
@Entity
@Table(name="system_resource")
public class SystemResource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4621456675053961592L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/**
     * 资源URL
     */
	@Column(name = "resCode", unique = true, nullable = false)
	private String resCode;
	/**
     * 资源名称
     */
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "resName")
	private String resName;
	/**
     * 资源描述
     */
	@Column(name = "resDesc")
	private String resDesc;
	/**
     * 资源类型
     */
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "resType")
	private String resType;
	
	/**
	 * 资源属实终端类型
	 */
	@Column(name = "terminalType")
	private String terminalType;
	/**
     * 资源模块
     */
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "resModule")
	private String resModule;
	
	/**
     * 资源父ID
     */
	@Column(name = "parentId")
	private Long parentId;
	/**
     * 资源菜单排序
     */
	@Column(name = "sortSeed")
	private String sortSeed;
	

	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	
	/**
	 * 角色id 负责维护用户与角色的关系
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="system_role_resource",
	joinColumns= {@JoinColumn(name="resourceId")},
	inverseJoinColumns= {@JoinColumn(name="roleId")})
	private Set<SystemRole> roles = new HashSet<SystemRole>();
	

	public enum ResModuleEnum implements EnumDictable {
		系统管理("system"),
		API("api"),
		UI("ui");
		
		public final static EnumSet<ResModuleEnum> RESMODULE1 = EnumSet.of(系统管理);

		private String value;

		ResModuleEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}

	public enum ResTypeEnum implements EnumDictable {
		菜单("menu"), 按钮("button");

		private String value;

		ResTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}
	
	public enum TerminalTypeEnum implements EnumDictable {
		PC("PC"), APP("APP");

		private String value;

		TerminalTypeEnum(String value) {
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

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getResModule() {
		return resModule;
	}

	public void setResModule(String resModule) {
		this.resModule = resModule;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSortSeed() {
		return sortSeed;
	}

	public void setSortSeed(String sortSeed) {
		this.sortSeed = sortSeed;
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

}
