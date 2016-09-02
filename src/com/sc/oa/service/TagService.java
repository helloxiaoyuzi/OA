package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Tag;
import com.sc.oa.domain.User;

public interface TagService extends DaoSupport<Tag> {

	public List getMine(User user);
	
	public List getSome(User user);
	
}
