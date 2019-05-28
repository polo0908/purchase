<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>任务详情</title>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="keywords" content="HTML,CSS,XML,JavaScript">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
  <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
  <style type="text/css">
		body{
			font:14px/28px "微软雅黑";
			background-color: #FAFAFA;
		}
		body>h2{
			text-align: center;
			font-size: 24px;
			color: #222;
    		font-weight: 100;
		}
		.contact *:focus{outline :none;}
		.contact{
			width: 1200px;
		    background: #fff;
		    margin: 10px auto 40px;
		    padding: 0;
		    -webkit-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -moz-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -ms-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -o-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    height: 100%;
		    overflow: hidden;
		}
		.contact ul {
			/* width: 650px;
			margin: 0 auto; */
		}
		.contact ul li{
		    width: 46%;
    		float: left;
			list-style: none;
		}
		.contact ul li.wid-all{width: 100%;}
		.contact ul li label {
			width:170px;
			display: inline-block;
			float: left;
			color: #555;
		}
		.contact ul li span{
			color: #555;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 220px;
			height: 25px;
			border :1px solid #aaa;
			padding: 3px 8px;
			border-radius: 5px;
		}
		.btn{
			position: relative;
			left: 300px;
		}
		.tips{
			color: rgba(0, 0, 0, 0.5);
			padding-left: 10px;
		}
		.tips_true,.tips_false{
			padding-left: 10px;
		}
		.tips_true{
			color: green;
		}
		.tips_false{
			color: red;
		}
		.task-icon-left{
			width: 30%;
			float: left;
			min-height: 250px;
			position: relative;
		}
		.task-icon-left img{
			display: inline-block;
			position: absolute;
			left: -9px;
		    top: 26px;
			/* background: url(../img/task-detail-list1.png) center no-repeat; */
		}
		.task-icon-left:after{
			content: "";
			width: 30px;
			height: 100%;
			position: absolute;
			right: 0;
			background: url(../img/ta-right.png) center no-repeat;
		}
		.task-li-right{
			width: 68%;
			float: left;
			margin-top: 40px;
			padding-right: 20px;
    		padding-bottom: 30px;
		}
		.mission-status{
			width: 1200px;
		    margin: 0 auto;
		    padding-bottom: 100px;
		    overflow: hidden;
		}
		.mission-status input{
			color: #777777;
			padding: 7px 37px;
			float: right;
			margin-left: 30px;
			background-color: #fff;
			border: 1px solid #DDDDDD;
			border-radius: 4px;
		}
		.mission-status input:hover{
			color: #fff;
			background-color: #4B67E7;
			border-color: #4B67E7;
		}
		.mission-status input.complete-mission{
			color: #fff;
			background-color: #4B67E7;
			border-color: #4B67E7;
		}
		.mission-status input.complete-mission:hover{
			background-color: #4362C5;
		}
		.expected-finish-time{
			display: inline-block;
		    position: relative;
		    height: 33px;
		    min-width: 154px;
		    float: right;
		}
		.expectFinishTimeHtml{
		display: none;
			width: 300px;
			height: 200px;
			border: 1px solid #ccc;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    margin-top: -25px;
		    margin-left: -150px;
		    background-color: #fff;
		}
		.expectFinishTimeHtml input[type="text"]{
		    color: #777777;
		    padding: 8px 12px;
		    background-color: #fff;
		    border: 1px solid #DDDDDD;
		    vertical-align: bottom;
		}
		/* .expectFinishTimeHtml{
			display: none;
			position: absolute;
			top: 33px;
			left:0;
		}
		.expectFinishTimeHtml input{
			display: inline-block;
		    max-width: 153px!important;
		    text-align: center;
		    padding: 7px 0;
		    margin: 5px 0;
		}
		.expectFinishTimeHtml input[type="submit"]{
			margin: 0;
		    padding: 7px 23px;
		    min-width: 155px;
		} */
  </style>
</head>
<body>
    <h2>任务详情</h2>
    <input type="hidden" name="userName" id="userName" value="${userName}">
    <input type="hidden" name="roleNo" id="roleNo" value="${roleNo}">
    <input type="hidden" name="userId" id="userId" value="${userId}">
    <input type="hidden" name="id" id="id" value="${projectTask.id}">
	<div class="contact">
		<div class="task-icon-left">
			<c:if test="${projectTask.taskStatus eq '0'}"><img src="../img/task-detail-list1.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '1'}"><img src="../img/task-detail-list2.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '2'}"><img src="../img/task-detail-list4.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '3'}"><img src="../img/task-detail-list3.png" /></c:if>
		</div>
		<div class="task-li-right">
			<ul>
				<li>
					<label>任务发起人：</label>
					<span readonly="readonly">${projectTask.initiator}</span>
					<input type="hidden" name="projectTaskId" id="projectTaskId" value="${projectTask.id}">
				</li>
				<li>
					<label>任务发起时间：</label>
					<span><fmt:formatDate value="${projectTask.startTime}"/></span>
				</li>
				<li>
					<label>任务接受人：</label>
					<span readonly="readonly">${projectTask.accepter}</span>
				</li>
				<li>
					<label>要求任务完成时间：</label>
					<span><fmt:formatDate value="${projectTask.finishTime}"/></span>
				</li>
				<li>
					<label>相关项目号：</label>
					<span readonly="readonly">${projectTask.projectNo}</span>
				</li>
				<li>
					<label>&nbsp;</label>
					<span readonly="readonly"></span>
				</li>
				<li class="wid-all">
					<label>任务简述：</label>
					<span readonly="readonly">${projectTask.description}</span>
				</li>
				<c:if test="${projectTask.urgentReason!='' and projectTask.urgentReason!=null}">
					<li class="wid-all">
						<label>如果紧急,请给出理由：</label>
						<span>${projectTask.urgentReason}</span>
					</li>
				</c:if>

			</ul>
			<span class="tips" id="urgentReasonHtml"></span>
		</div>
	</div>
	<div class="mission-status">
	    <c:if test="${projectTask.taskStatus eq '0'}"><!--任务未完成 -->
		    <input type="submit" class="complete-mission" onclick="finishProjectTask()" value="任务完成"/>
			<input type="submit" onclick="cancelProjectTask()" value="任务取消" />
			<input type="submit" onclick="stopProjectTask()" value="任务暂停"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '1' || projectTask.taskStatus eq '3'}"><!--任务已完成或者取消 -->
		  <input type="submit" onclick="restartProjectTask()"value="重启任务"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '2'}"><!-- 任务暂停 -->
	      <input type="submit" onclick="cancelProjectTask()"value="任务取消"/>
		  <input type="submit" onclick="restartProjectTask()"value="重启任务"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '0'}"><!--任务未完成 -->
	    <div class="expected-finish-time">
	    	<input type="submit" onclick="expectFinishTime()" value="预计完成时间"/>
	    	<!-- <div class="expectFinishTimeHtml" id="expectFinishTimeHtml">
			   <label>预计完成时间:</label>
			   <input type="text" name="expectFinishTime" id="expectFinishTime" value="" class="input-text form-control date-time" placeholder="请点击选择完成时间"/><span class="tips" id="finishTimeHtml"></span>
			   <input type="submit" value="确认" onclick="subExpectFinishTime()"/>
			</div> -->
	    </div>
	    </c:if>
	    
	    <input name="" style="height:28px;line-height:28px;background-color: #027cff;border: none;color: #fff;padding: 1px 16px; margin-left: 6px;border-radius: 2px;"type="submit" value="返回主页" onClick="goBack()" id="goBackHtml"/>
	    <c:if test="${not empty projectTask.meetingNo}">
	      <input name="" style="height:28px;line-height:28px;background-color: #027cff;border: none;color: #fff;padding: 1px 16px; margin-left: 6px;border-radius: 2px;"type="submit" value="关联会议" onClick="relatedMeeting('${projectTask.meetingNo}')" id="goBackHtml"/>
	    </c:if>
	 </div>
	 <div class="expectFinishTimeHtml" id="expectFinishTimeHtml">
	   <!-- <label>预计完成时间:</label> -->
	   <input type="text" name="expectFinishTime" id="expectFinishTime" value="" class="input-text form-control date-time" placeholder="请点击选择完成时间"/><span class="tips" id="finishTimeHtml"></span>
	   <input type="submit" value="确认" onclick="subExpectFinishTime()"/>
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
		startYear: currYear, //开始年份
		endYear: currYear + 50 //结束年份
	};
	$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
})
</script>
<script type="text/javascript">
function relatedMeeting(meetingNo){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var meetingNo=meetingNo;
	window.location.href="${ctx}/meetingRecord/selectMeetingRecordDetails?meetingNo="+meetingNo+"&roleNo="+roleNo+"&userName="+userName+"&userId="+userId;	
}
function expectFinishTime(){
	$("#expectFinishTimeHtml").toggle();
}
/**
 * 提交预计完成时间
 */
function subExpectFinishTime(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var id=$("#id").val();
	var expectFinishTime=$("#expectFinishTime").val();
	$.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateExpectFinishTime",           
	     data:{
	    	 expectFinishTime:expectFinishTime,
	    	 id:id
	     },              
	     success:function(data){  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  $("#expectFinishTimeHtml").hide()
				  alert('更新成功')
				  
				 // window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });	
}
//返回主页
function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}
//任务重启 0
function restartProjectTask(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
    var projectTaskId=$("#projectTaskId").val();
    var taskStatus=0;
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName
	     },              
	     success:function(data){  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });	
}
//完成任务按钮 任务完成  1
function finishProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
     var taskStatus=1;
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName
	     },              
	     success:function(data){  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
//取消任务按钮 取消任务  3
function cancelProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
     var taskStatus=3;
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName
	     },              
	     success:function(data){  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
//暂停任务按钮 暂停任务2
function stopProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
     var taskStatus=2;
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName
	     },              
	     success:function(data){  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.href="${ctx}/projectTask/projectTaskList";
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
</script>
</html>

