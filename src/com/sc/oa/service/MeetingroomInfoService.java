package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Forum;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.UseMeeting;

public interface MeetingroomInfoService extends DaoSupport<MeetingroomInfo>{

	public  List<MeetingroomInfo> getAvaliableOrdeRoom();
	public void saveApply(UseMeeting um);
	public  MeetingroomInfo findByName(MeetingroomInfo minfo);
	public List<UseMeeting> orderApplylist();
	public Integer delApply(String[] meetname);
	public Integer verify(String[] ids);
	public List<UseMeeting> noOrderApplylist();

}
