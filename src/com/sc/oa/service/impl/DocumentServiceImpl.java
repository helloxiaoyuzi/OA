package com.sc.oa.service.impl;

 

 
 
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.Document;
import com.sc.oa.domain.User;
import com.sc.oa.service.DepartmentService;
import com.sc.oa.service.DocumentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DocumentServiceImpl extends DaoSupportImpl<Document> implements DocumentService {

	@Resource
	private SessionFactory sessionFactory;
	
	public List getUserdocus(User user) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Document d WHERE d.state=1 and d.user=?")
				.setParameter(0, user)//
				.list();
	}
	
	
	


}
