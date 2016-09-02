package com.sc.oa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Forum;
import com.sc.oa.domain.PageBean;
import com.sc.oa.domain.Reply;
import com.sc.oa.domain.Topic;
import com.sc.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	private Long forumId;
	private Long topicId;

	/** 显示单个主题（主帖+回帖列表） */
	public String show() throws Exception {
		// 准备数据：topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// // 准备数据：replyList
		// List<Reply> replyList = replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);

		// // 准备分页信息 v1
		// PageBean pageBean = replyService.getPageBeanByTopic(pageNum,
		// pageSize, topic);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// // 准备分页信息 v2
		// String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(topic);
		//
		// PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql,
		// parameters);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// 准备分页信息, 最终版
		new QueryHelper(Reply.class, "r")//
				.addCondition("r.topic=?", topic)//
				.addOrderProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum, pageSize);

		return "show";
	}

	/** 发表新主题页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 发表新主题 */
	public String add() throws Exception {
		// 封装
		// >> 表单参数，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(forumService.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间

		// 保存
		topicService.save(model);

		return "toShow"; // 转到新主题的显示页面
	}

	/**
	 * 设置精华贴
	 * 
	 * @return
	 */
	public String setJingHua() {
		model = topicService.getById(topicId);
		model.setType(1);
		topicService.update(model);
		return "setJingHua";
	}

	/**
	 * 设置普通贴
	 * 
	 * @return
	 */
	public String setPuTong() {
		model = topicService.getById(topicId);
		model.setType(0);
		topicService.update(model);
		return "setPuTong";
	}

	/**
	 * 设置置顶贴
	 * 
	 * @return
	 */
	public String setZhiDing() {
		model = topicService.getById(topicId);
		model.setType(2);
		topicService.update(model);
		return "setZhiDing";
	}

	/**
	 * 删除主题
	 * 
	 * @return
	 */
	public String delTopic() {
		model = topicService.getById(topicId);
		model.setType(3);
		topicService.update(model);
		return "delTopic";
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
