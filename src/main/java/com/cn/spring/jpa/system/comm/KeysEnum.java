package com.cn.spring.jpa.system.comm;

public enum KeysEnum implements EnumDictable{
	
	statusCode("statusCode"),
	message("message");

	private String value;

	KeysEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.name();
	}

}
