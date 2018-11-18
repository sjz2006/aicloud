package com.cn.spring.jpa.system.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.cn.spring.jpa.system.comm.PageInfo;
/**
 * 查询用户对象
 * @author 苏坚昭
 *
 */
public class QuerySystemUser extends PageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8811074381325987357L;
	
	/**
	 * 按用户登录用户名
	 */
	private String userName;
	/**
	 * 按用户登录密码
	 */
	private String password;
	/**
	 * 按用户全称
	 */
	private String fullName;
	/**
	 * 按用户启用状态
	 */
	private String enabled;
	/**
	 * 按机构名称
	 */
	private String unitName;
	/**
	 * 按开始时间
	 */
	private LocalDateTime beginDate;
	/**
	 * 按截止时间
	 */
	private LocalDateTime endDate;
	
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDateTime beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}
