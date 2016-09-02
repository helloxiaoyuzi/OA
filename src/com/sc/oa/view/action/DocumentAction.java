package com.sc.oa.view.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sc.oa.base.BaseAction;
import com.sc.oa.domain.Department;
import com.sc.oa.domain.Document;
import com.sc.oa.domain.PageBean;
import com.sc.oa.domain.User;
import com.sc.oa.util.DocumentUpload;
import com.sc.oa.util.QueryHelper;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DocumentAction extends BaseAction<Document> {
	private File  myfile;
	private String  myfileFileName;
	
	private static String alldocu_sername=null;
	
	public String alldocu(){
		HttpServletRequest request=ServletActionContext.getRequest();
		if(request.getParameter("alldocu_sername")!=null&&request.getParameter("alldocu_sername").trim().length()>0)
			alldocu_sername=request.getParameter("alldocu_sername");
		if(request.getParameter("type")!=null&&request.getParameter("type").equals("all"))
			alldocu_sername=null;
		Object[] obj={};
		String hql="";
		Long departmentId=0L;
		User user=(User) ActionContext.getContext().getSession().get("user");
		Department department=user.getDepartment();
		if(department==null)departmentId=0L;
		else departmentId=department.getId();
		String str="/"+departmentId+"/";
		if(departmentId!=0){	
			if(alldocu_sername==null){
				Object[] ob={"%"+str+"%","%/"+0+"/%"};
				obj=ob;
				hql=" ( docu.url like ? or docu.url like ? ) and docu.state=0 order by docu.id desc ";
			}else{
				Object[] ob={"%"+str+"%","%/"+0+"/%","%"+alldocu_sername+"%"};
				obj=ob;
				hql=" ( docu.url like ? or docu.url like ? ) and docu.title like ? and docu.state=0 order by docu.id desc ";
			}
		}else{
			if(alldocu_sername==null){
				Object[] ob={"%/"+0+"/%"};
				obj=ob;
				hql=" docu.url like ?  and docu.state=0 order by docu.id desc";
			}else{
				Object[] ob={"%/"+0+"/%","%"+alldocu_sername+"%"};
				obj=ob;
				hql=" docu.url like ?  and docu.title like ? and docu.state=0 order by docu.id desc ";
			}
		}
		new QueryHelper(Document.class, "docu").addCondition(hql, obj).preparePageBean(documentService, pageNum, pageSize);
		ActionContext.getContext().put("qx",str);
		ActionContext.getContext().put("tpath",DocumentUpload.docUploadPath(request));
		System.out.println("---------"+DocumentUpload.docUploadPath(request));
		return "alldocu";
	}
	public String medocu(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long departmentId=0L;
		User user=(User) ActionContext.getContext().getSession().get("user");
		Department department=user.getDepartment();
		if(department==null)departmentId=0L;
		else departmentId=department.getId();
		String str="/"+departmentId+"/";
		Object[] ob={user};
		new QueryHelper(Document.class, "docu").addCondition(" docu.user=? and docu.state=0 order by docu.id desc", ob).preparePageBean(documentService, pageNum, pageSize);
		ActionContext.getContext().put("qx",str);
		ActionContext.getContext().put("tpath",DocumentUpload.docUploadPath(request));
		return "medocu";
	}
	public String adddocu(){
		return "adddocu";
	}
	public String submitDocu() throws Exception{	 
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String xp="";
		Long departmentId=0L;
		User user=(User) ActionContext.getContext().getSession().get("user");
		Department department=user.getDepartment();
		int qx=Integer.parseInt(request.getParameter("qx"));
		if(qx==0)departmentId=0L;
		if(qx==1){
			if(department==null)departmentId=0L;
			else departmentId=department.getId();
		}
		if(myfile!=null)xp=DocumentUpload.uploadDocument(myfile,myfileFileName,departmentId);
		if(xp!=null&&xp.startsWith("/")){
			model.setTitle(myfileFileName);
			model.setUrl(xp);
			model.setState(0);
			model.setUploadDate(new Date());
			model.setUser(user);
			documentService.save(model);System.out.println("-------"+xp);
			return "tomedocu";
		}
		else {
			xp="上传失败!"+xp;
			ActionContext.getContext().put("addf", xp);
			return "adddocu";
			}
	}
	public String  jjdeldocu() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		Document docu=documentService.getById(new Long(id));
		docu.setState(1);
		documentService.update(docu);
		return "tomedocu";
	}
	public String  resavedocu() {
		HttpServletRequest request=ServletActionContext.getRequest();
		Long departmentId=0L;
		User user=(User) ActionContext.getContext().getSession().get("user");
		Department department=user.getDepartment();
		if(department==null)departmentId=0L;
		else departmentId=department.getId();
		String str="/"+departmentId+"/";
		Object[] ob={user};
		new QueryHelper(Document.class, "docu").addCondition(" docu.user=? and docu.state=1 order by docu.id desc", ob).preparePageBean(documentService, pageNum, pageSize);
		ActionContext.getContext().put("qx",str);
		ActionContext.getContext().put("tpath",DocumentUpload.docUploadPath(request));
		return "resavedocu";
	}
	public String  resdocu() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		Document docu=documentService.getById(new Long(id));
		docu.setState(0);
		documentService.update(docu);
		return "toresavedocu";
	}
	public String  deldocu() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		Document docu=documentService.getById(new Long(id));
		DocumentUpload.delDocument(docu.getUrl());
		documentService.delete(new Long(id));
		return "toresavedocu";
	}
	public String  cleardocu() {
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) ActionContext.getContext().getSession().get("user");
		List li=documentService.getUserdocus(user);
		if(li!=null){
			for (int i = 0; i < li.size(); i++) {
				Document docu=(Document) li.get(i);
				DocumentUpload.delDocument(docu.getUrl());
				documentService.delete(docu.getId());
			}
		}
		return "toresavedocu";
	}
	public String  delids() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String ss=str.substring(0, str.lastIndexOf("*"));
		String[] ids=ss.split("\\*");
		for (int i = 0; i < ids.length; i++) {
			Document docu=documentService.getById(new Long(ids[i]));
			DocumentUpload.delDocument(docu.getUrl());
			documentService.delete(docu.getId());
		}
		return "toresavedocu";
	}
	public File getMyfile() {
		return myfile;
	}
	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}
	public String getMyfileFileName() {
		return myfileFileName;
	}
	public void setMyfileFileName(String myfileFileName) {
		this.myfileFileName = myfileFileName;
	}
	
	
	
	
}
