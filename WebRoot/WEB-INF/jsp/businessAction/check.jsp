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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 请假审核
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!-- 写内容 -->
<div id=MainArea>

    <s:form action="business_edit" onsubmit="return window.confirm('你确定当前的选择')">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="uid" value="${user.id}"/>
    	<!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div align="center"><font size="10" >出差申请审核</font>
        	</div>
            <div class="ItemBlock">
                <table cellpadding="10" cellspacing="0" class="mainForm">
                    <tr><td>申&nbsp请&nbsp人:&nbsp;${appuser.name}&nbsp;</td>
                        <td></td>
                    </tr>
                    <tr><td>出差日期:&nbsp;${startTime}</td>
                        <td>结束日期:&nbsp;${endTime}</td>
                    </tr>
                    <tr><td>出差地址:&nbsp;${address}</td>
                        <td>交通工具:&nbsp;${vehicle}</td>
                    </tr>
                     <tr><td>出差事由:</td>
                         <td>工作任务:</td>
                    </tr>
               		<tr><td><s:textarea name="content" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                        <td><s:textarea name="task" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                    </tr>
                   
                    <tr><td>出差预支经费:&nbsp;${money}</td>
                        <td>申请日期:&nbsp;${appDate}</td>
                    </tr>
                     <tr><td>审核状态:&nbsp;<s:radio name="state" list="#{'审核通过':'同意','审核未通过':'不同意'}" value="'审核通过'"></s:radio></td>
                     	<td></td>
                    </tr>
                </table>
            </div>
        

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
