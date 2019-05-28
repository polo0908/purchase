<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>PC会议录入</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../dist/css/jquery.atwho.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/mobiscroll_date.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../dist/js/jquery.caret.js"></script>
<script type="text/javascript" src="../dist/js/jquery.atwho.js"></script>
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
	height: 1070px;
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
	top: -95px;
	left: 185px;
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

.select_blank {
	background-color: #027CFF;
	padding: 2px 12px;
	text-decoration: none;
	color: #fff;
	border-radius: 2px;
	position: absolute;
	top: -40px;
	left: 0;
	font-size: 15px;
}

.contact {
	position: relative;
}

.select_blank:hover, .select_blank:hover {
	text-decoration: none;
	background-color: #4362C5;
	color: #fff;
}

.inputor {
	height: 50px;
}

#addMeetingProjectTask {
	margin-top: 60px;
}

h2 {
	margin-top: 25px;
}
</style>






<script type="text/javascript">

$(function(){
    $.fn.atwho.debug = true
   
   // var names = ["Jacob","Isabella","Ethan","Emma","Michael","Olivia","Alexander","Sophia","William","Ava","Joshua","Emily","Daniel","Madison","Jayden","Abigail","Noah","Chloe","你好","你你你"];
    
    var users = '${names}';
    var names = []; 
    names=eval("("+users+")"); 
    
    var names = $.map(names,function(value,i) {
      return {'id':i,'name':value};
    });	

    var at_config = {
      at: "@",
      data: names,
      headerTpl: '<div class="atwho-header">Member List<small>↑&nbsp;↓&nbsp;</small></div>',
      insertTpl: '@\${name}',
      displayTpl: "<li>\${name}</li>",
      limit: 200  
    }

    $inputor = $('.inputor').atwho(at_config);
    $inputor.caret('pos', 47);
    $inputor.focus().atwho('run');


  });

</script>



</head>
<body class="input_meeting_record">
	<h2 style="text-align: center; position: relative;">会议录入</h2>
	<input type="hidden" name="userName" id="userName" value="${userName}">
	<input type="hidden" name="roleNo" id="roleNo" value="${roleNo}">
	<input type="hidden" name="userId" id="userId" value="${userId}">
	<form id="addMeetingProjectTask" onsubmit="return false;" method="post">
		<input type="hidden" id="fileName" name="fileName"> <input
			type="hidden" id="filePath" name="filePath">
		<div class="contact" style="margin-top: 10px;">
			<a class="select_blank" target="_blank"
				href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
			<div>
				<ul class="ul_top">
					<li><label for="initiator">录入人</label> <input type="text"
						name="initiator" id="initiator" value="${userName}" /></li>
					<li><label for="projectNo">相关项目号</label> SHS<input type="text"
						name="projectNo" id="projectNo"
						value="${fn:replace(fn:toUpperCase(projectNo), 'SHS', '')}"
						placeholder="请输入项目号" /><span class="tips" id="projectNoHtml"></span>
					</li>
					<li class="meet_name_li"><label>会议名称</label>
					<div class="radios">
							<label><input type="radio" name="meetingName" checked
								style="margin-left: 0;" value="项目启动会" />项目启动会</label> <label><input
								type="radio" name="meetingName" value="项目周进展会" />项目周进展会</label> <label><input
								type="radio" name="meetingName" value="样品分析会"
								onchange="selectByProjectNo(0)"
								<c:if test="${type == 0}">checked</c:if> />样品分析会</label> <label><input
								type="radio" name="meetingName" value="大货分析会"
								onchange="selectByProjectNo(23)"
								<c:if test="${type == 23}">checked</c:if> />大货分析会</label> <label
								class="checked_span"><input type="radio"
								name="meetingName" value="质量分析会" />其他质量分析会</label> <label><input
								type="radio" name="meetingName" value="周会" />周例会</label> <label><input
								type="radio" name="meetingName" value="重要项目进度评审会" />重要项目进度评审会</label> <label><input
								type="radio" name="meetingName" value="重要项目问题解决会" />重要项目问题解决会</label> <label><input
								type="radio" name="meetingName" value="其他" />其他</label>
						</div></li>
					<li class="report" style="display: none;"><label>针对的质检报告</label>
						<div class="sels">
							<select class="form-control" id="quality_report" name="reportId">
								<option></option>
							</select> <span style="color: red;">选择对应的质检报告</span>
						</div></li>
					<li><label for="meetingDescribe">会议纪要</label> <textarea
							class="meeting-notes" name="meetingDescribe" id="meetingDescribe"
							placeholder="请输入会议纪要" /></textarea><span class="tips"
						id="meetingDescribeHtml"></span></li>
					<li class="quality" style="display: none;"><label>附件上传</label>
						<input type="file" name="files" onchange="upload(this)"> <span
						style="color: red;">(质量分析会请上传开会所用的质量分析表)</span></li>
					<li class="technical_support"><label>选择技术支持</label>
						<div class="sels">
							<select class="form-control" id="technician" name="technician">
								<option></option>
								<option>wangweiping</option>
							</select>
							<p style="color: red;">
								A/B级项目，必须选择技术支持，现进行中项目：wangweiping担当<span>${count1}</span>个。)
							</p>
							<p style="color: red;">
								非A/B级项目，如有必要也可选择技术支持，现进行中项目：wangweiping担当<span>${count1}</span>个。)
							</p>
						</div></li>
				</ul>
				<div class="wrap_ul">
					<ul id="projectTaskList" class="project-task-list">
						<li><label>会议任务</label> <span style="color: red;">注意：可直接书写任务内容+空格+@任务人(可加多个任务人，会相同信息发给每个任务人)</span>

						</li>
						<li>
							<!--  <span style="color:red;">注意：可通过空格+@选择任务接受人</span><br>	 -->
							<textarea name="meetingTask"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label>
						</li>
						<li><label></label> <textarea name="meetingTask1"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo1" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>
						<li><label></label> <textarea name="meetingTask2"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo2" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>

						<li><label></label> <textarea name="meetingTask3"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo3" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>

						<li><label></label> <textarea name="meetingTask4"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo4" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>

						<li><label></label> <textarea name="meetingTask5"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo5" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>

						<li><label></label> <textarea name="meetingTask6"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo6" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>

						<li><label></label> <textarea name="meetingTask7"
								class="meeting-notes inputor display_block"
								placeholder="请输入会议任务"></textarea> <label class="display_block"><input
								type="checkbox" name="isVideo7" value="0"
								onchange="changeVideo(this)" />本任务需要视频</label></li>
					</ul>
				</div>
			</div>

		</div>
		<div class="last_div">

			<!-- 			<a href="###" onclick="send_mail(this)" id="send_mail">调用客户端发送所有任务内容和会议纪要给项目组内成员(已上传的附件下载链接也会写在邮件正文中)</a><br/> -->
			<!-- 			<span>您可以先点击本按钮发送邮件，然后点击录入，也可以先点击录入，在浏览会议详情的时候再发送邮件</span> -->
			<!-- 			<span class="s1">质量分析会的内容会在点击录入后自动进入到客户投诉监管页</span> -->
			<div class="btn">
				<input type="submit" class="subm-but"
					onclick="submitMeetingProjectTask()" value="录入" /><span
					class="tips" id="subHtml"></span>
			</div>
		</div>
	</form>



</body>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
// 技术支持控制显示
$('.meet_name_li input').click(function(){
	var val = $(this).val();
	if(val == '项目启动会'){
		$('.technical_support').show();
	}else{
		$('.technical_support').hide();
	}
	if(val!='样品分析会' || val!='大货分析会'){
		$('.report').hide();
	}
	if(val == '质量分析会'){
		$('.quality').show();
	}else{
		$('.quality').hide();
	}
});



   //质检报告id
   var reportId = '${reportId}';


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
			$(this).parents(".updown-list").siblings("input").val(game1);
			foc();
		});
   
	   
	   
	 //页面加载的时候根据类型判断会议类型
	   var type='${type}';
	   if(type || type == 0){
	      if(type == '0' || type == '23'){
	   	   selectByProjectNo(type);
	      }	
	   }
     	
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
	 if(!projectNo){
		   layer.msg("项目号不能为空",{time:2000});  
		   return false;
     }else{
	   //自动加上SHS，防止自动录入shs
	   projectNo = projectNo.toUpperCase().replace("SHS","");
	   projectNo = "SHS"+projectNo;
	   $('#projectNo').val(projectNo);
     }
	 
	
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


//上传方法
function upload(obj){
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadComplaint",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var filePath = result.data.path;
					var fileName = result.data.originalFilename;
// 				    $(obj).next().text(fileName);
				    //1：新增上传
			    	$('#filePath').val(filePath);
					$('#fileName').val(fileName);					
				}
			},
			error : function() {
				
			}
		});
}



//发送邮件
function send_mail(obj){
	
	var projectNo = $('#projectNo').val();
	 if(!projectNo){
		   layer.msg("项目号不能为空",{time:2000});  
		   return false;
     }else{
	   //自动加上SHS，防止自动录入shs
	   projectNo = projectNo.toUpperCase().replace("SHS","");
	   projectNo = "SHS"+projectNo;
    }
	var meetingDescribe = $('#meetingDescribe').val();
	var meetingName = $("input[type='radio'][name='meetingName']:checked").val();
	
    $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/queryMail",           
	     data:{
	    	 projectNo:projectNo
	     },              
	     success:function(json){
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
				  var sellMail = json.data.sellEmail;
				  var purchaseEmail = json.data.purchaseEmail;
				  var bossMail = json.data.boss;
				  var cc_mail = "cc=jieguangyang@sourcing-cn.com&cc=wangweiping@sourcing-cn.com&cc=jiangwenlong@sourcing-cn.com&cc=jerrylong@sourcing-cn.com&cc=nina@sourcing-cn.com"; 
				  var cc_mail_pc = "cc=jieguangyang@sourcing-cn.com,wangweiping@sourcing-cn.com,jiangwenlong@sourcing-cn.com,jerrylong@sourcing-cn.com,nina@sourcing-cn.com"; 
				  var sendMail = sellMail;
				  var sendMail_pc = sellMail;
				  if(purchaseEmail){
					  sendMail = purchaseEmail;
					  sendMail_pc = sellMail + "," +purchaseEmail;
					  cc_mail = cc_mail + "&cc=" + sellMail;
				  }
				  //A、B级项目添加老板抄送
				  if(bossMail){
					  cc_mail = cc_mail + "&cc=" +bossMail;
					  cc_mail_pc = cc_mail + "," +bossMail;
				  }
		          var body = meetingDescribe;
		          a = encodeURIComponent(meetingDescribe);
		          var link = "";
		          var subject = projectNo  + " "+ meetingName;
		          if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		        	  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+subject+'&body='+ body; 
	        	  } else if (/(Android)/i.test(navigator.userAgent)) {
	        		  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+subject+'&body='+ body; 
	        	  }else{
	        		  link = 'mailto:'+sendMail_pc+'?'+cc_mail_pc+'&subject='+subject+'&body='+ body; 
	        	  }				          
		          $('#share_link').attr('href',link);
		          
				  window.open(link);
			  }
	     }
	
	})
}

//质量分析会选择后显示下面
/* $('.last_div a,.last_div span').hide();
$('.meet_name_li input').click(function(){
	var check = $('.checked_span input').prop('checked');
	if(check == true){
		$('.last_div a,.last_div span').show();
	}else{
		$('.last_div a,.last_div span').hide();
	}
}) */




 //根据项目号查询
 function selectByProjectNo(type){

       var projectNo = $('#projectNo').val();
	   if(!projectNo){
		   layer.msg("请先输入项目号",{time:2000});  
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   }	   
	   
		  $.ajax({
			    type:"post",                   
			    url:"${ctx}/quality/queryByProjectNo",           
			    data:{
			    	  projectNo:projectNo,
			    	  type:type
			    	 },              
			    success:function(json){  
// 			    	var json = eval("(" + data + ")");
					if (json.ok) {
						var reports = json.data;
						if(reports){
							$('#quality_report option').not(":first").remove();
							for(var i=0;i<reports.length;i++){
								var time = formatDateTime(reports[i].createtime);
								if(reports[i].id == reportId){
									$('#quality_report').append('<option value="'+reports[i].id+'" selected>'+reports[i].user+','+time+'</option>');
								}else{
									$('#quality_report').append('<option value="'+reports[i].id+'">'+reports[i].user+','+time+'</option>');
								}
								
							}  
							$('#quality_report').parents('li').show();
						}				
					}else{
						layer.msg(data.message,{time:2000});  
					}
			    }
		})
   }


/**
 * 判断是否需要上传视频
 */
function changeVideo(obj){
	if($(obj).is(':checked')){
		$(obj).val(1);
	}else{
		$(obj).val(0);
	}
}






function formatDateTime(inputTime) {  
    var date = new Date(inputTime);
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;  
    second = second < 10 ? ('0' + second) : second; 
    return y + '/' + m + '/' + d;  
};
</script>
</html>




