package com.cn.spring.jpa.system.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户与机构结果
 * @author 苏坚昭
 *
 */
public class SystemUserOrgunitVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8791657107941743463L;
	/**
	 * 用户id
	 */
	private Long userId;
	 /**
	  * 用户名
	  */
	private String userName;
	 /**
	  * 用户全称
	  */
	private String fullName;
	 /**
	  * 用户启用状态
	  */
	private String enabled;
	 /**
	  * 用户所在的机构id
	  */
	private Long orgunitId;
	 /**
	  * 用户所在的机构名称
	  */
	private String unitName;
	 /**
	  * 用户创建时间
	  * @return
	  */
	private LocalDateTime createdDate;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getOrgunitId() {
		return orgunitId;
	}

	public void setOrgunitId(Long orgunitId) {
		this.orgunitId = orgunitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}
