package com.sc.oa.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Forum;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.service.ForumService;
import com.sc.oa.service.MeetingroomInfoService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class MeetingroomInfoServiceImpl extends DaoSupportImpl<MeetingroomInfo> implements MeetingroomInfoService{
		public  List<MeetingroomInfo> getAvaliableOrdeRoom() {
			Query query=getSession().createQuery("from MeetingroomInfo where state = ?").setParameter(0, 0);
			return (query.list());
		}
		public  List<MeetingroomInfo> searchAvaliableOrdeRoom() {
			Query query=getSession().createQuery("from MeetingroomInfo where state = ? and start").setParameter(0, 0);
			return (query.list());
		}
		public void saveApply(UseMeeting um) {		
			getSession().save(um);
		}
		public  MeetingroomInfo findByName(MeetingroomInfo minfo) {		
			return (MeetingroomInfo) getSession().createQuery("from MeetingroomInfo where name = ?").setParameter(0, minfo.getName()).uniqueResult();
		}
		public List<UseMeeting> orderApplylist() {		
			return getSession().createQuery("from UseMeeting where state = 0  order by id desc").list();
		}
		public List<UseMeeting> noOrderApplylist() {		
			return getSession().createQuery("from UseMeeting where state <> 0  order by id desc").list();
		}
		public Integer delApply(String[] meetname) {		
			getSession().createSQLQuery("UPDATE  usemeeting SET state=2 WHERE meetName IN (:meetname)")
			.setParameterList("meetname", meetname).executeUpdate();
			return getSession().createSQLQuery("UPDATE  meetingroominfo SET state=0 WHERE name IN (:meetname)")
			.setParameterList("meetname", meetname).executeUpdate();
		}
		public Integer verify(String[] ids){
			return (Integer) getSession().createSQLQuery("UPDATE  usemeeting SET state=1 WHERE id IN (:ids)")
					.setParameterList("ids", ids).executeUpdate();
		}

}
