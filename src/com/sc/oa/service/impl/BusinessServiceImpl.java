package com.sc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.BusinessInfo;
import com.sc.oa.domain.User;
import com.sc.oa.service.BusinessService;
@Service
@Transactional
public class BusinessServiceImpl extends DaoSupportImpl<BusinessInfo> implements BusinessService{

	public int getMine(User user) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from BusinessInfo where appuser=? and state='未审核'").setParameter(0, user).list().size();
	}

	public int getAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from BusinessInfo where  state='未审核'").list().size();
	}

}
