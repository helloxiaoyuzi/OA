package com.sc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.BusinessInfo;
import com.sc.oa.service.BusinessInfoService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class BusinessInfoServiceImp extends DaoSupportImpl<BusinessInfo> implements BusinessInfoService{
	public void save(BusinessInfo businessInfo) {
		// 保存
		super.save(businessInfo);
	}
	public BusinessInfo getById(Long id){
		return super.getById(id);
	}
	public void update(BusinessInfo businessInfo){
		super.update(businessInfo);
	}
}
