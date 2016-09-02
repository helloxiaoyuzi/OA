package com.sc.oa.view.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sc.oa.domain.CachetInfo;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.MeetingroomInfo;
import com.sc.oa.domain.PageBean;
import com.sc.oa.domain.UseCachet;
import com.sc.oa.domain.UseMeeting;
import com.sc.oa.domain.User;
import com.sc.oa.service.MeetingroomInfoService;
import com.sc.oa.service.impl.MeetingroomInfoServiceImpl;
import com.sc.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

@SuppressWarnings("deprecation")
@Controller
@Scope("prototype")
public class PrintAction extends BaseAction<CachetInfo> {
	
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	
	}
	public String addUI() throws Exception {
		return "addUI";
	}
	
	public String add() throws Exception {	
		printService.save(model);	
		return "tolist";
	}
	public String backApply() {
		String ids=getRequest().getParameter("ids");
		String[] str=ids.split(",");
		System.out.println(ids+"  "+str[0]);
		printService.backApply(str);
		return "backApply";
		
	}
	public String verify() {
		String ids=getRequest().getParameter("ids");
		String[] str=ids.split(",");
		System.out.println(ids+"  "+str[0]);
		printService.verify(str);
		return "backApply";
		
	}
	public String orderlist() throws Exception {	
		ActionContext.getContext().put("orderapplylist",printService.orderApplylist());
		return "orderlist";
	}
	public String applyUI() throws Exception {
		List<CachetInfo> cachetlist= printService.findAll();
		ActionContext.getContext().put("cachetlist",cachetlist);
		return "applyUI";
	}
	public String list() throws Exception {
		List<CachetInfo> cachetlist= printService.findAll();
		ActionContext.getContext().put("printlist",cachetlist);
		return "list";
	}
	public String editUI() throws Exception {
		ActionContext.getContext().getValueStack().push(printService.getById(model.getId()));
		return "editUI";
	}
	public String edit() throws Exception {
		printService.update(model);
		return "tolist";
	}
	public String del() throws Exception {
		printService.delete(model.getId());		
		return "tolist";
	}
	public String apply() throws Exception {
		String state=getRequest().getParameter("state");
		String appuserId=getRequest().getParameter("appuserId");
		String appdepId=getRequest().getParameter("appdepId");
		String content=getRequest().getParameter("content");
		String appDate=getRequest().getParameter("appDate");
		String cachetId=getRequest().getParameter("cachetId");  
		System.out.println(appDate+" "+cachetId);
		UseCachet uc=new UseCachet();
		CachetInfo ci=new CachetInfo();
		ci.setId(Long.valueOf(cachetId));
		User u=new User();
		u.setId(Long.valueOf(appuserId));
		Department d=new Department();
		d.setId(Long.valueOf(appdepId));
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		uc.setAppDate(sd.parse(appDate));
		uc.setAppdepId(d);
		uc.setAppuserId(u);
		uc.setCachetId(ci);
		uc.setState(Integer.parseInt(state));
		uc.setContent(content);
		printService.save(uc);
		return "apply";
	}
}
