<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>会议室预定</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 会议室预定
        </div>
        <div id="Title_End"></div>
    </div>
</div>
 <%--  <form action="meetingroom_search.action">
预定时间段：  <input type="text" name="name" class="InputStyle" />to<input type="text" name="name" class="InputStyle" />&nbsp;提示：预定时间必须为同一天
  <input type="image" src="${pageContext.request.contextPath}/style/images/find.gif"/>
  </form> --%>
<!--显示表单内容-->
<div id="MainArea">
    <form action="meetingroom_apply.action" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        	可预订会议室查询结果：
        </div>
            <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">选择</td>
                <td width="100">会议室名称</td>
                <td >会议室描述</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="rooms">
          <c:forEach items="${avaliableorderroomlist}" var="avroom">
            <tr class="TableDetail1 template">
                <td width="20%"><input type="radio" name="group" value="${avroom.name}" onchange="javascript:$('#mn').val($(this).val())">&nbsp;</td>
                <td width="20%">${avroom.name}&nbsp;</td>
                <td>${avroom.desc}&nbsp;</td>         
            </tr>
             </c:forEach>
        </tbody>
    </table>
        <!-- 表单内容显示 -->
    
            <div class="ItemBlock">会议室预定人：      
                <input type="text" name="userName" class="InputStyle" value="${user.name}"/> *
            </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
        <input type="hidden" name="meetName" id="mn" value="">
    </form>
</div>

</body>
</html>
