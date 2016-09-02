package com.sc.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.QingjiaInfo;
import com.sc.oa.service.QingjiaInfoService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class QingjiaInfoServiceImp extends DaoSupportImpl<QingjiaInfo> implements QingjiaInfoService{
	public void save(QingjiaInfo qingjiaInfo) {
		// 保存
		super.save(qingjiaInfo);
	}
	public QingjiaInfo getById(Long id){
		return super.getById(id);
	}
	public void update(QingjiaInfo qingjiaInfo){
		super.update(qingjiaInfo);
	}

}
