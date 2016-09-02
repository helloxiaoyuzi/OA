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
	<s:form action="" onsubmit="return submitForm(this)">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="uid" value="${user.id}"/>
    	<!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div align="center"><font size="10" >请假审核</font>
        	</div>
            <div class="ItemBlock">
                <table cellpadding="10" cellspacing="0" class="mainForm">
                    <tr><td>申&nbsp请&nbsp人:&nbsp;${appuser.name}&nbsp;</td>
                        <td>审&nbsp核&nbsp人:&nbsp;${checkuser.name}&nbsp;</td>
                    </tr>
                      <tr><td>开始时间:&nbsp;${startTime}</td>
                        <td>结束时间:&nbsp;${endTime}</td>
                    </tr>
                    <tr><td>请假事由:</td>
                        <td>审核状态:&nbsp;${state}</td>
                    </tr>
					<tr><td><s:textarea name="content" cssClass="TextareaStyle" disabled="true"></s:textarea></td>
                        <td></td>
                    </tr>
                    <tr><td>申请时间:&nbsp;${appDate}</td>
                        <td>审批时间:&nbsp;${checkDate}</td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
      </s:form>
</div>
</body>
</html>
