<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>发布个人文档</title>
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
 
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 发布个人文档
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="document_submitDocu.action" method="post"  ENCTYPE="multipart/form-data"   onsubmit="return onsub()">
         
         
		
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 填写个人文档 </div> 
        </div>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="130">选择文档</td>
                        <td><input type="file" name="myfile" class="InputStyle" style="width:450px;" id="myfile"/> *</td>
                    </tr>
                    <tr>
                        <td width="130"></td>
                        <td>支持格式：".doc",".docx",".ppt",".pptx",".xls",".xlsx",".zip",".rar",".pdf"，大小100M以内</td>
                    </tr>
                     <tr>
                        <td width="130">文档描述</td>
                        <td><textarea name="description" id="description" class="InputStyle" style="width:450px;height: 150px;" ></textarea> *</td>
                    </tr>
                    <tr>
                        <td width="130">文档权限</td>
                        <td>您希望那些人可以看你的文档：<input type="radio" name="qx" value="1" checked="checked"/>本部
                        	<input type="radio" name="qx" value="0"/>整个公司
                        </td>
                    </tr>
                </table>
            </div>
        </div>
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"/>
			<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
			<p style="color: red;">${addf}</p>
        </div>
    </form>
</div>

<div class="Description">
	使用说明：<br />
	1，请认真读取要求。<br />
	2，填写文档中的表单。<br />
	3，不能上传有损他人利益的任何文件，后果自负。<br />
	<br />
	
</div>
<script type="text/javascript">
var myfile=document.getElementById("myfile");
var description=document.getElementById("description");
function onsub(){
  if(myfile.value.length>0){return true; }
  else{alert("请选择文件！");return false;}		
}


</script>
</body>
</html>
