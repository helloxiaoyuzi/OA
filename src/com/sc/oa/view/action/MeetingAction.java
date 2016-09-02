package com.sc.oa.view.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;
import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.PageBean;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.domain.User;
import com.sc.oa.service.MeetingroomInfoService;
import com.sc.oa.service.impl.MeetingroomInfoServiceImpl;
import com.sc.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


@Controller
@Scope("prototype")
public class MeetingAction extends BaseAction<MeetingroomInfo> {
	
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	
	}
	public String addUI() throws Exception {
		return "addUI";
	}
	public String applyUI() throws Exception {
		ActionContext.getContext().put("avaliableorderroomlist",meetingroomInfoService.getAvaliableOrdeRoom());
		return "applyUI";
	}
	public String add() throws Exception {	
		meetingroomInfoService.save(model);	
		return "tolist";
	}
	public String editUI() throws Exception {
		ActionContext.getContext().getValueStack().push(meetingroomInfoService.getById(model.getId()));
		return "editUI";
	}
	public String edit() throws Exception {
		meetingroomInfoService.update(model);
		return "tolist";
	}
	public String del() throws Exception {
		meetingroomInfoService.delete(model.getId());		
		return "tolist";
	}
	public String list() throws Exception {
		 List<MeetingroomInfo> rooms=meetingroomInfoService.findAll();
		ActionContext.getContext().put("roomlist", rooms);
		//System.out.println(rooms.get(0).getName());
		return "list";
	}
	public String search() throws Exception {
	//	meetingroomInfoService.
		return "search";
	}
	public String resetstate() {
		model=meetingroomInfoService.getById(model.getId());
		model.setState(0);
		meetingroomInfoService.update(model);
		return "tolist";
	}
	public String apply() throws Exception {
		String uname=getRequest().getParameter("userName");
		String mname=getRequest().getParameter("meetName");
		UseMeeting um=new UseMeeting();
		model.setName(mname);
		model=meetingroomInfoService.findByName(model);
		model.setState(1);
		meetingroomInfoService.update(model);
		um.setUserName(uname);
		um.setMeetName(mname);
		meetingroomInfoService.saveApply(um);	
		return "apply";
	}
	public String orderlist() throws Exception {
			ActionContext.getContext().put("orderapplylist",meetingroomInfoService.orderApplylist());
			ActionContext.getContext().put("noorderapplylist",meetingroomInfoService.noOrderApplylist());
			return "orderlist";
		}
	/**
	 * 取消预定（审核未通过）
	 * @return
	 */
	public String delApply() {
		String meetname=getRequest().getParameter("meetname");
		String[] str=meetname.split("-");
		System.out.println(meetname+"  "+str[0]);
		meetingroomInfoService.delApply(str);
		return "delApply";
		
	}
	/**
	 * 审核（通过）
	 * @return
	 */
	public String verify() {
		String ids=getRequest().getParameter("ids");
		String[] str=ids.split(",");
		System.out.println(ids+"  "+str[0]);
		meetingroomInfoService.verify(str);
		return "delApply";
		
	}
}
