package com.sc.oa.service;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.Role;
import com.sc.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 根据登录名与密码查询用户
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);
	
	int count(Long departmentId,Long roleId);
}
