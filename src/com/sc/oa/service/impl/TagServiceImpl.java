package com.sc.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Tag;
import com.sc.oa.domain.User;
import com.sc.oa.service.TagService;

@Service
@Transactional
public class TagServiceImpl extends DaoSupportImpl<Tag> implements TagService{

	public List getMine(User user){
		
		return getSession().createQuery("from Tag where appuser=? order by newDate desc ").setParameter(0, user).list();
	}

	public List getSome(User user) {
		// TODO Auto-generated method stub
		List list= getSession().createQuery("from Tag where appuser=? order by newDate desc ").setParameter(0, user).list();
		List<Tag> list2=new ArrayList();
		if(list.size()>4){
			for(int i=0;i<5;i++){
				list2.add((Tag) list.get(i));
			}
		}
		else{
			for(int i=0;i<list.size();i++){
				list2.add((Tag) list.get(i));
			}
		}
		return list2;
	}
}
