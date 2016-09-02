package com.sc.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.domain.User;
import com.sc.oa.service.MeetingService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class MeetingServiceImpl extends DaoSupportImpl<UseMeeting> implements MeetingService{

	public int getAll() {
		
		return getSession().createQuery("from UseMeeting where  state=0").list().size();
		
	}

	public int getMine(User user) {
	
		return getSession().createQuery("from UseMeeting where userName=? and state=0")
				.setParameter(0, user.getName()).list().size();
		
	}

}
