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
		<title>项目详情</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<style>
		   .special{display:block;}
		   .pic_file{display:block;position:relative;height:30px;line-height: 30px;border: 1px solid transparent;}
		   .pic_file input{width:105px;display:block;position:absolute;z-index:1000;overflow:hidden;opacity:0;}
		   .pic_file button{position:absolute;top:0;left:0;width:105px;height:30px;line-height:30px;background-color:#81d0fc;color: #fff;
    font-size: 16px;}
    .add_p p{width:auto;}
    .add_p .w195{width:50%;}
    .go_right{left: unset;right: 0.1rem;background: url(../img/right.png) no-repeat;background-size: contain;}		
    
    </style>
	</head>
	<body>
		<div class="go-back" onclick="goBack()"></div>
		<div class="go-back go_right" onclick="pcUrl()"></div>
		<div id="mask"></div>
		<input type="hidden" id="projectNo" value="${project.projectNo}">
		<input type="hidden" id="roleNo" value="${roleNo}">
	    <input type="hidden" id="userId" value="${userId}">
	    <input type="hidden" id="userName" value="${userName}">
	    <input type="hidden" id="purchaseNameId" value="${purchaseNameId}">
	    <input type="hidden" id="pageNumber" value="${pageNumber}">
		<div class="detail">
			<div class="detail-tit">项目详情页</div>
			<div class="detail-middle">
				<!--项目基本信息-->
				<div class="detail-one">
					<p class="detail-top">项目基本信息</p>
					<p class="p-row"><span class="span-left">项目名称：</span><span class="span-right">${project.projectName}</span><span class="span-left" style="margin-left:10px;">项目号：</span><span class="span-right">${project.projectNo}</span></p>
					<p><span class="span-right">${project.companyName}</span></p>		
				</div>
				<!-- 项目列表大图上传 -->
				<form id="file_upload_id" onsubmit="return false;" method="post" enctype="multipart/form-data">
				    <input type="hidden" name="productImg" id="productImg" value="">
				    <input type="hidden" name="projectImgNo" id="projectImgNo" value="${project.projectNo}">
					<div class="detail-one">
						<p class="detail-top">项目列表大图上传<span class="special">(周一开会项目列表会显示,请传高清晰图)</span></p>
						<div class="pic_file"><input type="file" id="file_upload" name="file_upload" onchange="doUploadFile(this)"><button>上传文件</button><a href="http://112.64.174.34:10010/product_img/${project.productImg}" id="productImgHtml" style="margin-left:200px;">${project.productImg}</a></div>
					</div>	
				</form>
				
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
				<!--<div class="detail-one">
					<p class="detail-top">质检报告信息</p>
					<c:forEach var="inspectionReport" items="${project.inspectionReportList}">
					  <p class="p-row"><span class="span-left">质检报告:</span>
					    <span class="span-right"><a href="http://112.64.174.34:33168/upload/po/upload/JIANYANJIHUAZJ/${inspectionReport.reportName}">${inspectionReport.reportName}</a></span>
					  </p>
					</c:forEach>
				</div>  -->
				<div class="detail-one quality_test">
					<p class="detail-top"><span>质检版本</span><button id="sendButton" class="sc_btn">质检报告上传</button></p>
					<table>
					<c:forEach var="qualityReport" items="${project.qrList}">
						<tr>
							<td class="td1"><div class="s_name">${qualityReport.detailView}</div></td>
							<td class="td2"><button onclick="viewDetailQuality(${qualityReport.id})">查看</button></td>
							<td class="td3"><button onclick="deleteQuality(${qualityReport.id})">删除</button></td>
						</tr>
					</c:forEach>	
					</table>
				
				</div>
				
				<!--项目交期-->
				<div class="detail-one">
					<p class="detail-top">项目交期</p>
					<div class="date-reason original-date add_p">
						<p class="w195"><span class="span-left">PO日期：</span><span class="span-right"><fmt:formatDate value="${project.poDate}" pattern="yyyy-MM-dd"/></span></p>
						<p><span class="span-left">开工日期：</span><span class="span-right"><fmt:formatDate value="${project.actualStartDate}" pattern="yyyy-MM-dd"/></span></p>
						<p class="w195"><span class="span-left">样品交期：</span><span class="span-right"><fmt:formatDate value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd"/></span></p>
						<p><span class="span-left">大货交期：</span><span class="span-right"><fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/></span></p>
					</div>
				    <c:forEach var="delay" items="${project.delayList}"> 
						<div class="date-reason">
							<p><span class="span-left">项目交期：</span><span class="span-right "><fmt:formatDate value="${delay.delayDate}" pattern="yyyy-MM-dd"/></span></p>
							<p class="p-row"><span>延期原因：</span><span>${delay.internalCause}${delay.externalCause}</span></p>
						</div>
					</c:forEach>	
				</div>
				<!--生产计划-->
				<div class="detail-one">
					<p class="detail-top">生产计划</p>
					<div class="table-container">
						<table class="table">
							<tr>
								<th>节点</th>
								<th>日期</th>
							</tr>
						    <c:forEach var="plan" items="${project.planList}">
							   <tr>
								 <td>${plan.planNode}</td>
								 <td><fmt:formatDate value="${plan.planDate}" pattern="yyyy-MM-dd"/></td>
							  </tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<!--项目汇报-->
				<div class="detail-one">
					<p class="detail-top">项目汇报</p>
					<c:forEach var="report" items="${project.reportList}">
						<div class="date-reason">
							<p class="p-row"><span class="span-left">汇&nbsp;报 &nbsp;人：</span><span class="span-right ">${report.reportName}</span><span class="span-left" style="margin-left:30px;">汇&nbsp;报时间：</span><span class="span-right "><fmt:formatDate value="${report.createDate}" pattern="yyyy-MM-dd"/></span></p>
							<p class="p-row"><span class="span-left">项目进展：</span><span>${report.report}</span></p>
							<p class="p-row"><span class="span-left">项目阶段：</span><span>${report.projectStageView}</span></p>
							<p class="p-row"><span class="span-left">汇报文件：</span>
                          <a href="#" onclick="openFile('http://112.64.174.34:10010/uploadimg/${report.picUrl}')"><span>${report.picUrl}</span></a></p>  
                          <!--<a href="#" onclick="openFile('http://localhost:8080/uploadimg/${report.picUrl}')"><span>${report.picUrl}</span></a></p>-->
						</div>
					</c:forEach>
				</div>
				<!--任务汇报-->
				<div class="detail-one">
					<p class="detail-top">任务汇报</p>
					  <c:forEach var="task" items="${project.taskList}">
					   <div class="date-reason sbb">
						<a href="${ctx}/task/taskDetails?projectNo=${task.projectNo}&id=${task.id}&roleNo=${roleNo}&userId=${userId}&userName=${userName}">
							<div class="div-top">
								<p class="p-row"><span class="span-left">部&nbsp;署 &nbsp;人：</span><span class="span-right ">${task.operator}</span><span class="span-left" style="margin-left:30px;">部署时间：</span><span class="span-right "><fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd"/></span></p>
								<p class="p-row"><span class="span-left">任务状态段：</span><span class="span-right "> ${task.status==0?'未完成':'' }${task.status==1?'已完成':''} </span><span class="span-left" style="margin-left:30px;">截至时间：</span><span class="span-right "><fmt:formatDate value="${task.endDate}" pattern="yyyy-MM-dd"/></span></p>
								<p class="p-row"><span class="span-left">任务节点：</span><span class="span-right ">${task.node==0?'无':'' }${task.node==1?'样品交货':''}${task.node==2?'大货交货':''}</span></p>
								<p class="p-row"><span class="span-left">任务需求：</span><span>${task.taskDemand}</span></p>
							</div>
						</a>
					   <c:forEach var="taskReport" items="${task.taskReportList}">
						  <div class="div-list">
							<p class="p-row"><span class="span-left">汇报人：</span><span class="span-right">${taskReport.reportName}</span>								
							 <span class="span-left" style="margin-left:30px;">汇报时间：</span><span class="span-right"><fmt:formatDate value="${taskReport.createDate}" pattern="yyyy-MM-dd"/></span></p>
							<p class="p-row"><span class="span-left">汇报文件：</span>
							<a href="#" onclick="openFile('http://112.64.174.34:10010/uploadimg/${taskReport.picUrl}')"><span>${taskReport.picUrl}</span></a></p>
							<p class="p-row"><span class="span-left">任务进展：</span><span>${taskReport.taskReport}</span></p>
					      </div>
						</c:forEach>
						</div>
					</c:forEach>
				</div>
				
				<!--销售录入状态信息-->
				<div class="detail-one">
					<p class="detail-top">销售录入状态信息</p>
					<c:forEach var="statusEnter" items="${project.statusEnterList}">
						<div class="date-reason" style="border:1px solid deepskyblue;box-sizing:border-box;border-radius: 4px;">
							<p class="p-row"><span class="span-left">项&nbsp;目 &nbsp;号：</span><span class="span-right ">${statusEnter.projectNo}</span><span class="span-left" style="margin-left:30px;">消息时间：</span><span class="span-right "><fmt:formatDate value="${statusEnter.createDate}" pattern="yyyy-MM-dd"/></span></p>
							<p class="p-row"><span class="span-left">前期生产中的沟通：</span>
							  <c:forEach var="statusType" items="${statusEnter.statusTypeList}">
							    <c:if test="${statusType eq '1'}">
							      <span class="span-right ">需求变更或细化–和图纸相关</span>
							    </c:if>
							    <c:if test="${statusType eq '2'}">
							      <span class="span-right ">需求变更或细化–其他</span>
							    </c:if>
							    <c:if test="${statusType eq '3'}">
							      <span class="span-right ">交期或者价格相关</span>
							    </c:if>
							  </c:forEach>
							</p>
							<p class="p-row"><span class="span-left">收货后的反馈：</span>
							  <span class="span-right ">
							    <c:if test="${statusEnter.feedback eq '1'}">有问题</c:if>
							    <c:if test="${statusEnter.feedback eq '2'}">客户开心</c:if>
							  </span>
							</p>
							<c:if test="${statusEnter.type eq '0'}">
							 <p class="p-row"><span class="span-left">上传文件：</span><a href="#" onclick="openFile('http://112.64.174.34:10010/uploadimg/${statusEnter.fileUrl}')"><span>${statusEnter.fileUrl}</span></a></p>
							</c:if>
							<c:if test="${statusEnter.type eq '1'}">
							 <p class="p-row"><span class="span-left">上传文件：</span><a href="#" onclick="openFile('http://112.64.174.34:43887/NBEmail/download3?filename=${statusEnter.fileUrl}')"><span>${statusEnter.fileUrl}</span></a></p>
							</c:if>
						    <p class="p-row"><span class="span-left">细节：</span><span class="span-right ">${statusEnter.details}</span></p>
						</div>
					</c:forEach>
				</div>
				
					<!-- 项目金额 -->
				<div class="detail-one price_">
					<lable col-xs-1>项目金额</lable>
					<input id ='projectAmout' type="test" placeholder="请尽快填写项目金额" value="${project.projectAmount}"/>
					<button id="editProjectAmout">修改</button>
				</div>
				
				<!--项目留言-->
				<div class="detail-one">
					<p class="detail-top">项目留言</p>
					<c:forEach var="comment" items="${project.commentList}">
						<div class="date-reason">
							<p class="p-row"><span class="span-left">留&nbsp;言 &nbsp;人：</span><span class="span-right ">${comment.reviewer}</span><span class="span-left" style="margin-left:30px;">留言时间：</span><span class="span-right "><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd"/></span></p>
							<p class="p-row"><span class="span-left">留言内容：</span><span class="span-right ">${comment.comment}</span></p>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="index-footer">
				<span class="footer-list-one">项目交期</span>
				<span class="footer-list-two">项目状态</span>
			    <span class="footer-list-three" id="taskAllocation">任务分配</span>
			</div>
		</div>
		<!--项目交期弹出-->
		<div class="index-mask-one">
			<a href="${ctx}/project/editDetails?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}" class="fact-date">启动、节点、交货日期</a>
			<a href="${ctx}/jsp/delay.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}" class="footer-margin delay-date">延长交期</a>
			<a href="javascript:;" class="footer-cancel">取消</a>
		</div>
		<!--项目状态-->
		<div class="index-mask-two">
		    <c:if test="${roleNo==6}">
		      <a href="${ctx}/jsp/project_pause.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}">暂停,启动,取消项目</a>
		    </c:if>
			<a href="${ctx}/jsp/send-img.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}">项目汇报</a>
			<a href="${ctx}/jsp/comment.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}" class="finish-say">项目留言</a>
			<a href="javascript:;" class="finish-task" >大货完结交货</a>
			<a href="javascript:;" class="footer-margin sample-finish-task">样品完结交货</a>
			<a href="javascript:;" class="footer-cancel">取消</a>
		</div>
		<div id="click-big">
			<img src="../img/2.jpg"/>
		</div>
		
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="../js/jquery-form.js"></script>
		<script type="text/javascript" src="../js/easydialog.min.js"></script>
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
		  //大货完成交货
		  $(document).on('click','.finish-task',function(){
				layer.open({
					type:1,
					skin:'finish-btn',
					title:'大货完成交货',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
				})	
				$('.finish-btn .layui-layer-btn0').click(function(){
					var projectNo=$("#projectNo").val();
					var type='1';
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/updateProjectStatus",           
					    data:{
					    	  projectNo:projectNo,type:type
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
		  
		  
		  
		  //样品完成交货
		  $(document).on('click','.sample-finish-task',function(){
				layer.open({
					type:1,
					skin:'finish-btn',
					title:'样品完成交货',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
				})	
				$('.finish-btn .layui-layer-btn0').click(function(){
					var projectNo=$("#projectNo").val();
					var type="2";
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/updateProjectStatus",           
					    data:{
					    	  projectNo:projectNo,type:type
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
		    var pageNumber=$("#pageNumber").val();
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			var purchaseNameId=$("#purchaseNameId").val();
			window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName))+"&pageNumber="+pageNumber;	
		 }
		function pcUrl(){		
	        var roleNo = $('#roleNo').val()			
			var um = $("#userName").val();
	        var ui = $("#userId").val();
	        var pageNumber=$("#pageNumber").val();
	    	window.location.href = "${ctx}/project/queryListPc?userName="+um + "&userId=" + ui+ "&roleNo=" + roleNo+"&pageNumber="+pageNumber;
		}
			
		function openFile(url){
		   window.location.href=encodeURI(url); 
		}
		
		$('#editProjectAmout').click(function(){
			var index = layer.open({
				type:1,
				skin:'finish-btn',
				title:'确认修改？',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
				yes:function(){
					var projectNo=$("#projectNo").val();
					var projectAmout = $('#projectAmout').val()
					
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/editAmout",           
					    data:{
					    	  projectNo:projectNo,
					    	  projectAmout:projectAmout
					    	 },              
					    success:function(data){  
					    	
					      var json = eval("(" + data +")");
						  if(json.ok){
							 layer.msg(json.message,{time:2000});
						  }else{
							 layer.msg(json.message,{time:2000});
						  }
						  layer.close(index)
					   }
				 });  
					
				},
			})	
		
		})
		
		$('#sendButton').click(function(){
		
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/quality/addQuality?roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));
			
		})
		
		function deleteQuality(obj){
			var id=obj;
			if(!id){
				return false
			}
			var index = layer.open({
				type:1,
				skin:'finish-btn',
				title:'确认删除？',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
				yes:function(){
					var roleNo=$("#roleNo").val();
					var projectNo=$("#projectNo").val();
					var userId=$("#userId").val();
					var userName=$("#userName").val();
						
					$.ajax({
				     type:"post",                   
				     url:"${ctx}/quality/deleteQuality",           
				     data:{
				         id:id,userName:userName
				     },              
				     success:function(data){
				    	 var json = eval("(" + data +")");
						 if(json.ok){
							  window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
						  }else{
							  layer.msg(json.message)
						  }
						 layer.close(index)
				     },
				     error:function(){
				    	 layer.msg(json.message);
				    	 layer.close(index)
				     }
					})

				}
			})
		}
		
		function viewDetailQuality(obj){
			var id=obj;
			if(!id){
				return false
			}
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/quality/viewQuality?id="+id+"&roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));	
		}
		
		 //上传文件
		 function doUploadFile(obj){
			  var path =  $("#file_upload").val();
		      sppath = path.split("\\");
			  var productImg = sppath[sppath.length-1];
			  $("#productImg").val(productImg);
	         //发送ajax请求,提交form表单    
			  $("#file_upload_id").ajaxSubmit({
				type : "post",
				url : "${ctx}/project/uploadProductFile",
				dataType : "text",
				success : function(result) {
				    var data = eval("(" + result +")");
					$('#productImgHtml').text(data);
					$('#productImgHtml').attr('href','http://112.64.174.34:10010/product_img/'+data+''); 
				},
				error : function() {
					easyDialog.open({
						container : {
							content : '  Upload failed'
						},
						autoClose : 1000
				  });
				}
			   });
		 }
	</script>	
	</body>
</html>
