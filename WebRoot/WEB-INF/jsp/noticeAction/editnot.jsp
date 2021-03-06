<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>编辑公告</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    function onsub(){
		var title=document.getElementById("title").value;
		  if(title.length>0&&title.length<255){return true; }
		  else{alert("标题的字数在1-255之间？");return false;}		
		}
		
	function ondd(){  
		var dd="${gfs}";
		var de="${gfd}";
		dojo.widget.byId("gfs").setValue(dd);
		dojo.widget.byId("gfd").setValue(de);
	}
    
    </script>
     <sx:head parseContent="true"/>
</head>
<body onload="ondd()">
 
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 编辑公告
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="notice_bcnotice.action" method="post"  ENCTYPE="multipart/form-data"   onsubmit="return onsub()">
         
         
		
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 编辑公告 </div> 
        </div>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="130">公告标题<input type="hidden" value="${oneNot.id}" name="id"></td>
                        <td><input type="text" name="title" id="title" class="InputStyle" value="${oneNot.title}">
                        	<c:if test="${oneNot.state eq 0 }">
                        	<input type="radio" name="state" checked="checked" value="0">一般
                        	<input type="radio" name="state" value="1">紧急
                        	</c:if>
                        	<c:if test="${oneNot.state eq 1 }">
                        	<input type="radio" name="state"  value="0">一般
                        	<input type="radio" name="state" checked="checked" value="1">紧急
                        	</c:if>
                         *</td>
                    </tr>
                     <tr>
                        <td width="130">修改时间</td>
                        <td><%=new Date().toLocaleString() %>
						</td>
                     <tr>
                        <td width="130">显示时间${gfs}</td>
                        <td>
                        	<sx:datetimepicker  name="uploadDate" value="%{'today'}"   id="gfs"
							toggleType="plain" toggleDuration="300" language="zh_CN" type="date"   
							displayWeeks="5" displayFormat="yyyy-MM-dd" formatLength="long"  /> 
							——
							<sx:datetimepicker  name="uploadDateEnd" value="%{'today'}"   id="gfd"
							toggleType="plain" toggleDuration="300" language="zh_CN" type="date"   
							displayWeeks="5" displayFormat="yyyy-MM-dd" formatLength="long"  /> *
						</td>
                    </tr>
                     <tr>
                        <td width="130">公告内容</td>
                        <td><textarea name="context" id="context" class="InputStyle" style="width:450px;height: 150px;">${oneNot.context}</textarea> *</td>
                    </tr>
                  
                </table>
            </div>
        </div>
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"/>
			<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
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
</html>
