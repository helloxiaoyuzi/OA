package com.sc.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Privilege;
import com.sc.oa.domain.QingjiaInfo;
import com.sc.oa.domain.User;
import com.sc.oa.service.QingJiaService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class QingJiaServiceImpl extends DaoSupportImpl<QingjiaInfo> implements QingJiaService{


	public int getAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from QingjiaInfo where state='未审核'").list().size();
		
	}

	public int getMine(User user){
		return getSession().createQuery("From QingjiaInfo where appuser=? and state='未审核'")
				.setParameter(0, user).list().size();
	}


}
