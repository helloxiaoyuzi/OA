package com.sc.oa.util;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;



public class DocumentUpload{
	
	//显示服务器路径下的文件（不在工程内部,下载用）
	public static String docUploadPath(HttpServletRequest request){
		String  docUploadPath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return docUploadPath; 
	 }
	//上传文档,返回相对路径
	public static String uploadDocument(File myfile,String myfileFileName,Long departmentId) throws Exception{
		String  mess="";
		InputStream in=new FileInputStream(myfile);
		String path=ServletActionContext.getServletContext().getRealPath("");
		String path1=path.substring(0,path.lastIndexOf("\\"));
		String xpath="/OAallDocument/"+departmentId+"/";
		String fname=myfileFileName.substring(0,myfileFileName.lastIndexOf("."));
		String houzhui= myfileFileName.substring(myfileFileName.lastIndexOf("."), myfileFileName.length());
		boolean boo=docformt(myfileFileName);
		String xp=xpath+myfileFileName;
		if(boo){
			if(myfile.length()<=100*1024*2014){//文档大小100M
				File fdir=new File(path1+xpath);
				if(!fdir.exists()){fdir.mkdir();}
				File newf=new File(fdir, myfileFileName);
				if(newf.exists()){
					Thread.sleep(10);
					fname=fname+getFileName()+houzhui;
					xp=xpath+fname;
					newf=new File(fdir, fname);
				}
				OutputStream out=new FileOutputStream(newf);
				byte[] b=new byte[1024];
				int len=0;
				while((len=in.read(b))!=-1){
					out.write(b, 0, len);
				}
				in.close();
				out.close();
				System.out.println(path1+xpath+"-绝对路径---上传文档的------相对路径--"+xp);
				return xp;
			}else{//文档>100M
				mess+="文档大于100M,";
			}
		}else{//格式不对
			mess+="文档格式不对，";
		}
		mess+="请填写正确信息！";
		return mess;
	}
	//文件名称，避免重名
	protected static String getFileName(){
		SimpleDateFormat sd=new SimpleDateFormat("ddHHmmssSSS");
		return sd.format(new Date());
	}
	//判断文档后缀
	protected static  boolean docformt(String myfileFileName) {
		boolean boo=false;
		String houzhui= myfileFileName.substring(myfileFileName.lastIndexOf("."), myfileFileName.length());
		String[]names={".doc",".docx",".ppt",".pptx",".xls",".xlsx",".zip",".rar",".pdf"};
		for (int i = 0; !boo&&i < names.length; i++) {
			if(names[i].equals(houzhui))boo=true;
		}
		return boo;
	}
	
	//删除文档，传入相对路径
	public static void delDocument(String docpath){
		String path=ServletActionContext.getServletContext().getRealPath("");
		String path1=path.substring(0,path.lastIndexOf("\\"));
		String absutsolt_docpath=path1+docpath;
		File f=new File(absutsolt_docpath);
		if(f.exists()){f.delete();System.out.println("--删除文档的绝对路径---"+absutsolt_docpath);}
	}
	
}
