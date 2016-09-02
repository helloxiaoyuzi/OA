package com.sc.oa.service;

 
 

import java.util.List;

 
 
import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Notice;
 

 

public interface NoticeService  extends DaoSupport<Notice>{

	public List getAll();
}
