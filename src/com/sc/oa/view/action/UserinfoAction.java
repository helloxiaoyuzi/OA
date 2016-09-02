package com.sc.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;

import com.sc.oa.domain.User;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserinfoAction extends BaseAction<User> {


	/** 修改页面 */
	public String list() throws Exception {
		return "update";
	}
	public String updateTJ() throws Exception {
		User user=(User) ActionContext.getContext().getSession().get("user");
		user.setName(model.getName());
		if(model.getGender().equals("0"))user.setGender("男");
		if(model.getGender().equals("1"))user.setGender("女");
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		userService.update(user);
		ActionContext.getContext().getSession().put("user",user);
		ActionContext.getContext().put("ff", "您修改的信息已保存！！");
		return "update";
	}
	public String xgpass() throws Exception {
		return "xgpass";
	}
	public String xgpassTJ() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String mm0=DigestUtils.md5Hex(request.getParameter("mm0"));
		String mm1=request.getParameter("mm1");
		String mm2=request.getParameter("mm2");
		User user=(User) ActionContext.getContext().getSession().get("user");
		int ff=0;
		if(mm0.equals(user.getPassword())){//111
			if(mm1!=null&&mm1.trim().length()>0&&mm1.equals(mm2)){//pp
				user.setPassword(DigestUtils.md5Hex(mm1));
				userService.update(user);
				ActionContext.getContext().getSession().put("user",user);
				ff=3;
			}else ff=2;
		}else ff=1;
		ActionContext.getContext().put("ff", ff);
		return "xgpass";
	}

	

}
