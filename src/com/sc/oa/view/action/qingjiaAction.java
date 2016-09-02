package com.sc.oa.view.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.QingjiaInfo;
import com.sc.oa.domain.Role;
import com.sc.oa.domain.User;
import com.sc.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class qingjiaAction extends BaseAction<QingjiaInfo> {
	private String appName;
	private String checkState;
	private String sedepartmentId;
	private String seroleId;

	public String list() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user.hasPrivilegeByUrl("/user_list")) {
			// 准备数据，departmentList
			List<Department> departmentList = departmentService.findAll();
			ActionContext.getContext().put("departmentList", departmentList);

			// 准备数据, roleList
			List<Role> roleList = roleService.findAll();
			ActionContext.getContext().put("roleList", roleList);

			new QueryHelper(QingjiaInfo.class, "qi").addOrderProperty(
					"qi.appDate", false).preparePageBean(qingjiaInfoService,
					pageNum, pageSize);
			ActionContext.getContext().put("topic", "全部假条");
			return "list2";
		} else {
			new QueryHelper(QingjiaInfo.class, "qi")
					.addCondition("qi.appuser.department = ?",
							user.getDepartment())
					.addOrderProperty("qi.appDate", false)
					.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			ActionContext.getContext().put("topic",
					user.getDepartment().getName());
			return "list1";
		}

	}

	public String apply() {
		return "apply";
	}

	public String select() throws Exception {
		System.out.println(appName + "  " + checkState);
		User user = (User) ActionContext.getContext().getSession().get("user");
		HttpServletRequest request=ServletActionContext.getRequest();
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String hql="";
		List<Date> ob=new ArrayList<Date>();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if("".equals(startTime)&&"".equals(endTime)){
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appuser.department = ?", user.getDepartment())
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list1";
		}
		if(startTime!=""&&"".equals(endTime)){
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appDate >= ?", sim.parse(startTime))
			.addCondition("qi.appuser.department = ?", user.getDepartment())
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list1";
		}
		if("".equals(startTime)&&endTime!=""){
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appDate <= ?", sim.parse(endTime))
			.addCondition("qi.appuser.department = ?", user.getDepartment())
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list1";
		}
		if(startTime!=""&&endTime!=""){
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appDate <= ? and qi.appDate <= ?", sim.parse(startTime),sim.parse(endTime))
			.addCondition("qi.appuser.department = ?", user.getDepartment())
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list1";
		}
		ActionContext.getContext().put("topic", user.getDepartment().getName());
		return "list1";
	}

	public String select2() {
		// 准备数据，departmentList
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		ActionContext.getContext().put("topic", "全部假条");
		
		Object[] ob = { "%" + appName + "%", "%" + checkState + "%" };
		String hql = " qi.appuser.name like ? and qi.state like ? ";
		if ("".equals(sedepartmentId)&&"".equals(seroleId)) {
			System.out.println("都为空");
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list2";
		}
		if (sedepartmentId != "" &&"".equals(seroleId)) {
			Department department = departmentService.getById(Long.parseLong(sedepartmentId));
			new QueryHelper(QingjiaInfo.class, "qi")
			.addCondition("qi.appuser.department like ?",department)
			.addCondition("qi.appuser.name like ?", "%" + appName + "%")
			.addCondition("qi.state like ?", "%" + checkState + "%")
			.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list2";
		}
		if ("".equals(sedepartmentId) && seroleId != "") {
			Role role = roleService.getById(Long.parseLong(seroleId));
			Set<User> users = role.getUsers();
			Object[] o = new Object[users.size()];
			List<Long> list = new ArrayList<Long>();
			String s = "";
			for (User u : users) {
				list.add(u.getId());
				s = s + "?,";
			}
			s = s.substring(0, s.length() - 1);
			o = list.toArray(o);
			String hql2 = " qi.appuser.id in (" + s + ")";
			new QueryHelper(QingjiaInfo.class, "qi")
					.addCondition(hql2, o)
					.addCondition(hql,ob)
					.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list2";
		}
		if (sedepartmentId != "" && seroleId != "") {
			Department department = departmentService.getById(Long.parseLong(sedepartmentId));
			Role role = roleService.getById(Long.parseLong(seroleId));
			Set<User> users = role.getUsers();
			Object[] o = new Object[users.size()];
			List<Long> list = new ArrayList<Long>();
			String s = "";
			for (User u : users) {
				list.add(u.getId());
				s = s + "?,";
			}
			s = s.substring(0, s.length() - 1);
			o = list.toArray(o);
			String hql2 = " qi.appuser.id in (" + s + ")";
			new QueryHelper(QingjiaInfo.class, "qi")
					.addCondition(hql2, o)
					.addCondition(hql,ob)
					.addCondition("qi.appuser.department like ?",department)
					.preparePageBean(qingjiaInfoService, pageNum, pageSize);
			return "list2";
		}
		return "list2";
	}

//	public String addUI() {
//		return "addUI";
//	}

//	public String add() {
//		Long appuserId = Long.parseLong(ServletActionContext.getRequest()
//				.getParameter("uid"));
//		Date startDate = model.getStartTime();
//		Date endDate = model.getEndTime();
//		if (endDate.before(startDate)) {
//			addFieldError("dateError", "结束时间不能早于开始时间");
//			return "apply";
//		} else {
//			User user = userService.getById(appuserId);
//			model.setAppuser(user);
//			model.setAppDate(new Date());
//			model.setState("未审核");
//			qingjiaInfoService.save(model);
//			return "toList";
//		}
//	}

	public String check() {
		// 准备回显的数据
		QingjiaInfo qingjiaInfo = qingjiaInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(qingjiaInfo);
		return "check";
	}

	public String scan() {
		// 准备回显的数据
		QingjiaInfo qingjiaInfo = qingjiaInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(qingjiaInfo);
		return "scan";
	}

	public String edit() {
		Long checkuserId = Long.parseLong(ServletActionContext.getRequest()
				.getParameter("uid"));
		User checkuser = userService.getById(checkuserId);
		QingjiaInfo qingjiaInfo = qingjiaInfoService.getById(model.getId());
		qingjiaInfo.setState(model.getState());
		qingjiaInfo.setCheckDate(new Date());
		qingjiaInfo.setCheckuser(checkuser);
		qingjiaInfoService.update(qingjiaInfo);
		return "toList2";
	}

	public String appuserlist() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		new QueryHelper(QingjiaInfo.class, "uone")
				.addCondition("uone.appuser=?", user)
				.addOrderProperty("uone.appDate", false)
				.preparePageBean(qingjiaInfoService, pageNum, pageSize);
		return "appuserlist";
	}

	public String update() {
		// 准备回显的数据
		QingjiaInfo qingjiaInfo = qingjiaInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(qingjiaInfo);
		return "update";
	}

	public String save() {
		Date startDate = model.getStartTime();
		Date endDate = model.getEndTime();
		if (endDate.before(startDate)) {
			addFieldError("dateError", "结束时间不能早于开始时间");
			return "update";
		} else {
			User user = (User) ActionContext.getContext().getSession()
					.get("user");
			QingjiaInfo qingjiaInfo = qingjiaInfoService.getById(model.getId());
			qingjiaInfo.setContent(model.getContent());
			qingjiaInfo.setStartTime(model.getStartTime());
			qingjiaInfo.setEndTime(model.getEndTime());
			qingjiaInfo.setAppDate(new Date());
			qingjiaInfo.setAppuser(user);
			qingjiaInfoService.update(qingjiaInfo);
			return "toList";
		}
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

	public String getSedepartmentId() {
		return sedepartmentId;
	}

	public void setSedepartmentId(String sedepartmentId) {
		this.sedepartmentId = sedepartmentId;
	}

	public String getSeroleId() {
		return seroleId;
	}

	public void setSeroleId(String seroleId) {
		this.seroleId = seroleId;
	}

}
