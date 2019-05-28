<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String roleNo=request.getParameter("roleNo");
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>启动暂停项目</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<style>
			#qidong input{
				width:50%;
			}
			#qidong textarea{
				height:1.2rem;
				width:50%;
			}
		
		</style>
	</head>
  <body>
		<header class="task-head">
	     	<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="task-tit">启动,暂停项目</div>
		</header>
		<main class="task-main">
		   <input type="hidden" id="roleNo" value="<%=roleNo%>">
		   <input type="hidden" id="userId" value="<%=userId%>">
		   <input type="hidden" id="userName" value="<%=userName%>">
		   <input type="hidden" id="projectNo" value="<%=projectNo%>">
			<div class="detail-one" id="qidong">
				<p class="detail-top">启动,暂停项目</p>
					<form action="" method="post" class="form">
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>项目号：</label>
					<input type="text" id="projectNo" class="input-text" value="<%=projectNo%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>选择操作：</label>
					<div class="checkbox-inline">
						<span><input type="radio" name="radio-reason" value="1"/> <label >暂停项目</label></span>
						<span><input type="radio" name="radio-reason"  value="0"/> <label >启动项目</label></span>
						<span><input type="radio" name="radio-reason"  value="2"/> <label >取消项目</label></span>
					</div>
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red"></span>原因：</label>
					<textarea type="text" id="pauseReason" class="textarea text-reason" placeholder="请输入原因..."></textarea>
				</div>
			</form>
			</div>
			<div class="task-btn">
				<button class="btn btn-primary" id="submitButton">确定</button>
			</div>
		</main>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		 $("#submitButton").click(function(){
			  var checked=$('.checkbox-inline input:checked').attr('value');
			  var projectNo=$("#projectNo").val();
			  var pauseReason=$("#pauseReason").val();
			  var userId=$("#userId").val();
			  var userName=$("#userName").val();
			  var roleNo=$("#roleNo").val();
			  
			  if(checked ==undefined){
				  layer.msg("请选择操作",{time:2000});  
				  return false; 
			  }
			  if(checked!=0){//如果暂停项目请填写原因
				  if(pauseReason=="" || pauseReason==null){
					  layer.msg("请填写原因",{time:2000});  
					  return false;
				  } 
			  }
			  
			  $.ajax({
			    type:"post",                   
			    url:"${ctx}/project/pauseProject",           
			    data:{
			    	  projectNo:projectNo,
			    	  checked:checked,
			    	  pauseReason:pauseReason,
			    	 },              
			    success:function(data){  
			      var json = eval("(" + data +")");
				  if(json.ok){
		             window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
				  }else{
					  layer.msg(json.message,{time:2000});
				  }   
			    }
			 });
		 })
		</script>
</body>
</html>