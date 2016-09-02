package com.sc.oa.service;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.BusinessInfo;
import com.sc.oa.domain.User;

public interface BusinessService extends DaoSupport<BusinessInfo>{

	public int getMine(User user);
	
	public int getAll();
}
