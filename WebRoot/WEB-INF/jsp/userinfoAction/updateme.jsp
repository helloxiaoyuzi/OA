<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>修改个人信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
   
</head>
<body>
 
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>修改个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="userinfo_updateTJ.action" method="post"     onsubmit="return onsub()">
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改个人信息&nbsp;&nbsp;&nbsp;&nbsp;<a href="userinfo_xgpass.action">修改密码</a></div> 
        </div>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr>
                        <td width="130">姓名</td>
                        <td><input type="hidden" value="${user.id}" name="id">
                        	<c:choose>
                        		<c:when test="${user.loginName == 'admin' }">
                        			<input type="text" name="name" id="name" class="InputStyle" value="${user.name }" onchange="oncname()"><div id="idname" style="color:red"></div>
                        		</c:when>
                        		<c:otherwise>
                        			<input type="text" name="name" id="name" class="InputStyle" value="${user.name }" readonly="readonly">
                        		</c:otherwise>
                        	</c:choose>                                             
						</td>
                     </tr>
                    <tr>
                        <td width="130">性别</td>
                        <td>
                        	<c:if test="${user.gender == '男' or empty user.gender}">
                        	<input type="radio" name="gender" checked="checked" value="0">男
                        	<input type="radio" name="gender" value="1">女
                        	</c:if>
                        	<c:if test="${user.gender == '女' }">
                        	<input type="radio" name="gender"  value="0">男
                        	<input type="radio" name="gender" checked="checked" value="1">女
                        	</c:if>
                         *</td>
                    </tr>
                     <tr>
                        <td width="130">手机</td>
                        <td><input type="text" name="phoneNumber" id="phoneNumber" class="InputStyle" value="${user.phoneNumber}" onchange="oncphone()"><div id="idphone" style="color:red"></div>
						</td>
                     </tr>
                     <tr>
                        <td width="130">邮箱</td>
                        <td><input type="text" name="email" id="email" class="InputStyle" value="${user.email}" onchange="oncemail()"><div id="idemail" style="color:red"></div>
						</td>
                    </tr>
                     <tr>
                        <td width="130">个人描述</td>
                        <td><textarea name="description" id="description" class="InputStyle" style="width:450px;height: 150px;"  onchange="oncdes()">${user.description}</textarea><div id="iddes" style="color:red"></div></td>
                    </tr>
                   <tr>
                        <td colspan="2"> 
                       <p style="color: red;">${ff}</p>
						</td>
                    </tr>
                </table>
            </div>
        </div>
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"/>
        </div>
    </form>
</div>

<div class="Description">
	使用说明：<br />
	1，紧急公告优先级较高。<br />
	2，当前时间超过显示时间，公告将不再显示。<br />
	<br />
	
</div>

</body>









 <script type="text/javascript">
    function onsub(){
		if(oncname()&&oncphone()&&oncemail()&&oncdes())return true;
		else{alert("请输入正确的信息！！");return false;}
	}
	function oncname(){
		var name=document.getElementById("name").value.length;
		var idname=document.getElementById("idname");
		if(name>0&&name<255){
			idname.innerHTML="";
			return true;
		}else{
			idname.innerHTML="请填写姓名！！";
			return false;
		}
	}
    function oncphone(){
    	var phone=document.getElementById("phoneNumber").value;
		var idphone=document.getElementById("idphone");
		if(phone.length==11&&!isNaN(phone)){
			idphone.innerHTML="";
			return true;
		}else{
			idphone.innerHTML="请填写正确的手机号！！";
			return false;
		}
    }
     function oncemail(){
    	var email=document.getElementById("email").value;
		var idemail=document.getElementById("idemail");
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(reg.test(email)){
			idemail.innerHTML="";
			return true;
		}else{
			idemail.innerHTML="请填写正确的邮箱！！";
			return false;
		}
    }
    function oncdes(){
    	var des=document.getElementById("description").value.length;
		var iddes=document.getElementById("iddes");
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(des<255){
			idemail.innerHTML="";
			return true;
		}else{
			idemail.innerHTML="个人描述在255字以内！";
			return false;
		}
    }
    
    
    
    
    </script>
    
</html>
