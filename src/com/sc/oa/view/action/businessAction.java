package com.sc.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.BusinessInfo;
import com.sc.oa.domain.QingjiaInfo;
import com.sc.oa.domain.User;
import com.sc.oa.service.BusinessInfoService;
import com.sc.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class businessAction extends BaseAction<BusinessInfo>{
	private String appName;
	private String checkState;
	public String list(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		Boolean privilege=user.hasPrivilegeByName("出差审核");
		if(privilege){    //有权限浏览全部请假信息
			//查询所有数据并分页
			new QueryHelper(BusinessInfo.class, "b").addOrderProperty("b.appDate", false).preparePageBean(businessInfoService, pageNum, pageSize);
			return "list";
		}else{           //没权限进人请假申请
			return "addUI";
		}
	}
	public String select(){
		System.out.println(appName+"  "+checkState);
		new QueryHelper(BusinessInfo.class, "bu").addCondition("bu.appuser.name like ?", "%"+appName+"%").addCondition("bu.state like ?", "%"+checkState+"%").preparePageBean(qingjiaInfoService, pageNum, pageSize);
		return "list";
	}
	public String addUI(){
		return "addUI";
	}
	public String add(){
		Long appuserId=Long.parseLong(ServletActionContext.getRequest().getParameter("uid"));
		Date startDate=model.getStartTime();
		Date endDate=model.getEndTime();
		if(endDate.before(startDate)){
			addFieldError("dateError", "结束时间不能早于开始时间");
			return "addUI";
		}else{
			User user = userService.getById(appuserId);
			model.setAppuser(user);
			model.setAppDate(new Date());
			model.setState("未审核");
			businessInfoService.save(model);
			return "toList";
		}
	}
	public String check(){
		// 准备回显的数据
		BusinessInfo businessInfo = businessInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(businessInfo);
		return "check";
	}
	public String scan(){
		// 准备回显的数据
		BusinessInfo businessInfo = businessInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(businessInfo);
		return "scan";
	}
	public String edit(){
		Long checkuserId=Long.parseLong(ServletActionContext.getRequest().getParameter("uid"));
		User checkuser = userService.getById(checkuserId);
		BusinessInfo businessInfo = businessInfoService.getById(model.getId());
		
		businessInfo.setState(model.getState());
		businessInfo.setCheckDate(new Date());
		businessInfo.setCheckuser(checkuser);
		businessInfoService.update(businessInfo);
		return "toList2";
	}
	public String appuserlist(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		new QueryHelper(BusinessInfo.class, "bone").addCondition("bone.appuser=?", user).addOrderProperty("bone.appDate", false).preparePageBean(businessInfoService, pageNum, pageSize);
		return "appuserlist";
	}
	public String update(){
		// 准备回显的数据
		BusinessInfo businessInfo = businessInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(businessInfo);
		return "update";
	}
	//保存用户的修改
	public String saveUpdate(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		BusinessInfo businessInfo=businessInfoService.getById(model.getId());
		businessInfo.setAppuser(user);
		businessInfo.setStartTime(model.getStartTime());
		businessInfo.setEndTime(model.getEndTime());
		businessInfo.setAddress(model.getAddress());
		businessInfo.setVehicle(model.getVehicle());
		businessInfo.setContent(model.getContent());
		businessInfo.setTask(model.getTask());
		businessInfo.setMoney(model.getMoney());
		businessInfo.setAppDate(new Date());
		businessInfoService.update(businessInfo);
		return "toList";
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	
}
