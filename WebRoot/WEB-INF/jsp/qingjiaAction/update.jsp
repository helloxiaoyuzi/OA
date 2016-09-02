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
	var content=form.content.value;
	if(start==null||start==""){
		errorMsg="请输入请假开始时间";
	}else if(end==null||end==""){
		errorMsg="请输入请假结束时间";
	}else if(content==null||content==""){
		errorMsg="请输入请假事由";
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 请假申请
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="qingjia_save" onsubmit="return submitForm(this)">
    	<!-- 表单内容显示 -->
    	<input type="hidden" name="uid" value="${user.id}"/>
    	<input type="hidden" name="id" value="${id}"/>
        <div class="ItemBlockBorder">
        	<div align="center"><font size="10" >请假申请</font>
        	</div>
            <div class="ItemBlock">
                <table cellpadding="10" cellspacing="0" class="mainForm">
                    <tr><td>申&nbsp请&nbsp人</td>
                        <td>${user.name}&nbsp;</td>
                    </tr>
                    <tr><td>开始时间</td>
                        <td><input name="startTime" placeholder="请输入开始日期" readonly="readonly" type="text" class="Wdate" id="d412" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
                    </tr>
					<tr><td>结束时间</td>
                        <td><input name="endTime" placeholder="请输入结束日期" readonly="readonly" type="text" class="Wdate" id="d412" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
                    </tr>
                    <tr><td>请假事由</td>
                        <td><s:textarea name="content" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/ >
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
            <s:a action="qingjia_appuserlist"><img src="${pageContext.request.contextPath}/style/images/scan.png"/></s:a>
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
