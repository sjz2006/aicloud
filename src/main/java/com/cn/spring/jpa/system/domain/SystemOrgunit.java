package com.cn.spring.jpa.system.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cn.spring.jpa.system.comm.EnumDictable;
/**
 * 机构表
 * @author 苏坚昭
 *
 */
@Entity
@Table(name="system_orgunit")
public class SystemOrgunit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6388179069260884334L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * 机构名称
	 */
	@Column(name = "unitName", unique = true, nullable = false)
	private String unitName;
	/**
	 * 父部门ID
	 */
	@Column(name = "parentId")
	private Long parentId;
	/**
	 * 机构编码
	 */
	@Column(name = "orgUnitCode")
	private String orgUnitCode;
	
	/**
	 * 组织类型
	 */
	@Column(name = "unitType")
	private String unitType;
	
	/**
	 * 是否启用
	 */
	@Column(name = "enabled")
	private String enabled;
	
	/**
	 *创建时间
	 */
	@Column(name = "createdDate")
	private LocalDateTime createdDate;
	/**
	 *修改时间
	 */
	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	/**
	 * 机构的uuid
	 */
	@Column(name="uuid")
	private String uuid;
	
	public enum UnitTypeEnum implements EnumDictable {
		公司("公司"),
		分公司("分公司"),
		门店("门店");

		private String value;

		UnitTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}
	
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
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
