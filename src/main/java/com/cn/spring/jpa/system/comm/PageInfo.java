package com.cn.spring.jpa.system.comm;

import java.io.Serializable;
/**
 * 查询翻页
 * @author 苏坚昭
 *
 */
public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1041133996889105526L;
	/**
	 * 第几页 从0开始
	 */
	private int page;
	/**
	 * 一页几条
	 */
	private int	size;
	/**
	 * 排序   倒序:DESC 升序:ASC
	 */
	private String direction;
	/**
	 * 排序字段
	 */
	private String dirname;
	
	public enum DirectionEnum implements EnumDictable {
		倒序("DESC"),
		升序("ASC");

		private String value;

		DirectionEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.name();
		}
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirname() {
		return dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}
}
