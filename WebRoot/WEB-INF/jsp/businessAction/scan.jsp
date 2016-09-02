<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>请假申请</title>
   	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<script type="text/javascript">
function submitForm(form){
	var errorMsg="";//定义错误信息
	var start=form.startTime.value;
	var end=form.endTime.value;
	var address=form.address.value;
	var vehicle=form.vehicle.value;
	var content=form.content.value;
	var task=form.task.value;
	var money=form.money.value;
	if(start==null||start==""){
		errorMsg="请输入出差日期";
	}else if(end==null||end==""){
		errorMsg="请输入结束日期";
	}else if(address==null||address==""){
		errorMsg="请输入出差地址";
	}else if(vehicle==null||vehicle=="-1"||vehicle==""){
		errorMsg="请选择出行方式";
	}else if(content==null||content==""){
		errorMsg="请输入出差事由";
	}else if(task==null||task==""){
		errorMsg="请输入工作任务";
	}else if(money==null||money==""){
		errorMsg="请输入出差预支经费";
	}else{
		errorMsg="";
	}
	if(errorMsg==""){
		return true;
	}else{
		alert(errorMsg);
		return false;
	}
}
</script>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 查看出差申请表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="business_saveUpdate" onsubmit="return submitForm(this)">
    	<!-- 表单内容显示 -->
    	<input type="hidden" name="uid" value="${user.id}"/>
    	<input type="hidden" name="id" value="${id}"/>
        <div class="ItemBlockBorder">
        	<div align="center"><font size="10" >出差申请表</font>
        	</div>
            <div class="ItemBlock">
                <table cellpadding="10" cellspacing="0" class="mainForm">
                    <tr><td>申&nbsp请&nbsp人:&nbsp;${user.name}&nbsp;</td>
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
                        <td>审核状态:&nbsp;${state}</td>
                    </tr>
                    <tr><td>申请日期:&nbsp;${appDate}</td>
                        <td>审核日期:&nbsp;${checkDate}</td>
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
<script>
	!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
	}();
</script>
</body>
</html>
