<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>底部菜单</title>
	<LINK href="${pageContext.request.contextPath}/style/blue/statusbar.css" type=text/css rel=stylesheet>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>

<div id="StatusBar">
<!--  
    <div id="Online">
    	在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人<span class="OnlineView">
        <a href="javascript:void(0)">[查看在线名单]</a>
</span></div>
-->
    <div id="Info" style="margin-left: 45%;" >
    	<a href="#" title = "湖北文理学院" target=_blank >湖北文理学院</a> |
        <a href="#" title = "湖北文理学院教务系统" target=_blank >湖北文理学院教务系统</a>
    </div>
<!--  
    <DIV id=DesktopText>
        <a href="javascript:void(0)"><img border="0" src="${pageContext.request.contextPath}/style/images/top/text.gif"/> 便笺</a>

        <span id=TryoutInfo>

        </span>
        <span id="Version">
            <a href="javascript:void(0)">
            	<img border="0" width="11" height="11" src="${pageContext.request.contextPath}/style/images/top/help.gif" />
            	<img border="0" width="40" height="11" src="${pageContext.request.contextPath}/style/blue/images/top/version.gif" />
            </a>
        </span>
    </DIV>
    -->
</div>

</body>
</html>