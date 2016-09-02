package com.sc.oa.base;

import org.junit.Test;

import com.sc.oa.dao.RoleDao;
import com.sc.oa.dao.UserDao;
import com.sc.oa.dao.impl.RoleDaoImpl;
import com.sc.oa.dao.impl.UserDaoImpl;

public class BaseDaoTest {

	@Test
	public void testSave() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}

}
