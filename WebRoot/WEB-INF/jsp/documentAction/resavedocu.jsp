<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>文档回收站</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文档回收站
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
	
    <table cellspacing="0" cellpadding="10" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="15px"><input type="checkbox" id="checkall" onclick="onc()"></td>
            	<td width="200px">名称</td>
                <td width="300px">描述</td>
                <td>发布人</td>
                <td>发布时间</td>
                <td>权限</td>
                <td>操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
		<c:if test="${empty recordList }"><p style="color: red;font-size: 28px;">暂无数据</p></c:if>
		<c:forEach  items="${recordList }" var="m">
        <tbody id="TableData" class="dataContainer" datakey="roleList">
			<tr bgcolor="#ffffff" onMouseOver="javascript:this.bgColor='#ccffcc'; "onmouseout="javascript:this.bgColor='#ffffff';">
				<td><input type="checkbox" name="ids" value="${m.id}"></td>
				<td>${m.title}&nbsp;</td>
				<td>${m.description}</td>
				<td>${m.user.name}&nbsp;</td>
				<td>${m.uploadDate}&nbsp;</td>
				<td>
						<c:choose>
							<c:when test="${fn:contains(m.url,qx)}">本院</c:when>
							<c:otherwise><font color="red">全校</font></c:otherwise>
						</c:choose>
				</td>
				<td>
					<a onClick="return confirm('确定恢复此文件？')" href="document_resdocu.action?id=${m.id}">恢复</a>
					<a onClick="return confirm('删除后将无法恢复，确定删除？')" href="document_deldocu.action?id=${m.id}">彻底删除</a>	
				</td>
			</tr>
        </tbody>
        </c:forEach>
        
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
        <c:if test="${not empty recordList }">
           <a   onclick="return onsub()" style="color: blue;cursor: pointer;"  target="_self">批量删除</a>
            <a  onClick="return confirm('删除清空回收站？')" href="document_cleardocu.action" style="color: blue;">清空回收站</a>
         </c:if>
        </div>
    </div>
</div>

<!-- 分页信息 -->
<%@ include  file="/WEB-INF/jsp/public/pageView.jspf"%>
<s:form action="document_resavedocu"></s:form>

</body>
<script type="text/javascript">
function onc(){//start
	var checkall=document.getElementById("checkall");
	if(checkall.checked){//if
		var beixuan = document.getElementsByName("ids").length;
		for(i=0;i<beixuan;i++){//for
			document.getElementsByName("ids")[i].checked =true;
		}//for
	}//if
	if(!checkall.checked){//if
		var beixuan = document.getElementsByName("ids").length;
		for(i=0;i<beixuan;i++){//for
			document.getElementsByName("ids")[i].checked =false;
		}//for
	}//if
}//end


function onsub(){//start
	var beixuan = document.getElementsByName("ids").length;
	var cc=0;
	var str="";
	for(i=0;i<beixuan;i++){//for
		if(document.getElementsByName("ids")[i].checked ==true){
			cc=cc+1;
			str=str+document.getElementsByName("ids")[i].value+"*";
		}
	}//for
	if(cc==0){alert("请至少选择一项！");return false;}
	else{
		if(confirm("删除后将无法恢复，确定删除？")){
		 	location.href="document_delids.action?str="+str ;
			return true; 
		}
		else return false;
	}
}//end
</script>

</html>
