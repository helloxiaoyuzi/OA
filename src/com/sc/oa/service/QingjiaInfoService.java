package com.sc.oa.service;

import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.QingjiaInfo;

public interface QingjiaInfoService extends DaoSupport<QingjiaInfo>{
	List<QingjiaInfo> findAll();
	
	void delete(Long id);

	void save(QingjiaInfo model);

	QingjiaInfo getById(Long id);

	void update(QingjiaInfo qingjiaInfo);
}
