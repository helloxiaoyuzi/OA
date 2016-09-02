<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 出差管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!-- 写内容 -->


    
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">申请人</td>
                <td width="300">出差内容</td>
                <td width="300">出差任务</td>
                <td width="100">出差日期</td>
                <td width="100">出差预支经费</td>
                <td width="100">审核状态</td>
                <td width="100">审核人</td>
                <td>操作</td>
            </tr>
        </thead>
          <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">        
        <s:iterator value="recordList">
            <tr bgcolor="#BCD2EE" onMouseOver="javascript:this.bgColor='#ccffcc'; "onmouseout="javascript:this.bgColor='#BCD2EE';">
                <td align="center">${appuser.name}&nbsp;</td>
                <td>${content}&nbsp;</td>
                <td align="center">${task}&nbsp;</td>
                <td align="center">${startTime}&nbsp;</td>
                <td align="center">${money}&nbsp;</td>
                <s:if test="state=='未审核'">
                	<td align="center"><font color="red">${state}&nbsp;</font></td>
                </s:if>
                <s:elseif test="state=='审核未通过'">
                	<td align="center"><font color="yellow">${state}&nbsp;</font></td>
                </s:elseif>
                <s:else>
                	<td align="center">${state}&nbsp;</td>
                </s:else>
                <td align="center">${checkuser.name}&nbsp;</td>
                <td>
                	<s:if test="state=='未审核'">
                    	<s:a action="business_update?id=%{id}">修改</s:a>
                    </s:if>
                    <s:else>
                    	<s:a action="business_scan?id=%{id}">查看</s:a>
                    </s:else>
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
      </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="business_addUI"><img src="${pageContext.request.contextPath}/style/images/add.png"/></s:a>
        </div>
    </div>
</div>
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="business_appuserlist"></s:form>
</body>
</html>
