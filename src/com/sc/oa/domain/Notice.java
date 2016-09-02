package com.sc.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * 
 * @author tyg
 * 
 */
public class Notice implements java.io.Serializable {
	private Long id;
	private String title;
	private String context;
	private Date uploadDate;
	private Date uploadDateEnd;
	private Date newDate;
	private int state;
	private User user;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
	
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Date getUploadDateEnd() {
		return uploadDateEnd;
	}
	public void setUploadDateEnd(Date uploadDateEnd) {
		this.uploadDateEnd = uploadDateEnd;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
