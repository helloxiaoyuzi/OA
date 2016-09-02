package com.sc.oa.view.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.Role;
import com.sc.oa.domain.User;
import com.sc.oa.service.DepartmentService;
import com.sc.oa.util.DepartmentUtils;
import com.sc.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;
	private User user;
	public String importUser() throws Exception{
		//准备数据，departmentList
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "import";
		
	}
	/** 列表 */
	public String list() throws Exception {
		//准备数据，departmentList
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备分页信息
		new QueryHelper(User.class, "u").preparePageBean(userService, pageNum, pageSize);

		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装到对象中（当model是实体类型时，也可以使用model，但要设置未封装的属性）
		// >> 设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// >> 设置默认密码为1234（要使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);

		// 保存到数据库
		userService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	/** 初始化密码为1234 */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	/** 登录页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登录 */
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "用户名或密码不正确！");
			return "loginUI";
		} else {
			// 登录用户
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}

	/** 注销 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	/**用户管理综合查询**/
	
	public String select() throws Exception{
		//准备数据，departmentList
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		HttpServletRequest request=ServletActionContext.getRequest();
		String seloginName=request.getParameter("seloginName").trim();
		String sename=request.getParameter("sename").trim();
		String sedepartmentId=request.getParameter("sedepartmentId").trim();
		String seroleId=request.getParameter("seroleId").trim();
		System.out.println(seroleId);
		Object[] ob={"%"+seloginName+"%","%"+sename+"%"};
		String hql=" u.loginName like ? and u.name like ? ";
		if(sedepartmentId==""&&seroleId==""){
			new QueryHelper(User.class, "u").addCondition(hql, ob).preparePageBean(userService, pageNum, pageSize);
		}
		if(sedepartmentId!=""&&seroleId==""){
			Department department=departmentService.getById(Long.parseLong(sedepartmentId));
			new QueryHelper(User.class, "u").addCondition("u.department like ?", department).addCondition(hql, ob).preparePageBean(userService, pageNum, pageSize);
		}
		if(sedepartmentId==""&&seroleId!=""){
			Role role=roleService.getById(Long.parseLong(seroleId));
			Set<User> users=role.getUsers();
			Object[] o=new Object[users.size()];
			List<Long> list=new ArrayList<Long>();
			String s="";
			for(User u:users){
				list.add(u.getId());
				s=s+"?,";
			}
			s=s.substring(0,s.length()-1);
			o=list.toArray(o);
			String hql2=" u.id in ("+s+")";
			new QueryHelper(User.class, "u").addCondition(hql2, o).addCondition(hql, ob).preparePageBean(userService, pageNum, pageSize);
		}
		if(sedepartmentId!=""&&seroleId!=""){
			Department department=departmentService.getById(Long.parseLong(sedepartmentId));
			Role role=roleService.getById(Long.parseLong(seroleId));
			Set<User> users=role.getUsers();
			Object[] o=new Object[users.size()];
			List<Long> list=new ArrayList<Long>();
			String s="";
			for(User u:users){
				list.add(u.getId());
				s=s+"?,";
			}
			s=s.substring(0,s.length()-1);
			o=list.toArray(o);
			String hql2=" u.id in ("+s+")";
			new QueryHelper(User.class, "u").addCondition("u.department like ?", department).addCondition(hql2, o).addCondition(hql, ob).preparePageBean(userService, pageNum, pageSize);
		}
		return "list";
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
