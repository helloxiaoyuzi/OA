package com.sc.oa.domain;

import java.util.Date;

public class Tag implements java.io.Serializable {

	private Long id;
	private String title;
	private String content;
	private Date newDate;
	private User appuser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public User getAppuser() {
		return appuser;
	}

	public void setAppuser(User appuser) {
		this.appuser = appuser;
	}

}
