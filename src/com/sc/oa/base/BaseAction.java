package com.sc.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.sc.oa.service.BusinessInfoService;

import com.sc.oa.domain.User;
import com.sc.oa.service.BusinessService;
import com.sc.oa.service.CachetService;
import com.sc.oa.service.DepartmentService;
import com.sc.oa.service.DocumentService;
import com.sc.oa.service.ForumService;
import com.sc.oa.service.MeetingService;
import com.sc.oa.service.MeetingroomInfoService;
import com.sc.oa.service.NoticeService;
import com.sc.oa.service.PrintService;
import com.sc.oa.service.PrivilegeService;
import com.sc.oa.service.QingJiaService;
import com.sc.oa.service.QingjiaInfoService;
import com.sc.oa.service.ReplyService;
import com.sc.oa.service.RoleService;
import com.sc.oa.service.TagService;
import com.sc.oa.service.TaskService;
import com.sc.oa.service.TopicService;
import com.sc.oa.service.UserService;
import com.sun.jmx.snmp.tasks.TaskServer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// =============== ModelDriven的支持 ==================

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;

	@Resource
	protected DocumentService documentService;

	@Resource
	protected NoticeService noticeService;
	@Resource
	protected QingJiaService qingJiaService;

	@Resource
	protected MeetingService meetingService;
	@Resource
	protected TagService tagService;
	
	@Resource
	protected QingjiaInfoService qingjiaInfoService;
	@Resource
	protected BusinessInfoService businessInfoService;
	
	@Resource
	protected MeetingroomInfoService meetingroomInfoService;

	@Resource
	protected PrintService printService;
	
	@Resource
	protected TaskService taskService;
	
	@Resource
	protected BusinessService businessService;
	@Resource
	protected CachetService cachetService;


	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	// ============== 分页用的参数 =============

	protected int pageNum = 1; // 当前页
	protected int pageSize = 10; // 每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
