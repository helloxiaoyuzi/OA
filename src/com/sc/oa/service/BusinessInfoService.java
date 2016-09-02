package com.sc.oa.service;
import java.util.List;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.BusinessInfo;
public interface BusinessInfoService extends DaoSupport<BusinessInfo>{
	List<BusinessInfo> findAll();
	void save(BusinessInfo model);

	BusinessInfo getById(Long id);

	void update(BusinessInfo businessInfo);
}
