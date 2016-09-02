<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>请假管理</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<script type="text/javascript">
function submitForm(form){
	var start=form.startTime.value;
	var end=form.endTime.value;
 	var d1 = new Date(start.replace(/\-/g, "\/")); 
 	var d2 = new Date(end.replace(/\-/g, "\/"));
	if(start!=""&&start!=""&&d1>=d2){
		alert("开始时间不能大于结束时间");
		return false;
	}
}
</script>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 请假管理-${topic}
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!-- 写内容 -->


    
<div id="MainArea">
	
		<table cellspacing="0" cellpadding="0" class="TableStyle">
		<form name="form" action="qingjia_select.action" method="post" onsubmit="return submitForm(this)">
			 <tr align=center valign=middle id=TableTitle>
	                <td>申请人</td>
	                <td><input type="text" name="appName"></td>
	                <td>开始时间</td>
                    <td><input name="startTime" placeholder="请输入开始日期" readonly="readonly" type="text" class="Wdate" id="d412" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
              		<td>结束时间</td>
					<td><input name="endTime" placeholder="请输入结束日期" readonly="readonly" type="text" class="Wdate" id="d412" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
	                <td>审核状态</td>
	                <td><s:select name="checkState" cssClass="SelectStyle" list="{'审核通过','未审核'}" headerKey="" headerValue="==请选择审核状态=="/></td>
	                <td><input type="submit" value="查询"></td>
	          </tr>
	          </form>
		</table>
	<br>
	<br>


    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">申请人</td>
                <td width="500">请假内容</td>
                <td width="100">开始时间</td>
                <td width="100">结束时间</td>
                <td width="100">审核状态</td>
                <td width="100">审核人</td>
                <td>操作</td>
            </tr>
        </thead>
          <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">        
        <s:iterator value="recordList">
            <tr bgcolor="#ffffff" onMouseOver="javascript:this.bgColor='#ccffcc'; "onmouseout="javascript:this.bgColor='#ffffff';">
                <td align="center">${appuser.name}&nbsp;</td>
                <td>${content}&nbsp;</td>
                <td align="center">${startTime}&nbsp;</td>
                <td align="center">${endTime}&nbsp;</td>
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
                    	<s:a action="qingjia_check?id=%{id}">审核</s:a>
                    </s:if>
                    <s:else>
                    	<s:a action="qingjia_scan?id=%{id}">查看</s:a>
                    </s:else>
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
      </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
          <!--   <s:a action="qingjia_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a> -->
        </div>
    </div>
</div>
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="qingjia_list"></s:form>
</body>
</html>
