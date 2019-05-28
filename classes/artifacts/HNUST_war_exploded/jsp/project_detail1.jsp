<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>项目详情页</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div class="go-back" onclick="goBack()"></div>
		<div id="mask"></div>
		<input type="hidden" id="projectNo" value="${project.projectNo}">
		<input type="hidden" id="roleNo" value="${roleNo}">
	    <input type="hidden" id="userId" value="${userId}">
	    <input type="hidden" id="userName" value="${userName}">
	    <input type="hidden" id="purchaseNameId" value="${purchaseNameId}">
		<div class="detail">
			<div class="detail-tit">项目详情页</div>
			<div class="detail-middle">
				<!--项目基本信息-->
				<div class="detail-one">
					<p class="detail-top">项目基本信息</p>
					<p><span class="span-left">项目需求</span><span class="span-right">${projectRequirements}</span></p>
					<p class="p-row"><span class="span-left">项目名称：</span><span class="span-right">${project.projectName}</span><span class="span-left" style="margin-left:10px;">项目号：</span><span class="span-right">${project.projectNo}</span></p>
					<p><span class="span-right">${project.companyName}</span></p>		
				</div>
				<!--项目图纸信息-->
				<div class="detail-one">			  
				  <p class="detail-top">图纸信息</p>
				   <c:forEach var="projectDrawing" items="${project.projectDrawingList}">
					 <c:if test="${projectDrawing.category eq '1'}">
					   <p class="p-row"><span class="span-left">图纸文件:</span>
					     <span class="span-right"><a href="http://112.64.174.34:33168/upload/tuzhi/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
					   </p>
				    </c:if>
				    <c:if test="${projectDrawing.category eq '2'}">
					   <p class="p-row"><span class="span-left">图纸文件:</span>
					     <span class="span-right"><a href="http://112.64.174.34:33168/upload/neibubaojia/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
					   </p>
				    </c:if>
				    <c:if test="${projectDrawing.category eq '3'}">
					   <p class="p-row"><span class="span-left">图纸文件:</span>
					     <span class="span-right"><a href="http://112.64.174.34:33168/upload/duiwaibaojia/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
					   </p>
				    </c:if>
				    <c:if test="${projectDrawing.category eq '4' || projectDrawing.category eq '5'}">
					   <p class="p-row"><span class="span-left">图纸文件:</span>
					     <span class="span-right"><a href="http://112.64.174.34:33168/upload/zhongwentuzhi/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
					   </p>
				    </c:if>
				   </c:forEach>
				</div>
				<!--项目图纸信息-->
				<div class="detail-one">
					<p class="detail-top">质检报告信息</p>
					<c:forEach var="inspectionReport" items="${project.inspectionReportList}">
					  <p class="p-row"><span class="span-left">质检报告:</span>
					    <span class="span-right"><a href="http://112.64.174.34:33168/upload/po/upload/JIANYANJIHUAZJ/${inspectionReport.reportName}">${inspectionReport.reportName}</a></span>
					  </p>
					</c:forEach>
				</div>
				<!--项目交期-->
				<div class="detail-one">
					<p class="detail-top">项目交期</p>
					<div class="date-reason original-date">
						<p><span class="span-left">销售：</span><span class="span-right">${project1.customerManager}</span></p>
						<p><span class="span-left">跟单销售：</span><span class="span-right">${project1.merchandManager1 }</span></p>
						<p><span class="span-left">采购：</span><span class="span-right">${project1.merchandManager2 }</span></p>
						<p><span class="span-left">质检：</span><span class="span-right">${project1.zhijian1 }</span></p>
						<p><span class="span-left">质检2：</span><span class="span-right">${project1.zhijian2 }</span></p>
					</div>
				   
				</div>
				
				
				
				
				
				
		
		
		<div id="click-big">
			<img src="../img/2.jpg"/>
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
				var roleNo=$("#roleNo").val();
				if(roleNo==6){
					$('#mask').show(100);
					$('.index-mask-one').show(300);
				}else{
					layer.msg("没有权限操作",{time:2000});
					return false;
				}
				
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
				var roleNo=$("#roleNo").val();
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
		  //完成交货
		  $(document).on('click','.finish-task',function(){
				layer.open({
					type:1,
					skin:'finish-btn',
					title:'完成交货',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
				})	
				$('.finish-btn .layui-layer-btn0').click(function(){
					var projectNo=$("#projectNo").val();
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/updateProjectStatus",           
					    data:{
					    	  projectNo:projectNo
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
				 $('#mask').hide();
				 $('.index-mask-two').hide();
		 		})
		  }) 
		  
		  $("#taskAllocation").click(function(){
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/jsp/task_allocation.jsp?roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));
		  })
		 //一键返回列表页 
		 function goBack(){
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			var purchaseNameId=$("#purchaseNameId").val();
			window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
		 }
			
		function openFile(url){
		   window.location.href=encodeURI(url); 
		}
	</script>	
	</body>
</html>
