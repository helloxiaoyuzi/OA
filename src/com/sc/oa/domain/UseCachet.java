package com.sc.oa.domain;

import java.util.Date;

public class UseCachet {

	private Long id;// 编号
	private CachetInfo cachetId;// 被申请的公章对象
	private User appuserId;// 申请人的对象
	private Department appdepId;// 申请人所在部门
	private Date appDate;// 申请时间
	private String content;// 申请原因
	private int state;// 申请状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CachetInfo getCachetId() {
		return cachetId;
	}

	public void setCachetId(CachetInfo cachetId) {
		this.cachetId = cachetId;
	}

	public User getAppuserId() {
		return appuserId;
	}

	public void setAppuserId(User appuserId) {
		this.appuserId = appuserId;
	}

	public Department getAppdepId() {
		return appdepId;
	}

	public void setAppdepId(Department appdepId) {
		this.appdepId = appdepId;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
