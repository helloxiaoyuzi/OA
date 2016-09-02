<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<html>
<head>
    <title>文档列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
    

</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文档列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
	<table cellspacing="0" cellpadding="0" class="TableStyle">
		<form name="form" action="document_alldocu.action" method="post">
			 <tr align="left">
	            <td>文档名称(类型)：
					<input type="text" name="alldocu_sername">
					<a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a>
					<a href="document_alldocu.action?type=all">显示所有文档</a>
				</td>
	         </tr>
	    </form>
	</table>
	<br>
	<br>


    <table cellspacing="0" cellpadding="10" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">名称</td>
                <td width="300px">描述</td>
                <td>发布人</td>
                <td>发布时间</td>
                <td>权限</td>
                <td>操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
		<c:forEach  items="${recordList }" var="m">
        <tbody id="TableData" class="dataContainer" datakey="roleList">
			<tr bgcolor="#ffffff" onMouseOver="javascript:this.bgColor='#ccffcc'; "onmouseout="javascript:this.bgColor='#ffffff';">
				<td>${m.title}&nbsp;</td>
				<td>${m.description}</td>
				<td>${m.user.name}&nbsp;</td>
				<td>${m.uploadDate}&nbsp;</td>
				<td>
						<c:choose>
							<c:when test="${fn:contains(m.url,qx)}">本院</c:when>
							<c:otherwise><font color="red">全校</font></c:otherwise>
						</c:choose>
				</td>
				<td>
					<a href="${tpath}${m.url}">下载</a>
					
				</td>
			</tr>
        </tbody>
        </c:forEach>
        
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
          <!--   <a href="saveUI.html"><img src="../style/images/createNew.png" /></a> -->
        </div>
    </div>
</div>
<!-- 分页信息 -->
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="document_alldocu"></s:form>




</body>

</html>
