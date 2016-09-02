package com.sc.oa.view.action;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Reply;
import com.sc.oa.domain.Tag;
import com.sc.oa.domain.User;
import com.sc.oa.util.QueryHelper;
@Controller
@Scope("prototype")
public class TagAction extends BaseAction<Tag>{
	
	public String allList(){
		User user=(User) ActionContext.getContext().getSession().get("user");
//		List list=tagService.getMine(user);
//		ActionContext.getContext().put("list", list);
		
		new QueryHelper(Tag.class, "r")//
		.addCondition("r.appuser=?", user)//
		.addOrderProperty("r.newDate", false)//
		.preparePageBean(tagService, pageNum, pageSize);

		return "showTag";
	}
	
	public String addPage(){
		return "toSave";
	}
	
	public String save(){
		
		Tag tag=new Tag();
		tag.setTitle(this.model.getTitle());

		if(this.model.getTitle().equals("")){
			String error="标题不能为空";
			addFieldError("error", error);
			return "toSave";
		}
		else{		
			User user=(User) ActionContext.getContext().getSession().get("user");
			this.model.setNewDate(new Date());
			this.model.setAppuser(user);		
			tagService.save(model);
			return "toShowTag";
		}
		
	}
	
	public String delete(){
		tagService.delete(this.model.getId());
		return "toShowTag";
	}
}
