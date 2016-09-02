package com.sc.oa.view.action;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Document;
import com.sc.oa.domain.Notice;
import com.sc.oa.domain.User;
import com.sc.oa.util.QueryHelper;





@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction<Notice> {
	public String notlist(){
		Date dd=new Date();
		dd=timeformat(new Date());
		ActionContext.getContext().put("dxdd", dd);
		Object[] ob={dd};
		new QueryHelper(Notice.class, "n").addCondition(" ? >= n.uploadDate order by n.id desc", ob).preparePageBean(documentService, pageNum, pageSize);
		return "notlist";
	}
	public String menotlist(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		Date dd=new Date();
		dd=timeformat(new Date());
		ActionContext.getContext().put("dxdd", dd);
		Object[] ob={user};
		new QueryHelper(Notice.class, "n").addCondition(" n.user = ? order by n.id desc", ob).preparePageBean(documentService, pageNum, pageSize);
		return "menotlist";
	}
	public String addnot(){
		return "addnot";
	}
	public String tjnotice(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		model.setUser(user);
		model.setNewDate(new Date());
		model.setUploadDate(timeformat(model.getUploadDate()));
		model.setUploadDateEnd(timeformat(model.getUploadDateEnd()));
		noticeService.save(model);
		return "tomenotlist";
	}
	public String bcnotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long id=Long.parseLong(request.getParameter("id"));
		Notice oneNot=noticeService.getById(id);
		oneNot.setTitle(model.getTitle());
		oneNot.setContext(model.getContext());
		oneNot.setState(model.getState());
		oneNot.setNewDate(new Date());
		oneNot.setUploadDate(timeformat(model.getUploadDate()));
		oneNot.setUploadDateEnd(timeformat(model.getUploadDateEnd()));
		noticeService.update(oneNot);
		return "tomenotlist";
	}
	protected static Date timeformat(Date date) {
		String ss=new SimpleDateFormat("yyyy-MM-dd").format(date);
		return java.sql.Date.valueOf(ss);
	}
	public String editnot(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long id=Long.parseLong(request.getParameter("id"));
		Notice oneNot=noticeService.getById(id);
		ActionContext.getContext().put("gfs",timeformat(oneNot.getUploadDate()));
		ActionContext.getContext().put("gfd",timeformat(oneNot.getUploadDateEnd()));
		ActionContext.getContext().put("oneNot", oneNot);
		return "editnot";
	}
	
	
	
	public String delnot(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long id=Long.parseLong(request.getParameter("id"));
		noticeService.delete(id);
		return "tomenotlist";
	}

	
	
}



