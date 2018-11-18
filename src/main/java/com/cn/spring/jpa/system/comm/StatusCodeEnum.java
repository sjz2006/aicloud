package com.cn.spring.jpa.system.comm;

public enum StatusCodeEnum implements EnumDictable{
	
	成功(200), // 全局成功
	失败(300);  // 全局失败
	
	private Integer value;

	StatusCodeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.name();
	}

}
