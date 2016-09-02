<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<title>学院详情</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				学院详情
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<div class="ItemBlock_Title1">
			<DIV CLASS="ItemBlock_Title1">
				<IMG BORDER="0" WIDTH="4" HEIGHT="7"
					SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
				学院信息
			</DIV>
		</div>

		<!-- 表单内容显示 -->
		<div class="ItemBlockBorder">
			<div class="ItemBlock">
				<table cellpadding="5" cellspacing="0" class="mainForm">
					<tr>
						<td>学院名称</td>
						<td>${name}</td>
					</tr>
					<c:forEach items="${roleNames}" var="keyword">
					<tr>
						<td>
							${keyword.key}用户数量
						</td>
						<td>
							${keyword.value}人
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td>学院概述</td>
						<td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<a href="javascript:history.go(-1);"><img
				src="${pageContext.request.contextPath}/style/images/goBack.png" />
			</a>
		</div>
	</div>
</body>
</html>
