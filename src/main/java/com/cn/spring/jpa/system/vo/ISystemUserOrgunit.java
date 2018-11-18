package com.cn.spring.jpa.system.vo;

import java.time.LocalDateTime;

/**
 * 用户与机构结果集的接口类
 * @author 苏坚昭
 *
 */
public interface ISystemUserOrgunit {
	/**
	 * 用户id
	 * @return
	 */
	 Long getId();
	 /**
	  * 用户名
	  * @return
	  */
	 String getUserName();
	 /**
	  * 用户全称
	  * @return
	  */
	 String getFullName();
	 /**
	  * 用户启用状态
	  * @return
	  */
	 String getEnabled();
	 /**
	  * 用户所在的机构id
	  * @return
	  */
	 Long getOrgunitId();
	 /**
	  * 用户所在的机构名称
	  * @return
	  */
	 String getUnitName();
	 /**
	  * 用户创建时间
	  * @return
	  */
	 LocalDateTime getCreatedDate();
}
