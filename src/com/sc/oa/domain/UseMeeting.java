package com.sc.oa.domain;

import java.util.Date;

public class UseMeeting {
	
	private long id;
	private String meetName;
	private String userName;
	private int state=0;//0:未审核 1:审核通过2:审核未通过
	private Date applyDate=new Date();
	private Date startDate;
	private Date endDate;
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public String getMeetName() {
		return meetName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
}
