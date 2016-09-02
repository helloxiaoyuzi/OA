<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>会议室预定管理</title>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    $(function(){
    
   	
	    $('#all').click(function(){
    	 if(this.checked){ 
		        $("input[name='id[]']").attr('checked', true);
		    }else{ 
		        $("input[name='id[]']").attr('checked', false);
		    } 
	    })    
	    $("#sub").click(function(){
	     var ids=""; 
	     var meetname="";
	    	$("input[name='id[]']:checked").each(function(idx, obj){
	    		//ids[idx]=$(this).val();
	    		ids=ids+$(this).val()+",";
	    		meetname=meetname+$(this).parent().parent().children().eq(1).text().trim()+"-";	
	    		
	    	})
	    	$("input[name='ids']").val(ids.substr(0,ids.length-1));
	    	$("input[name='meetname']").val(meetname.substr(0,meetname.length-1));
	   	 })	  
	   	  $("#save").click(function(){
	   	   var ids=""; 
	   	    var meetname="";
	   	  	$("input[name='id[]']:checked").each(function(idx, obj){
	    		ids=ids+$(this).val()+",";
	    		meetname=meetname+$(this).parent().parent().children().eq(1).text().trim()+"-";	
	    		
	    	})
	    	$("input[name='ids']").val(ids.substr(0,ids.length-1));
	    	$("input[name='meetname']").val(meetname.substr(0,meetname.length-1));
	   	  	$("#form1").attr("action","meetingroom_verify.action");
	   	  
	   	  })
    })
    </script>
</head>
<body> 

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 会议室预定管理
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
    <form action="meetingroom_delApply.action" method="post" id="form1">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        	未审核查询结果：
        </div>
        <hr>
            <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100"><input type="checkbox"  id="all">选择</td>
                <td width="100">会议室名称</td>
                <td width="100">预定人</td>
                <td >预定时间</td>
                <td >审核状态</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="rooms">
          <c:forEach items="${orderapplylist}" var="avroom">
            <tr class="TableDetail1 template">
                <td width="20%"><input type="checkbox" name="id[]" value="${avroom.id}">&nbsp;</td>
                <td width="20%">${avroom.meetName}&nbsp;</td>
                <td  width="20%">${avroom.userName}&nbsp;</td>  
               <td>${avroom.applyDate}&nbsp;</td>    
                <td>
              <c:if test="${avroom.state ==0}">未审核</c:if>
                <c:if test="${avroom.state ==1}">审核通过</c:if>
                <c:if test="${avroom.state ==2}">审核未通过</c:if>&nbsp;
                </td>  
            </tr>
             </c:forEach>
        </tbody>
    </table>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/bh.png" id="sub"/>
            <input type="image" src="${pageContext.request.contextPath}/style/images/sh.png" id="save"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
        <input type="hidden" name="ids">
         <input type="hidden" name="meetname">
    </form>
   
 <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        	已审核查询结果：
        </div>
        <hr>
            <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">会议室名称</td>
                <td width="100">预定人</td>
                <td >预定时间</td>
                <td >审核状态</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="rooms">
          <c:forEach items="${noorderapplylist}" var="avroom">
            <tr class="TableDetail1 template">
                <td width="20%">${avroom.meetName}&nbsp;</td>
                <td  width="20%">${avroom.userName}&nbsp;</td>  
               <td>${avroom.applyDate}&nbsp;</td>    
                <td>
              <c:if test="${avroom.state ==0}">未审核</c:if>
                <c:if test="${avroom.state ==1}">审核通过</c:if>
                <c:if test="${avroom.state ==2}">审核未通过</c:if>&nbsp;
                </td>  
            </tr>
             </c:forEach>
        </tbody>
    </table>
    
</div>


</body>
</html>
