package com.sc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.CachetInfo;
import com.sc.oa.domain.UseCachet;
import com.sc.oa.domain.User;
import com.sc.oa.service.CachetService;
@Service
@Transactional
public class CachetServiceImpl extends DaoSupportImpl<UseCachet> implements CachetService{

	public int getMine(User user) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from UseCachet where appuserId=? and state=0").setParameter(0, user).list().size();
	}

	public int getAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from UseCachet where  state=0").list().size();
	}
}
