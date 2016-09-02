package com.sc.oa.domain;

/**
 * 回复
 * 
 * @author
 */
public class Reply extends Article {
	private Topic topic; // 所属的主题
	private Long del;


	public Long getDel() {
		return del;
	}

	public void setDel(Long del) {
		this.del = del;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
