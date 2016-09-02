package com.sc.oa.domain;

import java.util.Date;

public class QingjiaInfo implements java.io.Serializable {

	private Long id;
	private String content;
	private Date startTime;
	private Date endTime;
	private Date appDate;
	private User appuser;
	private String state;
	private Date checkDate;
	private User checkuser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public User getAppuser() {
		return appuser;
	}

	public void setAppuser(User appuser) {
		this.appuser = appuser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public User getCheckuser() {
		return checkuser;
	}

	public void setCheckuser(User checkuser) {
		this.checkuser = checkuser;
	}

}
