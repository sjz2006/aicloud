package com.cn.spring.jpa.system.comm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource(value= {"classpath:system.yml"},ignoreResourceNotFound = true,encoding = "utf-8")
@ConfigurationProperties
public class SystemConfig {
	
	private String err001;
	
	private String err002;
	
	private String err003;
	
	private String err004;
	
	private String err005;
	
	private String err006;
	
	private String err007;

	public String getErr001() {
		return err001;
	}

	public void setErr001(String err001) {
		this.err001 = err001;
	}
	
	public String getErr002() {
		return err002;
	}

	public void setErr002(String err002) {
		this.err001 = err002;
	}
	
	public String getErr003() {
		return err003;
	}

	public void setErr003(String err003) {
		this.err003 = err003;
	}

	public String getErr004() {
		return err004;
	}

	public void setErr004(String err004) {
		this.err004 = err004;
	}

	public String getErr005() {
		return err005;
	}

	public void setErr005(String err005) {
		this.err005 = err005;
	}

	public String getErr006() {
		return err006;
	}

	public void setErr006(String err006) {
		this.err006 = err006;
	}

	public String getErr007() {
		return err007;
	}

	public void setErr007(String err007) {
		this.err007 = err007;
	}
	
	
	
}
