<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'taglist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>

    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />

  </head>
  
  <body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 便签管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="center" valign=middle id=TableTitle>
            	<td width="250px">便签标题</td>
            	<td width="600px">详细內容</td>
				<td width="240px">添加时间</td>
                <td>相关操作</td>
            </tr>
        </thead>

<s:form action="tag_allList.action">
		<!--显示数据列表-->
		<s:iterator value="recordList">
        <tbody id="TableData" class="dataContainer">
        
			<tr class="TableDetail1 template">
					<td>${title}&nbsp;</td>
					<td>${content}</td>
					<td align="center">${newDate}&nbsp;</td>
					<td align="center"><a href=" ${pageContext.request.contextPath}/tag_delete.action?id=${id}">删除</a>
					</td>
			</tr>
	
        </tbody>
        	</s:iterator>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
			<a href="${pageContext.request.contextPath}/tag_addPage.action"><img src="style/blue/images/button/addNew.PNG" /></a>
        </div>
    </div>
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
</s:form>
</div>
  </body>
</html>
