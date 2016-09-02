package com.sc.oa.service.impl;



import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.User;
import com.sc.oa.service.UserService;


@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	public User findByLoginNameAndPassword(String loginName, String password) {
		// 使用密码的MD5摘要进行对比
		String md5Digest = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Digest)//
				.uniqueResult();
	}
	
	public int count(Long roleId,Long departmentId){
		String sql="SELECT * FROM USER u WHERE u.departmentId=? AND u.id IN (SELECT ur.userId FROM user_role ur WHERE ur.roleId=?)";
		Query query=getSession().createSQLQuery(sql).setParameter(0, departmentId).setParameter(1, roleId);
		int num=query.list().size();
		return num;
	}
}
