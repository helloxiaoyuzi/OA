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
    <title>公告列表</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 公告列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">名称</td>
                <td width="300px">内容</td>
                <td>显示时间</td>
                <td>显示状态</td>
                <td>紧急状态</td>
                <td>发布人</td>
                <td>新建时间</td>
            </tr>
        </thead>

		<!--显示数据列表-->
		<c:forEach  items="${recordList }" var="m">
        <tbody id="TableData" class="dataContainer" datakey="roleList">
			<tr class="TableDetail1 template" align="center">
				<td>${m.title}&nbsp;</td>
				<td><textarea style="width: 300px;height: 50px;border-style: hidden;" readonly="readonly">${m.context}</textarea></td>
				<td>${m.uploadDate}<br>${m.uploadDateEnd}</td>
				<td>
					<c:choose>
						<c:when test="${dxdd >= m.uploadDate and dxdd <= m.uploadDateEnd}">正常</c:when>
						<c:when test="${dxdd > m.uploadDateEnd}"><font style="color: red;">过期</font></c:when>
						<c:otherwise><font style="color: green;">等待</font></c:otherwise>
					</c:choose>
				
				</td>
				<td>
					<c:if test="${m.state eq 0 }">一般</c:if>
					<c:if test="${m.state eq 1 }"><font style="color: red;">紧急</font></c:if>
				</td>
				<td>${m.user.name}</td>
				<td>${m.newDate}</td>
			</tr>
        </tbody>
        </c:forEach>
        
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
           <!--<a href="notice_addnot.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>  -->o
        </div>
    </div>
    
</div>
<!-- 分页信息 -->
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="notice_notlist.action"></s:form>













</body>

</html>
