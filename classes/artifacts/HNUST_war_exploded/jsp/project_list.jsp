<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String roleNo = request.getParameter("roleNo");
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
	String purchaseNameId = request.getParameter("purchaseNameId");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>项目首页111</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<style>
.span-right {
	display: initial;
	float: none;
}
</style>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
	<!--遮罩层-->
	<div id="mask"></div>
	<div class="index-main project_list">
		<div class="index-top">
			<div>
				<span class="index-active">项目列表</span><span id="tsak-list">采购任务列表</span>
				<span onclick="pcUrl()"
					style="background: #7FFFD4">电脑浏览</span>
			</div>

		</div>
		<input type="hidden" id="roleNo" value="<%=roleNo%>"> <input
			type="hidden" id="userId" value="<%=userId%>"> <input
			type="hidden" id="userName" value="<%=userName%>"> <input
			type="hidden" id="purchaseNameId" value="<%=purchaseNameId%>">
		<input type="hidden" id="pageSize" value="10"> <input
			type="hidden" id="pageNumber" value="1"> <input type="hidden"
			id="totalCount" value="10"> <input type="hidden"
			id="operatorType" value="0">
		<div class="index-middle">
			<div class="div-bg">
				<div class="key-search">
					<button class="left btn btn-primary" id="exitlogin"
						style="margin-left: 10px;">退出系统</button>
					<button id="cleanSelect" class="left btn btn-primary"
						style="margin-left: 10px; width: 150px;" onclick="cleanSelect()">清空所有关键词和条件</button>
					<span class="span-left">项目总数：</span><span class="span-left"
						id="projectNum"></span> <span class="span-left">延期总数：</span><span
						class="span-left animation-scale" id="delayNum" style="color: red"></span>

				</div>

				<div class="key-search" style="color:red;position:relative;">
					<label for="input-key" class="left control-label">关键字搜索：</label> <input
						type="text" class="left input-text form-control" id="inputKey"
						value="" placeholder="项目号/项目名/姓名" />
					<button class="left btn btn-primary" id="searchBut">搜索</button>
					<div class="red" style="color:red;position:absolute;top:0;left:0;">无该项目，请联系项目跟单到ERP上该项目页添加您的姓名，并务必按"保存"键。</div>

				</div>

				<div class="index-link">
					<p style="line-height: 0.5rem; height: 0.60rem; margin: 0.20rem;">

						<label>项目状态</label> <select name="projectType" id="projectType"
							value="" style="width: 2.0rem; height: 0.50rem;"
							onchange="selectOnchange()">
							<option value="">所有项目</option>
							<option value='0'>新立项项目</option>
							<option value='1'>正常进行项目</option>
							<option value='2'>完成项目</option>
							<option value='3'>延期项目</option>
							<option value='4'>暂停项目</option>
							<option value='5'>取消项目</option>
						</select> <label style="margin-left: 20px;">项目阶段</label> <select
							name="projectStage" id="projectStage" value=""
							style="width: 2.0rem; height: 0.50rem;"
							onchange="selectOnchange()">
							<option value="">全部</option>>
							<option value='0'>样品</option>
							<option value='1'>模具</option>
							<option value='2'>大货</option>

						</select>
					</p>
				</div>

				<div class="index-link">
					<p style="line-height: 0.5rem; height: 0.60rem; margin: 0.20rem;">
						<span class="span-right"> <label>人员筛选</label> <select
							id="purchase_name" style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()">
								<option value="">全部采购</option>
							    <option value="无采购">无采购</option>
						</select> <select id="documentary_name"
							style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()">
								<option value="">全部跟单</option>
						</select> <select id="quality_name" style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()">
								<option value="">全部质检</option>
						</select>

						</span>

					</p>
				</div>

			</div>
			<!-- 项目列表 -->
			<div class="index-task" id="projectDataList"
				style="margin-top: 35px;"></div>
			<!-- 任务列表显示 -->
			<div class="index-task" id="projectTaskList"></div>
		</div>
		<div class="index-footer">
			<!-- <span class="footer-list-one" id="messageCenter">消息中心</span> -->
			<!-- 	<span class="footer-list-one" id="addProjectTask">录入任务</span> -->
			<span style="background: #C0C0C0; color: #000000; border: 1px solid;"
				class="footer-list-one" id="projectTaskTechnologyList">极简任务列表</span>
			<!-- 	<span class="footer-list-one" id="inputMeetingRecord">录入会议</span> -->
			<span style="background: #C0C0C0; color: #000000; border: 1px solid;"
				id="meetingRecordList">会议列表</span> <span
				style="background: #C0C0C0; color: #000000; border: 1px solid;"
				class="footer-list-two">项目统计</span>

			<!-- <span style="background:blue;" id="roleBind">角色绑定</span> -->

			<!--<span style="background: #ef4f4f;" id="trigger">触发动作</span>  -->

			<span
				style="background: #C0C0C0; color: #000000; border: 1px solid; display: none;"
				id="taskFlow">任务流</span>
		</div>
	</div>
	<div class="index-mask">
		<a href="javascript:;" id="projectSummary" class="footer-margin">项目汇总</a>
		<a href="javascript:;" class="footer-cancel">取消</a>
	</div>

	<div class="index-mask-task">
		<a href="javascript:void(0);" id="roleBind" class="footer-margin">角色绑定</a>
		<a href="javascript:void(0);" id="trigger" class="footer-margin">触发动作</a>
		<a href="javascript:void(0);" id="taskFlowdetail"
			class="footer-margin">任务流</a> <a href="javascript:void(0);"
			class="footer-cancel">取消</a>
	</div>



	<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
	<script type="text/javascript">
		Date.prototype.pattern = function(fmt) {
			var o = {
				"M+" : this.getMonth() + 1, //月份         
				"d+" : this.getDate(), //日         
				"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时         
				"H+" : this.getHours(), //小时         
				"m+" : this.getMinutes(), //分         
				"s+" : this.getSeconds(), //秒         
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度         
				"S" : this.getMilliseconds()
			//毫秒         
			};
			var week = {
				"0" : "/u65e5",
				"1" : "/u4e00",
				"2" : "/u4e8c",
				"3" : "/u4e09",
				"4" : "/u56db",
				"5" : "/u4e94",
				"6" : "/u516d"
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}
			if (/(E+)/.test(fmt)) {
				fmt = fmt
						.replace(
								RegExp.$1,
								((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
										: "/u5468")
										: "")
										+ week[this.getDay() + ""]);
			}
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(fmt)) {
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
				}
			}
			return fmt;
		}
	</script>
	<script type="text/javascript">
		var queryObj = {
			"preNum" : 1,
			"isPre" : false,
			"nextNum" : 2,
			"isNext" : false,
			"curNum" : 0,
			"operatorType" : 0
		};
		var isDoQuery = false;
		//一进来加载默认数据
		$(function() {

			var roleNo = $('#roleNo').val()
			
			if(roleNo==6){
				$('#purchase_name').hide()
			}
			if(roleNo==5){
				$('#documentary_name').hide()
			}
			if(roleNo==9){
				$('#quality_name').hide()
			}
			
			
			
			ajaxSelectOption()
			
			var um = $("#userName").val();

			if (um == 'system' || um=='ninazhao') {
				$('#taskFlow').show()
			}
			//projectDataLoad(1);
			var indexHeight;
			//判断PC端还是移动端
			var sUserAgent = navigator.userAgent.toLowerCase();
			var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
			var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
			var bIsMidp = sUserAgent.match(/midp/i) == "midp";
			var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
			var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
			var bIsAndroid = sUserAgent.match(/android/i) == "android";
			var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
			var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
			if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc
					|| bIsAndroid || bIsCE || bIsWM)) {
				indexHeight = 1042;
			} else {
				indexHeight = 500;
			}
			showload();
			$(".index-middle")
					.scroll(
							function() {
								var txHeight = $('#projectDataList').height();
								var projectNum = $("#projectNum").text();
								var hh = $(this).scrollTop();
								if (hh + indexHeight > txHeight
										&& $('#projectDataList').find('dl').length <= projectNum) {
									if (isDoQuery) {
										return false;
									} else {
										isDoQuery = true;
										queryObj.operatorType = 0;
										showload();
									}
									txHeight = $('#projectDataList').height();
								} else {
									return false;
								}
								console.log(txHeight + '=e===' + hh);
								console
										.log("dl count : "
												+ $("#projectDataList").find(
														'dl').length)
							});
		});

		jQuery.Huitab = function(tabBar, tabCon, class_name, tabEvent, i) {
			var $tab_menu = $(tabBar);
			// 初始化操作
			$tab_menu.removeClass(class_name);
			$(tabBar).eq(i).addClass(class_name);
			$(tabCon).hide();
			$(tabCon).eq(i).show();

			$tab_menu.bind(tabEvent, function() {
				$tab_menu.removeClass(class_name);
				$(this).addClass(class_name);
				var index = $tab_menu.index(this);
				$(tabCon).hide();
				$(tabCon).eq(index).show();
			})
		}
		$.Huitab(".index-top span", ".index-middle .index-task",
				"index-active", "click", "0");
		$('.footer-list-two').click(function() {
			$('#mask').show(100);
			$('.index-mask').show(300);
		});

		$('#taskFlow').click(function() {
			$('#mask').show(100);
			$('.index-mask-task').show(300);
		});

		$('#mask').click(function() {
			$('#mask').hide(200);
			$('.index-mask').hide(200);
			$('.index-mask-task').hide(200);
		})
		$('.footer-cancel').click(function() {
			$('#mask').hide(200);
			$('.index-mask').hide(200);
			$('.index-mask-task').hide(200);

		})

		//页面查询按钮
		$("#projectSummary")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/jsp/project-summary.jsp?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						})
		//页面查询按钮
		$("#exitlogin").click(function() {
			$.cookie('name', '', {
				path : '/'
			});
			$.cookie('pass', '', {
				path : '/'
			});
			window.location.href = "${ctx}/index.jsp";
		})

		//页面查询按钮
		$("#searchBut").click(function() {
			var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
			showload();
		})
		//根据选择的采购查询列表
		function selectOnchange() {
			
		
			var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
			showload();
		}

		//消息中心
		$("#messageCenter").click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					$("#messageNum").val('0');
					window.location.href = "${ctx}/jsp/message.jsp?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				})
		//进入录入任务页面
		$("#addProjectTask")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/projectTask/addTask?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});
		//任务列表页
		$("#projectTaskTechnologyList")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/projectTask/projectTaskList?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						})
		//会议录入界面
		$("#inputMeetingRecord")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/jsp/input_meeting_record.jsp?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});
		//会议列表页面
		$("#meetingRecordList")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/meetingRecord/selectMeetingRecordList?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});

		//角色绑定
		$("#roleBind").click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/roleBind/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				});

		//触发动作
		$('#trigger').click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/trigger/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				});

		//任务流
		$('#taskFlowdetail').click(
				function() {

					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/taskflow/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;

				})

		function projectDataLoad(pageNumber) {
			var operatorType = queryObj.operatorType;
			isDoQuery = true;
			var pageSize = $("#pageSize").val();
			if ((queryObj.preNum == pageNumber && !queryObj.isPre)
					|| (queryObj.nextNum == pageNumber && !queryObj.isNext)) {
				doQuery(pageNumber,operatorType);
			} else {
				isDoQuery = false;
				return false;
			}

		}

		function doQuery(pageNumber,operatorType) {
			
			var roleNo = $("#roleNo").val();
			var userId = $("#userId").val();
			var userName = $("#userName").val();
			var inputKey = $("#inputKey").val();//关键字查询
		
			var projectType = $("#projectType").val();
			var projectStage = $('#projectStage').val()
			var purchase_name = $('#purchase_name').val()
			var documentary_name = $('#documentary_name').val()
			var quality_name = $('#quality_name').val()
			
			
			$.ajax({
						type : 'post',
						url : '${ctx}/project/showList',
						sync : true,
						data : {
							pageNumber : pageNumber,
							operatorType : operatorType,
							
							roleNo : roleNo,
							userId : userId,
							userName : userName,
							inputKey : inputKey,
							
							projectType:projectType,
							projectStage:projectStage,
							
							purchase_name : purchase_name,
							documentary_name :documentary_name,
							quality_name: quality_name
							
						},
						beforeSend : function() {
							index = layer.load();
						},
						success : function(data) {
							layer.close(index);
							var json = eval("(" + data + ")");
							var projectDataList = json.data.list;//项目数据
							var delayNum = json.data.delayNum; //延期数目
							var messageNum = json.data.messageNum;//消息数目
							var projectNum = json.data.projectNum;//项目数量
							var projectType = json.data.projectType; //状态类型(完成,未完成,暂停)
							var roleNo = json.data.roleNo;//角色代码
							var taskList = json.data.taskList;//任务列表
							var userId = json.data.userId;//用户Id
							var userName = json.data.userName;//用户名
							var pageSize = json.data.pageSize;
							var pageNumber = json.data.pageNumber;
							var totalCount = json.data.totalCount;
							var totalCountFinish=json.data.totalCountFinish;//大货完成数量
							var purchaseNameId = json.data.purchaseNameId;//采购姓名查询
							$("#purchaseSelect").empty();//采购下拉列表

							if (operatorType == 1) { // 代表是查询操作要清空
								$("#projectDataList").empty();
								$("#projectTaskList").empty();
								$("#selectProjectType").empty();
							} else {

							}
							if (json.ok) {
								var dataHtml = '';
								var taskHtml = '';
								
								
								if (projectDataList&& projectDataList.length > 0) {
									
									for (var i = 0; i < projectDataList.length; i++) {//项目列表
										dataHtml += '<dl class="index-list"><dd><p class="pc_p"><span class="span-left"></span><span class="span-right porject-number">'
												+ projectDataList[i].projectNo
												+ '</span></p>'
										        + '<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'
												+ (projectDataList[i].projectName?projectDataList[i].projectName:"")
												+ '</span></p>'
										        + '<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'
												+ (projectDataList[i].companyName?projectDataList[i].companyName:"")
												+ '</span></p>'
												
										if (roleNo == 5) {
											dataHtml += '<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'
													+ (projectDataList[i].purchaseName ? projectDataList[i].purchaseName:"")
													+ '</span></p>'
													
										} else if (roleNo == 6) {
											dataHtml += '<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'
													+ (projectDataList[i].sellName ?projectDataList[i].sellName :"")
													+ '</span></p>'
										} else {
											dataHtml += '<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'
												+ (projectDataList[i].sellName ?projectDataList[i].sellName :"")
													+ '</span></p>'
											dataHtml += '<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'
													+ (projectDataList[i].purchaseName ? projectDataList[i].purchaseName: "")
													+ '</span></p>'
										}
												
										if (projectDataList[i].deliveryDate != null&& projectDataList[i].deliveryDate != '') {
											dataHtml += '<p class="pc_p"><span class="span-left">大货交期：</span><span class="span-right project-date">'
													+ (new Date(projectDataList[i].deliveryDate).pattern("yyyy-MM-dd"))+ '</span></p>'
										} else {
											if(projectDataList[i].sampleScheduledDate!=null && projectDataList[i].sampleScheduledDate!=''){
												dataHtml += '<p class="pc_p"><span class="span-left">样品交期：</span><span class="span-right project-date">'
												+ (new Date(projectDataList[i].sampleScheduledDate).pattern("yyyy-MM-dd"))+ '</span></p>'
											}else{
												dataHtml += '<p class="pc_p"><span class="span-left">交期：</span><span class="span-right project-date"></span></p>'
											}
										}
										dataHtml += '<p class="project-status pc_p"><span class="span-left">项目状态：</span><span class="span-right project-state">'
										if (projectDataList[i].warningStatus == 1) {
											dataHtml += " 质量投诉,";
										}
										if (projectDataList[i].importance == 1) {
											dataHtml += " 重要,";
										}
										dataHtml +=(projectDataList[i].status?projectDataList[i].status:"")						
										dataHtml += '<input type="hidden" value="'+projectDataList[i].flag+'" class="project-flag">'
										if (projectDataList[i].flag == 1) {
											dataHtml += '<p class="pc_p"><span class="span-left warning-project">项目快到期了</span></p>'
										}
										if (projectDataList[i].flag == 2) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project">项目已延期</span></p>'
										}
										if (projectDataList[i].projectReportDate != null) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project">周报更新时间:'
												+ (new Date(projectDataList[i].projectReportDate).pattern("yyyy-MM-dd"))+ '</span></p>'
										}
										if (projectDataList[i].projectReportList.length > 0) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project" style="color:green">已更新周报</span></p>';
										} else {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project" style="color:red">未更新周报</span></p>';
										}
										dataHtml += '<p class="detail-link"><a class="left btn btn-primary" href="${ctx}/project/showDetails?projectNo='
												+ projectDataList[i].projectNo
												+ '&roleNo='
												+ roleNo
												+ '&userId='
												+ userId
												+ '&userName='
												+ userName
												+ '">详情</a>'
										if (projectDataList[i].taskList.length > 0) {
											dataHtml += '<a class="left btn btn-primary" style="width:27%;margin-left:5px;" href="${ctx}/project/projectTask?projectNo='
													+ projectDataList[i].projectNo
													+ '&roleNo='
													+ roleNo
													+ '&userId='
													+ userId
													+ '&userName='
													+ userName
						
													+ '">有'
													+ projectDataList[i].taskList.length
													+ '条新任务</a>'
										}
										if (projectDataList[i].projectDrawingList.length > 0) {
											dataHtml += '<span><a class="left btn btn-primary" href="#" style="margin-left:5px;font-size:0.25rem;">图纸</a></span>'
										}
										if (projectDataList[i].inspectionReportList.length > 0) {
											dataHtml += '<a class="left btn btn-primary" style="width:27%;margin-left:5px;" href="#">QC REPORT</a>'
										}
									    if (projectDataList[i].delay!=null && (userName =='Jiangwenlong' ||  userName =='jiangwenlong') && projectDataList[i].delay.isAgree==0) { 
											dataHtml += '<span class="span-left" style="color:red;">申请延期时间:'
												+ (new Date(projectDataList[i].delay.delayDate).pattern("yyyy-MM-dd"))+'</span>'        
												+'<span  class="left btn btn-primary" style="width:5%;margin-left:5px;color:#fff;" onclick="applayDelaySub(1,\''+projectDataList[i].projectNo+'\')">同意</span>'
												+'<span  class="left btn btn-primary" style="width:5%;margin-left:5px;color:#fff;" onclick="applayDelaySub(2,\''+projectDataList[i].projectNo+'\')">不同意</span>'
										 }
										if (roleNo == 5) {
											dataHtml += '<a class="left btn btn-primary" style="width:20%;margin-left:5px;" href="${ctx}/jsp/status_enter.jsp?projectNo='
													+ projectDataList[i].projectNo
													+ '&roleNo='
													+ roleNo
													+ '&userId='
													+ userId
													+ '&userName='
													+ userName
													+ '">录入状态</a>'
										}
										dataHtml += '</p></dd></dl>'
									}
									for (var i = 0; i < taskList.length; i++) {//任务列表
										taskHtml += '<a href="${ctx}/task/taskDetails?projectNo='
												+ taskList[i].projectNo
												+ '&id='
												+ taskList[i].id
												+ '&roleNo='
												+ roleNo
												+ '&userId='
												+ userId
												+ '&userName='
												+ userName
												+ '" class="index-link">'
										taskHtml += '<dl class="index-list task-list" style="margin-top: 45px;"><dd><p><span class="span-left">项目号：</span><span class="span-right porject-number">'
												+ taskList[i].projectNo
												+ '</span></p>'
										taskHtml += '<p><span class="span-left">截止日期：</span><span class="span-right project-date">'
												+ (new Date(taskList[i].endDate)
														.pattern("yyyy-MM-dd"))
												+ '</span></p>'
										if (roleNo == 5) {
											taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
													+ (taskList[i].purchaseName ? taskList[i].purchaseName
															: "")
													+ '</span></p>'
										} else if (roleNo == 6) {
											taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
													+ (taskList[i].sellName?taskList[i].sellName:"")
													+ '</span></p>'
										} else {
											taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
													+ (taskList[i].sellName?taskList[i].sellName:"")
													+ '</span></p>'
											taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
													+ (taskList[i].purchaseName ? taskList[i].purchaseName: "")
													+ '</span></p>'
										}
										taskHtml += '<p><span class="span-left">任务节点：</span><span class="span-right">'
										if (taskList[i].node == 0) {
											taskHtml += "无";
										} else if (taskList[i].node == 1) {
											taskHtml += "样品交货";
										}
										if (taskList[i].node == 2) {
											taskHtml += "大货交货" + '</span></p>';
										}
										taskHtml += '<input type="hidden" class="task-status" value="'+taskList[i].status+'"/>'
										taskHtml += '<p><span class="span-left">任务状态：</span><span class="span-right project-person">'
										if (taskList[i].status == 0) {
											taskHtml += '未完成';
										} else {
											taskHtml += '已完成' + '</span></p>';
										}
										taskHtml += '<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">'
												+ taskList[i].taskDemand
												+ '</span></p></dd></dl></a>';
									}
									$("#projectDataList").append(dataHtml);//项目列表
									$("#projectTaskList").append(taskHtml);//任务列表	
									if(projectType==""){//如果查询所有的去掉完成的项目
										$("#projectNum").text(projectNum-totalCountFinish);//项目总数
									}else{
										$("#projectNum").text(projectNum);//项目总数
									}
									$("#delayNum").text(delayNum);//延期数量

								} else {
									queryObj.operatorType = 1;
									queryObj.preNum = 1;
									queryObj.isPre = false;
									queryObj.nextNum = 1;
									queryObj.isNext = false;
									queryObj.curNum = 0;
									$("#projectNum").text(0);//项目总数
									$("#delayNum").text(0);//延期数量
									$("#projectType").val(projectType);
								}

								//$("#selectProjectType").append(typeHtml);

								$("#selectProjectType").find(
										'input[type=radio]').each(function() {

									if ($(this).val() == projectType) {
										$(this).prop('checked', true)
									}
								})

								
							
								$("#totalCount").val(totalCount);

								//分页使用 
								queryObj.isPre = true;
								queryObj.preNum = pageNumber;
								queryObj.isNext = false;
								queryObj.nextNum = pageNumber + 1;
								queryObj.curNum = pageNumber;

								console.log("query success ,cur pageNumber:"
										+ queryObj.curNum);
								$("#pageNumber").val(queryObj.curNum);

								if (roleNo == 6) {//显示采购下拉框(销售,管理员显示)
									$("#purchaseSelect").css("display", "none");
								} else {
									$("#purchaseSelect")
											.css("display", "block");
								}
								//下拉框重新赋值
								if (!(purchaseNameId == null
										|| purchaseNameId == "" || purchaseNameId == "0")) {
									$("#purchase_id").val(purchaseNameId);
								}
								//项目列表延期与快到期标记
								$('.index-list')
										.each(
												function(i) {
													var project_flag = $(
															'.project-flag')
															.eq(i).val();
													if (project_flag == 1) {
														$(this)
																.css("border",
																		"1px solid deepskyblue");
													} else if (project_flag == 2) {
														$(this)
																.css("border",
																		"1px solid red");
													}
												})
								//点击任务列表判断任务完成与未完成标记
								$('#tsak-list')
										.click(
												function() {
													$('.task-list')
															.each(
																	function(i) {
																		var task_status = $(
																				'.task-status')
																				.eq(
																						i)
																				.val();
																		if (task_status == 0) {
																			$(
																					this)
																					.find(
																							'.project-person')
																					.css(
																							"color",
																							"red")
																		}
																	})
												})
								
							} else {
								//点击项目状态查询
								$("#projectType").val(projectType);
								queryObj.operatorType = 1;
								queryObj.preNum = 1;
								queryObj.isPre = false;
								queryObj.nextNum = 1;
								queryObj.isNext = false;
								queryObj.curNum = 0;
								layer.msg(json.message, {
									time : 2000
								});
							}
							isDoQuery = false;
						},
						error : function() {
							isDoQuery = false;
						}
					});
		}

		/**分页加载数据*/
		function showload() {
			var totalCount = Number($("#totalCount").val());
			pageNumber = queryObj.curNum + 1;
			console.log("begin query,pageNumber:" + pageNumber);
			if (!isDoQuery
					|| (queryObj.preNum == pageNumber && !queryObj.isPre)
					|| (queryObj.nextNum == pageNumber && !queryObj.isNext)) {
				console.log("preNum:" + queryObj.preNum + "," + "isQuery:"
						+ queryObj.isPre + ",curNum:" + queryObj.curNum
						+ ",nextNum:" + queryObj.nextNum + "," + "isQuery:"
						+ queryObj.isNext);

				var residue = totalCount % 10;
				var divider = parseInt(totalCount / 10);
				if (residue > 0) {
					divider = divider + 1;
				}
				if (pageNumber <= divider) {
					projectDataLoad(pageNumber);
				} else {
					return true;
				}
			} else {
				return false;
			}
		}

		function ajaxSelectOption() {

			$.ajax({
				type : "post",
				url : "${ctx}/project/queryStaff.do ",
				success : function(data) {
					var json = eval("(" + data +")");
					if (json.ok) {
						
						var purchaseList = json.data.purchase
						var saleList = json.data.sale
						var qualityList = json.data.quality
						
						var purchaseHtml ='<option value="">全部采购</option><option value="无采购">无采购</option>'
						var saleHtml ='<option value="">全部跟单</option>'
						var qualityHtml ='<option value="">全部质检</option>'
						
						for(var i=0;i<purchaseList.length;i++){
							purchaseHtml+='<option value="'+purchaseList[i].userName+'">'+purchaseList[i].userName+'</option>'
						}
						for(var i=0;i<saleList.length;i++){
							saleHtml+='<option value="'+saleList[i].userName+'">'+saleList[i].userName+'</option>'
						}
						for(var i=0;i<qualityList.length;i++){
							qualityHtml+='<option value="'+qualityList[i].userName+'">'+qualityList[i].userName+'</option>'
						}
						
						
						$('#purchase_name').html(purchaseHtml)
						$('#documentary_name').html(saleHtml)
						$('#quality_name').html(qualityHtml)
						
						
					}
				}
			})

		}
		
		function cleanSelect(){		
			$("#inputKey").val("")
			$("#projectType").val("")
            $('#projectStage').val("")
            $('#purchase_name').val("")
            $('#documentary_name').val("")
            $('#quality_name').val("")
			
        	var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
			showload();	
		}
		
		function pcUrl(){		
	        var roleNo = $('#roleNo').val()			
			var um = $("#userName").val();
	        var ui = $("#userId").val();
	    	window.location.href = "${ctx}/project/queryListPc?userName="+um + "&userId=" + ui+ "&roleNo=" + roleNo
		}
		//同意 
		function applayDelaySub(obj,projectNo){
			var type=obj;
			var projectNo=projectNo;
			$.ajax({
				type : "post",
				data : {
					type : type,
					projectNo : projectNo,
				},
				url : "${ctx}/project/agreeDelayApply",
				success : function(data) {
					var json = eval("(" + data +")");
					if (json.ok) {
						 layer.msg(json.message)
					}
				}
			})
		}
	</script>
</body>
</html>