package com.sc.oa.view.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Privilege;
import com.sc.oa.domain.Role;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.domain.User;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<User> {

	public String index() throws Exception {
		return "index";
	}

	public String top() throws Exception {
		return "top";
	}

	public String bottom() throws Exception {
		return "bottom";
	}

	public String left() throws Exception {
		return "left";
	}

	public String right() throws Exception {
		
		User user=(User) ActionContext.getContext().getSession().get("user");

		boolean b1=user.hasPrivilegeByName("请假审核");
		boolean b2=user.hasPrivilegeByName("会议室审核");
		boolean b3=user.hasPrivilegeByName("出差审核");
		boolean b4=user.hasPrivilegeByName("用章预定管理");

		if(b1){
//			System.out.println("进了b1判断"+b1);
			int qingjianum=qingJiaService.getAll();
			ActionContext.getContext().put("qingjianum", qingjianum);
		}
		else{
//			System.out.println("进了b1判断"+b1+"false");
			int qingjianum=qingJiaService.getMine(user);	
			ActionContext.getContext().put("qingjianum", qingjianum);
		}
		
		if(b2){
//			System.out.println("进了b2判断"+b2);
			int meetingnum=meetingService.getAll();	
			ActionContext.getContext().put("meetingnum", meetingnum);
		}
		else{
			System.out.println("进了b2判断"+b2+"false");
			int meetingnum=meetingService.getMine(user);	
			ActionContext.getContext().put("meetingnum", meetingnum);
		}
		
		if(b3){
//			System.out.println("进了b1判断"+b1);
			int chuchainum=businessService.getAll();
			ActionContext.getContext().put("chuchainum", chuchainum);
		}
		else{
//			System.out.println("进了b1判断"+b1+"false");
			int chuchainum=businessService.getMine(user);
			ActionContext.getContext().put("chuchainum", chuchainum);
		}
		
		if(b4){
//			System.out.println("进了b1判断"+b1);
			int cachetnum=cachetService.getAll();
			ActionContext.getContext().put("cachetnum", cachetnum);
		}
		else{
//			System.out.println("进了b1判断"+b1+"false");
			int cachetnum=cachetService.getMine(user);
			ActionContext.getContext().put("cachetnum", cachetnum);
		}
		List noticelist=noticeService.getAll();
		ActionContext.getContext().put("noticelist", noticelist);
		
		List taglist=tagService.getSome(user);
		ActionContext.getContext().put("taglist", taglist);
		
		return "right";
	}
}
