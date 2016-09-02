<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>会议室列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="../script/jquery.js"></script>
    <script language="javascript" src="../script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="../script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="../script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="../script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="../style/blue/pageCommon.css" />
     <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="../style/images/title_arrow.gif"/> 会议室管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">会议室名称</td>
                <td width="100">描述</td>
                <td width="100">状态</td>
                <td >相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="rooms">
        <c:forEach items="${roomlist}" var="room">
            <tr class="TableDetail1 template">
                <td width="20%">${room.name}&nbsp;</td>
                <td width="20%">${room.desc}&nbsp;</td>
                <td>${room.state == 0?"可用":"不可用"}&nbsp;</td>   
                <td>
					<a href="meetingroom_del.action?id=${room.id}" onclick="return confirm('确定要删除吗？')">删除</a>
					<a href="meetingroom_editUI.action?id=${room.id}">修改</a>
					<c:if test="${room.state != 0}"><a href="meetingroom_resetstate.action?id=${room.id}">状态重置</a></c:if>
				</td>        
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="meetingroom_addUI.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
        </div>
    </div>
</div>

</body>
</html>
