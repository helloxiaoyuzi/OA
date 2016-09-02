package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.domain.User;


public interface MeetingService extends  DaoSupport<UseMeeting>{

	//获取所有待审批的会议室申请
	public int getAll();
	
	//获取该用户的待审批的会议室申请
	public int getMine(User user);
}
