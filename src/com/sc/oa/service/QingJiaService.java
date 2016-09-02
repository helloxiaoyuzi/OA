package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Privilege;
import com.sc.oa.domain.QingjiaInfo;
import com.sc.oa.domain.User;

public interface QingJiaService extends DaoSupport<QingjiaInfo>{

	//获取所有待审批的请假申请
	public int getAll();
	
	//获取该用户待审批的请假申请
	public int getMine(User user);
}
