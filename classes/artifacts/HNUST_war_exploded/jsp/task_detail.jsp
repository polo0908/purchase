<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String roleNo=request.getParameter("roleNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>任务详情</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div id="mask"></div>
		<input type="hidden" id="projectNo" value="${task.projectNo}">
		<input type="hidden" id="id" value="${task.id}">
		<input type="hidden" id="userName" value="${userName}">
		<input type="hidden" id="roleNo" value="<%=roleNo%>">
		<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" name="userName" value="<%=userName%>">
		<div class="detail">
		    <div class="go-back" onclick="goBack()"></div>
			<div class="detail-tit">任务详情页</div>
			<div class="detail-middle">
				<!--任务基本信息-->
				<div class="detail-one">
					<p class="detail-top">任务基本信息</p>
				       <dl class="index-list">
					     <dd>
								<p><span class="span-left">项&nbsp; 目 号： </span><span class="span-right porject-number">${task.projectNo}</span></p>
								<c:if test="${roleNo==100 or roleNo==99 or roleNo==98}">
									<p><span class="span-left">跟单销售： </span><span class="span-right project-person">${task.roleName}</span></p>
									<p><span class="span-left">采 &nbsp; &nbsp; &nbsp;购： </span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==5}">
									<p><span class="span-left">&nbsp;采  &nbsp; 购： </span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==6}">
									<p><span class="span-left">跟单销售： </span><span class="span-right project-person">${task.sellName}</span></p>
								</c:if>
								<p><span class="span-left">截止日期： </span><span class="span-right project-date">
								  <fmt:formatDate value="${task.endDate}" pattern="yyyy-MM-dd"/>
								  </span>
								 </p>
								<p><span class="span-left">任务节点： </span><span class="span-right project-person">
								    ${task.node==0?'无':'' }${task.node==1?'样品交货':''}${task.node==2?'大货交货':''}
								   </span>
								</p>
								<p><span class="span-left">任务状态： </span><span class="span-right project-person">
								    ${task.status==0?'未完成':'' }${task.status==1?'已完成':''} 
								   </span>
								</p>
								<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">${task.taskDemand}</span></p>
							</dd>
					 </dl>
				</div>
				<!--任务汇报情况-->
				<div class="detail-one">
					<p class="detail-top">进展汇报</p>
					<c:forEach var="taskReport" items="${task.taskReportList}">
						<div class="date-reason">
							<p><span class="span-left">汇报人：</span><span class="span-right ">${taskReport.reportName}</span></p>
							<p><span class="span-left">汇报进展：</span><span >${taskReport.taskReport}</span></p>
							<div class="pic-show">
								<span class="span-left">文件链接：</span>
								  <span><a href="#" onclick="openFile('http://112.64.174.34:10010/uploadimg/${taskReport.picUrl}')">${taskReport.picUrl}</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="key-search btn-search">
			            <% if(roleNo.equals("6")){%>
							<button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
						<%}%>
						<% if(roleNo.equals("5")){%>
							<button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
						    <button class="btn btn-primary" id="restartTask">重启任务</button>
						<%}%>
						<% if(roleNo.equals("100") || roleNo.equals("99")  || roleNo.equals("98")){%>
						    <button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
							<button class="btn btn-primary" id="delTask">删除任务</button>
							<button class="btn btn-primary" id="restartTask">重启任务</button>
						<%}%>
			   </div>
			    <div id="click-big">
		      	 <img src="../img/2.jpg"/>
	          	</div> 
			</div>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
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
			});
		</script>
		<script type="text/javascript">
			//底部导航弹出处理
			$('.footer-list-one').click(function(){
				$('#mask').show(100);
				$('.index-mask-one').show(300);
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-list-two').click(function(){
				$('#mask').show(100);
				$('.index-mask-two').show(300);
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
			//处理图片点击放大
			$(document).on('click','.pic-show img',function(){
				$('#click-big').show(100);
				var this_src=$(this).attr('src');
				$('#click-big img').attr('src',this_src);
				layer.open({
					type:1,
					skin:'img-big',
					title:'图片预览',
					area:['100%','50%'],
					anim:4,
					shadeClose:true,
					content:$("#click-big")
				})
			})
			//任务汇报
			$("#taskReport").click(function(){
				 var projectNo=$("#projectNo").val();
				 var id=$("#id").val();
				 var roleNo=$("#roleNo").val();
				 var userId=$("#userId").val();
				 var userName=$("#userName").val();
				 window.location.href="${ctx}/jsp/task_report.jsp?projectNo="+projectNo+"&id="+id+"&userId="+userId+"&roleNo="+roleNo+"&userName="+userName;
			})
			$("#task").click(function(){
				 var taskId=$("#id").val();
				 var userId=$("#userId").val();
				 var roleNo=$("#roleNo").val();
				 var userName=$("#userName").val();
				 var flag=1;
				  $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/updateTask",           
					    data:{
					    	 taskId:taskId,
					    	 flag:flag
					    	 },              
					    success:function(data){  
					      var json = eval("(" + data +")");
						  if(json.ok){
							  layer.msg(json.message,{time:2000});
							  window.location.href="${ctx}/task/taskDetails?id="+taskId+"&userId="+userId+"&roleNo="+roleNo+"&userName="+encodeURI(encodeURI(userName));	
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					    }
				 });  
			})
			//重启任务
			$("#restartTask").click(function(){
				 var projectNo=$("#projectNo").val();
				 var taskId=$("#id").val();
				 var flag=0;
				  $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/updateTask",           
					    data:{
					    	 taskId:taskId,
					    	 flag:flag
					    	 },              
					    success:function(data){  
					      var json = eval("(" + data +")");
						  if(json.ok){
							  layer.msg(json.message,{time:2000});
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					    }
				 });  
			})
			
			//删除任务
			$("#delTask").click(function(){
				 var projectNo=$("#projectNo").val();
				 var taskId=$("#id").val();
				 var userId=$("#userId").val();
				 var roleNo=$("#roleNo").val();
				 var userName=$("#userName").val();
				 $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/delTask",           
					    data:{
					    	 taskId:taskId
					    	 },              
					    success:function(data){  
					      var json = eval("(" + data +")");
						  if(json.ok){
							  window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&userName="+userName;
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					   }
				 });  
			})
			//一键返回列表页 
		 function goBack(){
		    var projectNo=$("#projectNo").val();
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			if(userName!=null && userName!=''){
				userName=$("#userName").val();
			}else{
				userName=$("userName").val();
			}
			window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&userId="+userId+"&roleNo="+roleNo+"&userName="+encodeURI(encodeURI(userName));	
		 }
			
		 function openFile(url){
	    	 window.location.href=encodeURI(url); 
	     }
	</script>	
	</body>
</html>
