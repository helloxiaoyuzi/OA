package com.sc.oa.service;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.CachetInfo;
import com.sc.oa.domain.UseCachet;
import com.sc.oa.domain.User;

public interface CachetService extends DaoSupport<UseCachet>{

	public int getMine(User user);
	
	public int getAll();
}
