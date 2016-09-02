package com.sc.oa.service;

 

import java.util.List;

 
import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Document;
import com.sc.oa.domain.User;

 

public interface DocumentService  extends DaoSupport<Document>{

	public List getUserdocus(User user);

}
