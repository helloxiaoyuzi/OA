package com.sc.oa.domain;

public class CachetInfo implements java.io.Serializable {

	private Long id;// 公章ID编号
	private String name;// 公章名称
	private String description;// 公章描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
