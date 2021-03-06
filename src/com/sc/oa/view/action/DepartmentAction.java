package com.sc.oa.view.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.Role;
import com.sc.oa.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	private Long parentId;
	private Long departmentId;

	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	
	public String detail() throws Exception{
		List<Role> roles=roleService.findAll();
		Map<String,Integer> roleNames = new HashMap<String,Integer>();
		for(int i=0;i<roles.size();i++){
			//System.out.println(roles.get(i).getName()+"  "+userService.count(roles.get(i).getId(), departmentId));
			roleNames.put(roles.get(i).getName(),userService.count(roles.get(i).getId(), departmentId));
		}
		ActionContext.getContext().put("roleNames", roleNames);
		Department department=departmentService.getById(departmentId);
		ActionContext.getContext().put("name", department.getName());
		ActionContext.getContext().put("description", department.getDescription());
		return "detail";
		
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 封装信息到对象中
		// Department department = new Department();
		// department.setName(name);
		// department.setDescription(description)
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);

		// 保存
		departmentService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备回显的数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库取出原对象
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId)); // 设置所属的上级部门

		// 3，更新到数据库中
		departmentService.update(department);

		return "toList";
	}

	// ---

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
