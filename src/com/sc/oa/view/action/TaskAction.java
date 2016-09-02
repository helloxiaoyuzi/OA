package com.sc.oa.view.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Schedule;
import com.sc.oa.domain.User;
@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Schedule>{

	public String show(){
		
		return "showTask";
	}
	
	//获取目标月份任务信息
    public void getTasks() throws IOException {

    	String month=ServletActionContext.getRequest().getParameter("month");
    	User user=getCurrentUser();
        JSONArray array =taskService.getMine(user, month);      //新建JSON数组对象
//    	JSONArray array =taskService.getTasks(month); 
    	
        System.out.println(array);
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(array);
    }

    //添加新任务
   public int addTask() {
        User user=getCurrentUser();
        this.model.setId(2);
        this.model.setAppuser(user);      
        taskService.save(this.model);
        return this.model.getId();
    }

    //删除任务 
    public void delTask() {
        taskService.delete(this.model);
    }

    //更新任务
    public void updateTask() {
    	System.out.println("进入update");
    	Schedule schedule=taskService.getById2(this.model.getId());
    	schedule.setContext(this.model.getContext());
    	taskService.update(schedule); 	
    }
}
