<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>修改密码</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="userinfo_xgpassTJ.action" method="post"   onsubmit="return onsub()">
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改密码</div> 
        </div>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                     
                     <tr>
                        <td width="130">旧密码</td>
                        <td><input type="password" name="mm0" id="mm0" class="InputStyle" >
						</td>
                     <tr>
                     <tr>
                        <td width="130">新密码</td>
                        <td><input type="password" name="mm1" id="mm1" class="InputStyle">
						</td>
                    </tr>
                      <tr>
                        <td width="130">确认新密码</td>
                        <td><input type="password" name="mm2" id="mm2" class="InputStyle" >
						</td>
                    </tr>
                   <tr>
                        <td colspan="2"> 
                       <c:if test="${ff eq 1 }"> <p style="color: red;">请填写正确的旧密码！</p></c:if>
                        <c:if test="${ff eq 2 }"> <p style="color: red;">两次的新密码必须一样！</p></c:if>
                         <c:if test="${ff eq 3 }"> <p style="color: red;">修改成功！！！</p></c:if>
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
  <script type="text/javascript">
    function onsub(){
		var mm0=document.getElementById("mm0").value;
		var mm1=document.getElementById("mm1").value;
		var mm2=document.getElementById("mm2").value;
		  if(mm1.length>3&&mm1.length<13&&mm1.equal(mm2)){return true; }
		  else{alert("输入密码，长度在4-12之间，两次新密码必须相同？");return false;}		
		}
    
    </script>
</body>
</html>
