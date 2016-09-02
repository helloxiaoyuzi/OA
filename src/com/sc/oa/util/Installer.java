package com.sc.oa.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.domain.Privilege;
import com.sc.oa.domain.User;

@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 执行安装
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ==============================================================
		// 保存超级管理员用户
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user); // 保存

		// ==============================================================
		// 保存权限数据
		Privilege menu, menu1, menu2, menu3, menu4, menu5,menu6;

		// --------------------
		menu = new Privilege("系统管理", null, null);
		menu1 = new Privilege("用户管理", "/user_list", menu);
		menu2 = new Privilege("角色管理", "/role_list", menu);
		menu3 = new Privilege("部门管理", "/department_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		session.save(new Privilege("用户列表", "/user_list", menu1));
		session.save(new Privilege("用户删除", "/user_delete", menu1));
		session.save(new Privilege("用户添加", "/user_add", menu1));
		session.save(new Privilege("用户修改", "/user_edit", menu1));
		session.save(new Privilege("初始化密码", "/user_initPassword", menu1));

		session.save(new Privilege("角色列表", "/role_list", menu2));
		session.save(new Privilege("角色删除", "/role_delete", menu2));
		session.save(new Privilege("角色添加", "/role_add", menu2));
		session.save(new Privilege("角色修改", "/role_edit", menu2));

		session.save(new Privilege("部门列表", "/department_list", menu3));
		session.save(new Privilege("部门删除", "/department_delete", menu3));
		session.save(new Privilege("部门添加", "/department_add", menu3));
		session.save(new Privilege("部门修改", "/department_edit", menu3));



		// --------------------
		menu = new Privilege("网上交流", null, null);
		menu1 = new Privilege("论坛管理", "/forumManage_list", menu);
		menu2 = new Privilege("论坛", "/forum_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		// --------------------

		menu = new Privilege("人事管理", null, null);
		menu1 = new Privilege("请假管理", "/qingjia_list", menu);
		menu2 = new Privilege("出差管理", "/business_list", menu);
		menu3 = new Privilege("请假申请", "/qingjia_apply", menu);
		menu4 = new Privilege("出差申请", "/business_apply", menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
	
		
		
		// --------------------
		menu = new Privilege("综合行政", null, null);
		menu1 = new Privilege("会议室管理", "/meetingroom_list", menu);
		menu2 = new Privilege("用章管理", "/print_list", menu);
		menu3 = new Privilege("会议室申请", "/meetingroom_applyUI", menu);
		menu4 = new Privilege("用章申请", "/print_applyUI", menu);
		menu5 = new Privilege("会议室预定管理", "/meetingroom_orderlist", menu);
		menu6 = new Privilege("用章预定管理", "/print_orderlist", menu);
			
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);
		
	
		
		// --------------------
		menu = new Privilege("文档管理", null, null);
		menu1=new Privilege("文档列表", "/document_alldocu", menu);
		menu2=new Privilege("个人文档", "/document_medocu", menu);
		menu3=new Privilege("文档回收站", "/document_resavedocu", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		menu = new Privilege("公告管理", null, null);
		menu1 = new Privilege("公告列表", "/notice_notlist", menu);
		menu2 = new Privilege("发布公告", "/notice_menotlist", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
//		menu = new Privilege("内容管理", null, null);
//		menu1 = new Privilege("公告管理", "/notice_list", menu);
//		menu2 = new Privilege("文档管理", "/document_list", menu);
//			
//		session.save(menu);
//		session.save(menu1);
//		session.save(menu2);
//		
//		
//		session.save(new Privilege("公告列表", "/notice_list", menu1));
//		session.save(new Privilege("公告删除", "/notice_del", menu1));
//		session.save(new Privilege("公告修改", "/notice_update", menu1));	
//		session.save(new Privilege("公告添加", "/notice_add", menu1));
//		
//		session.save(new Privilege("文档列表", "/document_list", menu2));
//		session.save(new Privilege("文档删除", "/document_del", menu2));
//		session.save(new Privilege("文档上传", "/document_upload", menu2));
//		session.save(new Privilege("文档修改", "/document_update", menu2));
//		
		
		// --------------------
		menu = new Privilege("我的桌面", null, null);
		menu1 = new Privilege("个人信息管理", "/userinfo_list", menu);
		menu2 = new Privilege("任务管理", "/task_list", menu);
		menu3 = new Privilege("便签管理", "/tag_list", menu);
			
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		

		
		session.save(new Privilege("个人信息列表", "/userinfo_list", menu1));
		session.save(new Privilege("个人信息修改", "/userinfo_update", menu1));
		
		session.save(new Privilege("任务列表", "/task_list", menu2));
		
		session.save(new Privilege("便签列表", "/tag_list", menu3));
		session.save(new Privilege("便签删除", "/tag_del", menu3));
		session.save(new Privilege("便签修改", "/tag_update", menu3));
		session.save(new Privilege("便签添加", "/tag_add", menu3));
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
