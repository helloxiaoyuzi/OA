package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.CachetInfo;
import com.sc.oa.domain.Forum;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.UseCachet;
import com.sc.oa.domain.UseMeeting;

public interface PrintService extends DaoSupport<CachetInfo>{
	public void save(UseCachet entity);
	public List<UseCachet> orderApplylist() ;
	public Integer backApply(String[] ids);
	public Integer verify(String[] ids);
}
