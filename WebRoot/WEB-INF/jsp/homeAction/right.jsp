<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的桌面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style/skin.css" />
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<!-- 头部开始 -->
		<tr>
			<td width="17" valign="top" background="Images/mail_left_bg.gif">
				<img src="Images/left_top_right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="Images/content_bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" background="Images/content_bg.gif">
					<tr>
						<td height="31"><div class="title">我的桌面</div></td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="Images/mail_right_bg.gif"><img
				src="Images/nav_right_bg.gif" width="16" height="29" /></td>
		</tr>
		<!-- 中间部分开始 -->
		<tr>
			<!--第一行左边框-->
			<td valign="middle" background="Images/mail_left_bg.gif">&nbsp;</td>
			<!--第一行中间内容-->
			<td valign="top" bgcolor="#F7F8F9">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<!-- 空白行-->
					<tr>
						<td colspan="2" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					<tr>
						<!--左边内容-->
						<!--  <td colspan="2" valign="top">
							<h3 style="margin:0 0 10px 10px;">待处理任务</h3>
							<p
								style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;">
								<span> <a href="qingjia_list.action" style="text-decoration: none">请假申请-待处理（${requestScope.qingjianum }）</a><br>
									<p style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;">
										<span> <a href="business_list.action" style="text-decoration: none">出差申请-待处理（${requestScope.chuchainum }）</a><br>
									<p style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;">
									<span> <a href="meetingroom_orderlist.action" style="text-decoration: none">会议室申请-待处理（${requestScope.meetingnum }）</a><br>
									<p style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;">
									<span> <a href="print_orderlist.action" style="text-decoration: none">用章申请-待处理（${requestScope.cachetnum }）</a>
						</td>-->
						<td colspan="2" valign="top">
							<table width="100%" height="150" border="0" cellpadding="0"
								cellspacing="0" style="border: 1px solid #CCCCCC;">
								<tr>
									<td width="7%" height="27"
										background="Images/news_title_bg.gif"><img
										src="Images/news_title_bg.gif" width="2" height="27">
									</td>
									<td width="93%" background="Images/news_title_bg.gif"
										class="left_bt">待处理任务
									</td>
								</tr>
								<tr>
									<td></td>
									<td style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;"><a href="qingjia_list.action" style="text-decoration: none">请假申请-待处理（${requestScope.qingjianum }）</a></td>
								</tr>
								<tr>
									<td></td>
									<td style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;"><a href="business_list.action" style="text-decoration: none">出差申请-待处理（${requestScope.chuchainum }）</a></td>
								</tr>
								<tr>
									<td></td>
									<td style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;"><a href="meetingroom_orderlist.action" style="text-decoration: none">会议室申请-待处理（${requestScope.meetingnum }）</a></td>
								</tr>
								<tr>
									<td></td>
									<td style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;"><a href="print_orderlist.action" style="text-decoration: none">用章申请-待处理（${requestScope.cachetnum }）</a></td>
								</tr>
								<tr><td></td><td>&nbsp;</td></tr>
								<tr><td></td><td>&nbsp;</td></tr>
								<tr><td></td><td>&nbsp;</td></tr>
								<tr><td></td><td>&nbsp;</td></tr>
							</table>
						</td>
						<!--间隔-->

						<td width="2%">&nbsp;</td>
						<!--右边内容-->
						<td valign="top">
							<table width="100%" height="150" border="0" cellpadding="0"
								cellspacing="0" style="border: 1px solid #CCCCCC;">
								
								<tr>
									<td width="7%" height="27"
										background="Images/news_title_bg.gif"><img
										src="Images/news_title_bg.gif" width="2" height="27">
									</td>
									<td width="93%" background="Images/news_title_bg.gif"
										class="left_bt">通知公告
										<p style="float: right;">
											<a href="notice_notlist.action" target="right">更多</a>
										</p>
									</td>
								</tr>
								
								<tr>
									<td height="5" colspan="2">&nbsp;</td>
								</tr>
								
								<tr>
									<td height="100" valign="top" colspan="2" id="news"><marquee
											direction="up" scrollamount="2" vspace="30px" width="300px"
											height="100px" onmouseout="this.start()"
											onmouseover="this.stop()">
											<ul>
												<s:iterator value="#noticelist">
													<li><img src="Images/ts.gif" width="16" height="16"
														style="margin-left:10px;">${title}</li>

												</s:iterator>
											</ul>
										</marquee>
									</td>
								</tr>
								
								<tr>
									<td height="5" colspan="2">&nbsp;
										<p
											style="text-indent:20px;line-height:25px;margin-left:10px;font-size:15px;">
											<span> 详细咨询：<a target="_blank"
												href="http://wpa.qq.com/msgrd?v=3&uin=2801986936&site=qq&menu=yes"
												onfocus="this.blur()"> <img
													style="vertical-align:middle;" border="0"
													src="http://wpa.qq.com/pa?p=2:609307843:41" alt="思创为您服务"
													title="思创科技"> </a>
													</span>
										</p>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr height="20">
						<td colspan="2" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					<!--第二行-->
					<tr>
						<!-- 第三部分   我的便签 -->
						<td colspan="2" valign="top">
							<table width="100%" height="230" border="0" cellpadding="0"
								cellspacing="0" style="border: 1px solid #CCCCCC;">
								<tr>
									<td width="7%" background="Images/news_title_bg.gif"><img
										src="Images/news_title_bg.gif" width="2" height="27">
									</td>
									<td width="93%" background="Images/news_title_bg.gif"
										class="left_bt">我的便签
										<p style="float: right;">
											<a
												href="${pageContext.request.contextPath}/tag_allList.action">更多</a>
										</p>
									</td>
								</tr>
								<tr>
									<td height="186" valign="top">&nbsp;</td>
									<td height="186" valign="top" colspan="2"><font size="3">

											<s:iterator value="#taglist">
												<li style="list-style-type: none;"><img
													src="Images/FUNC20029.gif"> ${title }
													<p style="float: right;margin-right: 10px;">${newDate }</p>
												</li>
											</s:iterator> </font>
									</td>
								</tr>
								
								<tr>
									<td height="5" colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
						
						<td>&nbsp;</td>
						<!-- 第四部分   常用工具 -->
						<td valign="top">
							<table width="100%" height="230" border="0" cellpadding="0"
								cellspacing="0" style="border: 1px solid #CCCCCC;">
								<tr>
									<td width="7%" background="Images/news_title_bg.gif"><img
										src="Images/news_title_bg.gif" width="2" height="27">
									</td>
									<td width="93%" height="27"
										background="Images/news_title_bg.gif" class="left_bt">常用工具
									</td>
								</tr>
								
								<tr>
									<td height="186" valign="top">&nbsp;</td>
									<td height="102" valign="top"><a href="task_show.action"
										style="text-decoration: none"> <img alt=""
											src="Images/calendar.png"><br />&nbsp&nbsp我的日程 </a>
									</td>
								</tr>
								
								<tr>
									<td height="5" colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td height="40" colspan="4">
							<table width="100%" height="1" border="0" cellpadding="0"
								cellspacing="0" bgcolor="#ffffff">
								<tr>
									<td align="center"><iframe width="420" scrolling="yes"
											height="60" frameborder="0" allowtransparency="true"
											src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5"></iframe>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td width="2%">&nbsp;</td>
						<td width="51%" class="left_txt">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					
				</table>
			</td>
			<td background="Images/mail_right_bg.gif">&nbsp;</td>
		</tr>
	</table>
</body>
</html>