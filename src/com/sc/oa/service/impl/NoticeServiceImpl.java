package com.sc.oa.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Notice;
import com.sc.oa.service.NoticeService;
@Service
@Transactional
public class NoticeServiceImpl extends DaoSupportImpl<Notice> implements NoticeService {

	public List getAll() {
		// TODO Auto-generated method stub
		Date date=timeformat(new Date());
		List<Notice> list= getSession().createQuery("from Notice where ? >=uploadDate and ? <=uploadDateEnd").setParameter(0, date).setParameter(1, date).list();	
		return list;
	}
	
	protected static Date timeformat(Date date) {
		String ss=new SimpleDateFormat("yyyy-MM-dd").format(date);
		return java.sql.Date.valueOf(ss);
	}

}
