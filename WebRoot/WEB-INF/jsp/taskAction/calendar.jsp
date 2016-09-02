<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>日程表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
/* 页面基本样式 */
body,td,input {
	font-family: Arial;
	font-size: 12px;
}

/* 日程表格样式 */
#calTable {
	border-collapse: collapse;
	border: 5px solid #C3D9FF;
}

/* 每日单元格样式 */
td.calBox {
	border: 1px solid #CCDDEE;
	width: 100px;
	height: 80px;
	vertical-align: top;
}

/* 每日单元格内日期样式 */
td.calBox div.date {
	background: #E8EEF7;
	font-size: 11px;
	padding: 2px;
	cursor: pointer;
}

/* 每日单元格内周六周日样式 */
td.sat div.date,td.sun div.date {
	color: red;
}

/* 今日样式 */
td.calBox div.today {
	background: #BBCCDD;
}

/* 周标识样式 */
td.day {
	text-align: center;
	background: #C3D9FF;
	border: 1px solid #CCDDEE;
	color: #112ABB;
}

/* 当前显示的年月样式 */
#dateInfo {
	font-weight: bold;
	margin: 3px;
}

/* 添加任务div样式 */
#addBox {
	display: none;
	position: absolute;
	width: 300px;
	border: 1px solid #000;
	height: 100px;
	background: #FFFF99;
	padding: 10px;
}

/* 添加任务内日期样式 */
#taskDate {
	height: 30px;
	font-weight: bold;
	padding: 3px;
}

/* 按钮样式 */
.taskBtn {
	margin: 10px;
}

/* 编辑任务div样式 */
#editBox {
	display: none;
	position: absolute;
	width: 300px;
	border: 1px solid #000;
	height: 60px;
	background: #99FF99;
	padding: 10px;
}

/* 任务样式 */
div.task {
	width: 98px;
	overflow: hidden;
	white-space: nowrap;
	background: #668CB3;
	border: 1px solid #FFF;
	color: #FFF;
	padding: 1 2 1 3;
	cursor: pointer;
}
</style>
<script type="text/javascript" src="script/calendar_jquery.js"></script>
<script type="text/javascript">
var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);    //每月天数
var today = new Today();    //今日对象
var year = today.year;      //当前显示的年份
var month = today.month;    //当前显示的月份

//页面加载完毕后执行fillBox函数
$(function() {
    fillBox();
});

//今日对象
function Today() {
    this.now = new Date();
    this.year = this.now.getFullYear();
    this.month = this.now.getMonth();
    this.day = this.now.getDate();
}

//根据当前年月填充每日单元格
function fillBox() {
    updateDateInfo();                   //更新年月提示
    $("td.calBox").empty();             //清空每日单元格

    var dayCounter = 1;                 //设置天数计数器并初始化为1
    var cal = new Date(year, month, 1); //以当前年月第一天为参数创建日期对象
    var startDay = cal.getDay();        //计算填充开始位置
    //计算填充结束位置
    var endDay = startDay + getDays(cal.getMonth(), cal.getFullYear()) - 1;

    //如果显示的是今日所在月份的日程，设置day变量为今日日期
    var day = -1;
    if (today.year == year && today.month == month) {
        day = today.day;
    }

    //从startDay开始到endDay结束，在每日单元格内填入日期信息
    for (var i=startDay; i<=endDay; i++) {
        if (dayCounter==day) {
        	if(month<9){
        	$("#calBox" + i).html("<div class='date today' id='" + year + "-"+"0" + (month + 1) + "-" + dayCounter + "' onclick='openAddBox(this)'>" + dayCounter + "</div>");
        	}
        	else{
        	$("#calBox" + i).html("<div class='date today' id='" + year + "-" + (month + 1) + "-" + dayCounter + "' onclick='openAddBox(this)'>" + dayCounter + "</div>");
        	}       
        } else if(dayCounter>day){
        	if(month<9){
        	 $("#calBox" + i).html("<div class='date' id='" + year + "-"+"0" + (month + 1) + "-" + dayCounter + "' onclick='openAddBox(this)'>" + dayCounter + "</div>");
        	}
        	else{
        	$("#calBox" + i).html("<div class='date' id='" + year + "-" + (month + 1) + "-" + dayCounter + "' onclick='openAddBox(this)'>" + dayCounter + "</div>");
        	}          
        }
        else if(dayCounter<day){
        	 $("#calBox" + i).html("<div class='date' id='" + year + "-"+"0" + (month + 1) + "-" + dayCounter + "'>" + dayCounter + "</div>");
        }
        dayCounter++;
    }
    getTasks();                         //从服务器获取任务信息
}

//从服务器获取任务信息
function getTasks() {
  	var date;
  	if(month<9){
  	date=year+"-"+"0"+ (month + 1);
	  }
	  else{
	  date=year+"-"+(month + 1);
	  }
    $.getJSON("task_getTasks.action",               //服务器页面地址
        {
            month: date//年月参数
        },
        function(array) {                    //回调函数
            //遍历JSON数组，建立任务信息
            $(array).each(function(i){
                buildTask(array[i].builddate, array[i].id, array[i].task);    
            });
        }
    );
}

//根据日期、任务编号、任务内容在页面上创建任务节点
function buildTask(buildDate, taskId, taskInfo) {
    $("#"+buildDate).parent().append("<div id='task" + taskId + "' class='task' onclick='editTask(this)'>" + taskInfo + "</div>");
}

//判断是否闰年返回每月天数
function getDays(month, year) {
    if (1 == month) {
        if (((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400)) {
            return 29;
        } else {
            return 28;
        }
    } else {
        return daysInMonth[month];
    }
}

//显示上月日程
function prevMonth() {
    if ((month - 1) < 0) {
        month = 11;
        year--;
    } else {
        month--;
    }
    fillBox();              //填充每日单元格
}

//显示下月日程
function nextMonth() {
    if((month + 1) > 11) {
        month = 0;
        year++;
    } else {
        month++;
    }
    fillBox();              //填充每日单元格
}

//显示本月日程
function thisMonth() {
    year = today.year;
    month = today.month;
    fillBox();              //填充每日单元格
}

//更新年月提示
function updateDateInfo() {
    $("#dateInfo").html(year + "年" + (month + 1) + "月");
}

//打开新建任务box
function openAddBox(src) {
    $("#taskDate").html(src.id);                    //设置新建日期
    $("#taskInfo").val("");                         //初始化新建任务内容
    var left = getLeft(src) + 15;                   //设置左边距
    var top = getTop(src) + 15;                     //设置顶边距
    $("#addBox").left(left).top(top).slideDown();   //显示新建任务box
}

//向服务器提交新任务信息
function addTask() {
    var taskInfo = $("#taskInfo").val();                //获取任务信息
    //检查任务信息是否为空
    if ($.trim(taskInfo)=="") {
        alert("请输入信息");
    } else {
        var buildDate = $("#taskDate").html();          //获取任务日期
        
        var state=$("#state").val();

         
        $.post("task_addTask.action",                          //服务器页面地址
            {
                context:taskInfo,
                date:buildDate,
                state:state
            },
            function(taskId) {                          //回调函数
                buildTask(buildDate, taskId, taskInfo); //建立任务节点
                closeAddBox();                          //关闭新建任务box
                location.replace(location);
            });
    }
}

//关闭新建任务box
function closeAddBox() {
    $("#addBox").slideUp();
}

//打开编辑任务box
function editTask(src) {
    $("#taskId").val(src.id.substr(4));             //对任务编号隐藏域赋值
    $("#editTaskInfo").val(src.innerHTML);          //设置编辑内容
    var left = getLeft(src) + 15;                   //设置左边距
    var top = getTop(src) + 15;                     //设置顶边距
    $("#editBox").left(left).top(top).slideDown();  //显示编辑任务box
}

//删除任务
function delTask() {
    var taskId = $("#taskId").val();                //获取任务编号
    $.post("task_delTask.action",                          //服务器页面地址
        {
            id: taskId                          //任务编号参数
        },
        function() {                                //回调函数
            $("#task" + taskId).remove();           //在页面删除任务节点
            closeEditBox();                         //关闭编辑box
        }
    );
}

//关闭编辑box
function closeEditBox() {
    $("#editBox").slideUp();
}

//更新任务信息
function updateTask() {
    var taskId = $("#taskId").val();                //任务编号
    var taskInfo = $("#editTaskInfo").val();        //任务内容
    //检查任务信息是否为空
    if ($.trim(taskInfo)=="") {
        alert("请输入任务信息。");
    } else {
        $.post("task_updateTask.action",                      //服务器页面地址
            {
                id: taskId,                     //任务编号
                context: taskInfo                  //任务信息参数
            },
            function() {                            //回调函数
                $("#task" + taskId).html(taskInfo); //更新页面任务内容
                closeEditBox();                     //关闭编辑box
            }
        );
    }
}

//返回对象对页面左边距
function getLeft(src){
    var obj = src;
    var objLeft = obj.offsetLeft;
    while(obj != null && obj.offsetParent != null && obj.offsetParent.tagName != "BODY"){
        objLeft += obj.offsetParent.offsetLeft;
        obj = obj.offsetParent;
    }
    return objLeft;
}

//返回对象对页面上边距
function getTop(src){
    var obj = src;
    var objTop = obj.offsetTop;
    while(obj != null && obj.offsetParent != null && obj.offsetParent.tagName != "BODY"){
        objTop += obj.offsetParent.offsetTop;
        obj = obj.offsetParent;
    }
    return objTop;
}
</script>
</head>

<h1>日程表</h1>
<form method="post">
	<!-- 新建任务box -->
	<div id="addBox">
		<div id="taskDate"></div>
		内容：<input type="text" id="taskInfo" size="40">
		<div class="taskBtn">
			<select id="state">
				<option value=0>私人</option>
				<option value=1>公开</option>
			</select> <input type="button" value="创建新任务" onclick="addTask()"> <input
				type="button" value="取消" onclick="closeAddBox()">
		</div>
	</div>

	<!-- 编辑任务box -->
	<div id="editBox">
		<input type="hidden" id="taskId"> 内容：<input type="text"
			id="editTaskInfo" size="40">asd

		<div class="taskBtn">
			<input type="button" value="更新任务信息" onclick="updateTask()"> <input
				type="button" value="删除该任务" onclick="delTask()"> <input
				type="button" value="取消" onclick="closeEditBox()">
		</div>
	</div>
</form>
<!-- 日历表格 -->
<table id="calTable">
	<tr>
		<td colspan="7"><input type="button" value="上月"
			onclick="prevMonth()"> <input type="button" value="本月"
			onclick="thisMonth()"> <input type="button" value="下月"
			onclick="nextMonth()"> <span id="dateInfo"></span></td>
	</tr>
	<tr>
		<td class="day">周日</td>
		<td class="day">周一</td>
		<td class="day">周二</td>
		<td class="day">周三</td>
		<td class="day">周四</td>
		<td class="day">周五</td>
		<td class="day">周六</td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox0"></td>
		<td class="calBox" id="calBox1"></td>
		<td class="calBox" id="calBox2"></td>
		<td class="calBox" id="calBox3"></td>
		<td class="calBox" id="calBox4"></td>
		<td class="calBox" id="calBox5"></td>
		<td class="calBox sat" id="calBox6"></td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox7"></td>
		<td class="calBox" id="calBox8"></td>
		<td class="calBox" id="calBox9"></td>
		<td class="calBox" id="calBox10"></td>
		<td class="calBox" id="calBox11"></td>
		<td class="calBox" id="calBox12"></td>
		<td class="calBox sat" id="calBox13"></td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox14"></td>
		<td class="calBox" id="calBox15"></td>
		<td class="calBox" id="calBox16"></td>
		<td class="calBox" id="calBox17"></td>
		<td class="calBox" id="calBox18"></td>
		<td class="calBox" id="calBox19"></td>
		<td class="calBox sat" id="calBox20"></td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox21"></td>
		<td class="calBox" id="calBox22"></td>
		<td class="calBox" id="calBox23"></td>
		<td class="calBox" id="calBox24"></td>
		<td class="calBox" id="calBox25"></td>
		<td class="calBox" id="calBox26"></td>
		<td class="calBox sat" id="calBox27"></td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox28"></td>
		<td class="calBox" id="calBox29"></td>
		<td class="calBox" id="calBox30"></td>
		<td class="calBox" id="calBox31"></td>
		<td class="calBox" id="calBox32"></td>
		<td class="calBox" id="calBox33"></td>
		<td class="calBox sat" id="calBox34"></td>
	</tr>
	<tr>
		<td class="calBox sun" id="calBox35"></td>
		<td class="calBox" id="calBox36"></td>
		<td class="calBox" id="calBox37"></td>
		<td class="calBox" id="calBox38"></td>
		<td class="calBox" id="calBox39"></td>
		<td class="calBox" id="calBox40"></td>
		<td class="calBox sat" id="calBox41"></td>
	</tr>
</table>
</body>
</html>
