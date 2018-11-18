package com.cn.spring;

import com.cn.spring.jpa.system.comm.EnumDictable;

public class UUIDEnum {
	
	public enum UuidEnum implements EnumDictable {
		机构("SYSORG-"),
		角色("SYSROLE-"),
		用户("SYSUSER-");

		private String value;

		UuidEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}

}
