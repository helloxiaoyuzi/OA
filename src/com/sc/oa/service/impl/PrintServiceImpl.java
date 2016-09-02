package com.sc.oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.CachetInfo;
import com.sc.oa.domain.Forum;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.UseCachet;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.service.ForumService;
import com.sc.oa.service.MeetingroomInfoService;
import com.sc.oa.service.PrintService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrintServiceImpl extends DaoSupportImpl<CachetInfo> implements PrintService{
	public void save(UseCachet entity) {
		getSession().save(entity);
		
	}
	public List<UseCachet> orderApplylist() {		
		return getSession().createQuery("from UseCachet order by id desc").list();
	}
	public Integer backApply(String[] ids){
		return (Integer) getSession().createSQLQuery("UPDATE  itcast_usecachet SET state=2 WHERE id IN (:ids)")
				.setParameterList("ids", ids).executeUpdate();
	}
	public Integer verify(String[] ids){
		return (Integer) getSession().createSQLQuery("UPDATE  itcast_usecachet SET state=1 WHERE id IN (:ids)")
				.setParameterList("ids", ids).executeUpdate();
	}
	
}
