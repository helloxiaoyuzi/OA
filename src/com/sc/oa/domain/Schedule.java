package com.sc.oa.domain;

import java.util.Date;

public class Schedule {

	private int id;
	private String context;
	private User appuser;
	private Date date;
	private int state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public User getAppuser() {
		return appuser;
	}
	public void setAppuser(User appuser) {
		this.appuser = appuser;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
