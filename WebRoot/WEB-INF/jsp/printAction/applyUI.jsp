<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>用章申请</title>
		<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用章申请
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <form action="print_apply.action" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
               		 <tr>
                        <td width="100">姓名：</td>
                        <td>${user.name}</td>
                    </tr>
                    <tr>
                        <td width="100">申请时间：</td>
                        <td><input name="appDate" placeholder="请输入开始日期" class="laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly="readonly"> *</td>
                    </tr>
                     <tr>
                        <td width="100">部门：</td>
                        <td>${user.department.name}</td>
                    </tr>
                     <tr>
                        <td width="100">用章类型：</td>
                        <td><select name="cachetId">
                        <c:forEach items="${cachetlist}" var="cachet">
							  <option value ="${cachet.id}">${cachet.name}</option>
						</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>
                        <td>申请原因</td>
                        <td><textarea name="content" class="TextareaStyle"></textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        <input type="hidden" name="state" value="0" /> 
        <input type="hidden" name="appuserId" value="${user.id}" /> 
         <input type="hidden" name="appdepId" value="${user.department.id}" /> 
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>
</body>
</html>
