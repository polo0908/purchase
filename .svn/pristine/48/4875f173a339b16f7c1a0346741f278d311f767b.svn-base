<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>会议录入</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/mobiscroll_date.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>
<style type="text/css">
body {
	font: 14px/28px "微软雅黑";
	background-color: #FAFAFA;
}

body>h2 {
	text-align: center;
	font-size: 24px;
	color: #222;
	font-weight: 100;
}

.contact *:focus {
	outline: none;
}

.contact {
	width: 1200px;
	height: 800px;
	background: #fff;
	margin: 40px auto;
	padding: 10px;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}

.contact ul {
	/* width: 650px; */
	margin: 0 auto;
}

.contact ul li {
	/* border-bottom: 1px solid #dfdfdf; */
	list-style: none;
	padding: 10px;
}

.contact ul li label {
	width: 100px;
	display: inline-block;
	float: left;
	text-align: right;
	margin-right: 20px;
	color: #555555;
}

.contact ul li:nth-child(1) label, .contact ul li:nth-child(2) label,
	.contact ul li:nth-child(5) label {
	line-height: 34px;
}

.contact ul li input[type=text], .contact ul li input[type=password] {
	width: 414px;
	height: 26px;
	border: 1px solid #ddd;
	padding: 3px 8px;
	font-size: 13px;
}

.contact ul li:nth-child(3) span {
	margin-right: 56px;
}

.contact ul li:nth-child(3) input[type="radio"] {
	margin: 0;
	margin-right: 3px;
	vertical-align: middle;
	cursor: pointer;
}

:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #C2C2C2;
	opacity: 1;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #C2C2C2;
	opacity: 1;
}

input:-ms-input-placeholder, textarea:-ms-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

input::-o-input-placeholder, textarea::-o-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

.contact ul li input[type="text"]:focus {
	border-color: #c00;
}

.btn {
	position: relative;
	width: 100%;
	display: inline-block;
	text-align: center;
	margin-bottom: 40px;
}

.btn input[type=submit] {
	padding: 7px 46px;
	background-color: #4B67E7;
	border: none;
	font-size: 14px;
	color: #fff;
}

.btn input[type=submit]:hover {
	background-color: #4362C5;
}

.meeting-notes {
	width: 640px;
	height: 107px;
	font-size: 13px;
	border: 1px solid #ddd;
	padding: 3px 8px;
	resize: none;
}

.tips {
	color: rgba(0, 0, 0, 0.5);
	padding-left: 10px;
}

.tips_true, .tips_false {
	padding-left: 10px;
}

.tips_true {
	color: green;
}

.tips_false {
	color: red;
}

.project-task-list li input[type=text]:nth-child(2) {
	margin-left: 120px;
}

.project-task-list li:first-child input[type=text] {
	margin-left: 0;
}
/* .project-task-list li input[type=text]:last-child{
			width: 198px;
		} */
.project-task-list li .box-executive input[type="text"] {
	width: 190px;
	font-size: 13px;
	color: #555;
	display: block;
	vertical-align: bottom;
	background: #fff;
	border: 1px solid #DDDDDD;
	text-align: left;
	padding: 3px 12px;
	cursor: pointer;
}

.project-task-list li em {
	display: inline-block;
	width: 14px;
	height: 10px;
	background: url(../img/arrow-down.png) right center no-repeat;
	position: absolute;
	background-size: cover;
	left: 190px;
	margin-top: -20px;
	z-index: 5;
	cursor: pointer;
	-webkit-transition: all .3s;
	-moz-transition: all .3s;
	-ms-transition: all .3s;
	-o-transition: all .3s;
	transition: all .3s;
}

.project-task-list li em.arrow-icon {
	transform: rotate(180deg);
	-ms-transform: rotate(180deg);
	-moz-transform: rotate(180deg);
	-webkit-transform: rotate(180deg);
	-o-transform: rotate(180deg);
	/* background: url(../img/arrow-up.png) right center no-repeat;
		    background-size: cover; */
}

.box-executive {
	width: 216px;
	margin: 0;
	position: absolute;
	margin-left: 10px;
	color: #5E5E5E;
	font-size: 12px;
	display: inline-block;
}
/* .box-executive:before{
			content: "";
			display: inline-block;
		    width: 14px;
		    height: 10px;
		    background: url(../img/arrow-down.png) right center no-repeat;
		    background-size: cover;
		    position: absolute;
		    right: 12px;
		    top: 12px;
		    z-index: 5;
		    cursor: pointer;
		    -webkit-transition: all .3s;
		    -moz-transition: all .3s;
		    -ms-transition: all .3s;
		    -o-transition: all .3s;
    		transition: all .3s;
		} */
.box-executive input {
	line-height: 30px;
}

.box-executive ul {
	width: 100%;
	height: 150px;
	position: absolute;
	top: 34px;
	left: 0;
	z-index: 10;
	padding: 0;
	background-color: #FFF;
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
}

.updown-list {
	display: none;
	width: 432px;
	height: 190px;
	overflow: hidden;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}
/* .list{display: none;} */
.box-executive .list>li, .box-executive .list>li ol li {
	/* width: 300px;
		    height: 30px;
    		line-height: 30px; */
	font-size: 13px;
	color: #666;
	padding: 1px 12px;
	cursor: pointer;
	/* position: relative; */
}

.box-executive .list>li:before {
	content: "";
	display: inline-block;
	width: 10px;
	height: 15px;
	background: url(../img/arrow-right.png) center no-repeat;
	background-size: cover;
	position: relative;
	left: 186px;
	top: 3px;
}

.box-executive .list>li:hover, .box-executive .list>li ol li:hover {
	color: #FFF;
	background-color: #027CFF;
}

.list>li.active ol {
	display: block;
}

.list>li:hover ol {
	display: block;
}

.list>li ol {
	display: none;
	width: 216px;
	height: 150px;
	position: absolute;
	top: 0;
	left: 216px;
	z-index: 20;
	padding: 0;
	background-color: #FFF;
	overflow-y: scroll;
	overflow-x: hidden;
}

.updown-list>div {
	font-size: 20px;
	width: 432px;
	height: 40px;
	line-height: 40px;
	background-color: #FFF;
	margin-top: 150px;
	text-align: right;
	padding: 3px 12px;
	border-top: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	cursor: pointer;
	position: relative;
	z-index: 30;
}
</style>
</head>
<body>
	<h2 style="text-align: center;">会议录入</h2>
	<input type="hidden" name="userName" id="userName" value="${userName}">
	<input type="hidden" name="roleNo" id="roleNo" value="${roleNo}">
	<input type="hidden" name="userId" id="userId" value="${userId}">
	<form id="addMeetingProjectTask" onsubmit="return false;" method="post">
		<div class="contact" style="margin-top: 10px;">
			<div>
				<ul>
					<li><label for="initiator">录入人</label> <input type="text"
						name="initiator" id="initiator" value="${userName}" /> <input
						name=""
						style="height: 28px; line-height: 28px; background-color: #027cff; border: none; color: #fff; padding: 1px 16px; margin-left: 6px; border-radius: 2px;"
						type="submit" value="返回主页" onClick="goBack()" id="goBackHtml" /></li>
					<li><label for="projectNo">相关项目号</label> <input type="text"
						name="projectNo" id="projectNo" value="${projectNo}"
						placeholder="请输入项目号" /><span class="tips" id="projectNoHtml"></span>
					</li>
					<li><label>会议名称</label> <span>
					<input type="radio" name="meetingName" checked style="margin-left: 0;" value="项目启动会" />项目启动会</span>
						<span><input type="radio" name="meetingName" value="项目周进展会" />项目周进展会</span>
						<span><input type="radio" name="meetingName" value="样品验货会" />样品验货会</span>
						<span><input type="radio" name="meetingName" value="大货验货会" />大货验货会</span>
						<span><input type="radio" name="meetingName" value="质量分析会" />质量分析会</span>
						<span><input type="radio" name="meetingName" value="周会" />周例会</span>
						<span><input type="radio" name="meetingName" value="其他" />其他</span>
					</li>
					<li><label for="meetingDescribe">会议纪要</label> <textarea
							class="meeting-notes" name="meetingDescribe" id="meetingDescribe"
							placeholder="请输入会议纪要" /></textarea><span class="tips"
						id="meetingDescribeHtml"></span></li>
				</ul>
				<ul id="projectTaskList" class="project-task-list">
					<li><label>会议任务</label> <input type="text" name="meetingTask"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter"><em></em>
							<div class="updown-list">
						
							</div>
						</div></li>
					<li><label></label> <input type="text" name="meetingTask1"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter1"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>
					<li><label></label> <input type="text" name="meetingTask2"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter2"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>

					<li><label></label> <input type="text" name="meetingTask3"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter3"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>

					<li><label></label> <input type="text" name="meetingTask4"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter4"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>

					<li><label></label> <input type="text" name="meetingTask5"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter5"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>

					<li><label></label> <input type="text" name="meetingTask6"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter6"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>

					<li><label></label> <input type="text" name="meetingTask7"
						placeholder="请输入会议任务">
						<div class="box-executive">
							<input type="text" value="执行人" name="accepter7"><em></em>
							<div class="updown-list">
								
							</div>
						</div></li>
				</ul>
			</div>

		</div>
		<b class="btn"><input type="submit" class="subm-but"
			onclick="submitMeetingProjectTask()" value="录入" /><span class="tips"
			id="subHtml"></span>
	</form>
	
	<div id="staffMember" style="display:none" >
	    		<ul class="list">
									<li class="active"><span>技术部</span>
										<ol>
											<li>LiuZhongYi</li>
											<li>zhangqun</li>
											
											<li>OwenCui</li>
											<li>YanRongchao</li>
											<li>wangweiping</li>
										</ol></li>
									<li><span>销售部</span>
										<ol>
											<li>jerrylong</li>

											<li>amyzhao</li>
											<li>tina</li>
											<li>paul</li>
											<li>FionaYan</li>
						
											<li>Susiehuang</li>
											<li>minniewu</li>
											<li>SherryZhou</li>
									
											<li>LynnYuan</li>
											<li>elainesheng</li>
											<li>ivywu</li>
											<li>kathypan</li>
											<li>chloema</li>
											<li>annazhu</li>
											<li>KateGong</li>
											<li>ShirleyYu</li>
											<li>NataliaLi</li>
											<li>jennyguo</li>
											<li>Kristinemei</li>
											<li>pandage</li>
											<li>chengmingkun</li>
											<li>MinnXu</li>
									
											<li>AndsXue</li>
										</ol></li>
									<li><span>采购部</span>
										<ol>
											<li>Jiangwenlong</li>
											<li>Bensonzhang</li>
											<li>ninazhao</li>
											<li>zhaoshuhao</li>
											<li>zhaoqiang</li>
							
											<li>xuping</li>
											<li>ThomasChen</li>
											<li>xuwei</li>
											<li>RogerQiu</li>
											<li>SISI</li>
											<li>ArnoZhou</li>
										</ol></li>
									<li><span>质检部</span>
										<ol>
											<li>yanggong</li>
											<li>wangjingjun</li>
											<li>maxiaolei</li>
											<li>litie</li>
											<li>zoumin</li>
											<li>wangzhuo</li>
											<li>zhangyouqing</li>
											<li>liuzikai</li>
											<li>zuoliang</li>
											<li>zhaochun</li>
										</ol></li>
								</ul>
								<div>
									<span class="close-ck" onclick="foc()">X</span>
								</div>
	
	</div>
	
</body>

<script type="text/javascript">
	$(function () {
		var currYear = (new Date()).getFullYear();	
		var opt={};
		opt.date = {preset : 'date'};
		opt.datetime = {preset : 'datetime'};
		opt.time = {preset : 'time'};
		opt.default = {
			theme: 'android-ics light', //皮肤样式
			display: 'modal', //显示方式 
			mode: 'scroller', //日期选择模式
			dateFormat: 'yyyy-mm-dd',
			lang: 'zh',
			showNow: true,
			nowText: "今天",
			startYear: currYear - 1, //开始年份
			endYear: currYear + 50 //结束年份
		};
		$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
		
	   var staffMember = $('#staffMember').html()
	   $('.updown-list').html(staffMember)
	   
	   $(".list ol li").click(function(event) {
			var game1 = $(this).text()
			console.log($(this).parents(".updown-list").siblings("input").length);
			$(this).parents(".updown-list").siblings("input").val(game1);
			foc();
		});
	   
	})
	Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
<script type="text/javascript">
function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}
 function submitMeetingProjectTask(){
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var projectNo=$("#projectNo").val();
	
	 var meetingType ='';
    $("#addMeetingProjectTask input[name=meetingName]").each(function(){
     if($(this).prop('checked')){
    	 meetingType = $(this).val()
     }
	  
	 
    })
 
	 if(meetingType!='晨会'&&meetingType!='其他'){
		 if(projectNo==null || projectNo=="" ||projectNo==undefined){
				$("#projectNoHtml").html('<font class="tips_false">请输入相关项目号</font>');
				return false;
			 }
	 }
	 

	 
	$(".subm-but").attr("disabled", true).css("background-color", "#999");
	 $("#addMeetingProjectTask").ajaxSubmit({
		type : "post",
		url : "${ctx}/meetingRecord/addMeetingRecord",
		dataType : "text",
		success : function(result) {
			var json = eval("(" + result + ")");
			  if(json.ok){
				  window.location.href="${ctx}/meetingRecord/selectMeetingRecordList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				 $("#subHtml").html('<font class="tips_false">录入失败</font>');
				 $(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
			  }    
		},
		error : function() {
			$("#subHtml").html('<font class="tips_false">录入失败</font>');
			$(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
		}
		
		
	});
}
 
$(".box-executive input,.box-executive em").click(function(event) {
	$(this).parents(".project-task-list").find(".updown-list").slideUp(30).end().end().siblings(".updown-list").slideDown();
	$(this).parent(".box-executive").find("em").addClass("arrow-icon");
	return false;
});
function foc(){
	$(".close-ck").parents(".updown-list").slideUp();
	$(".box-executive em").removeClass("arrow-icon");
}




</script>
</html>