package com.sc.oa.domain;

import java.util.Date;

public class MeetingroomInfo {
	private long id;
	private String name;
	private String desc;
	private int state;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

}
