<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.addInput {
	position: relative;
	top: 35px;
	left: -830px;
}
</style>
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/test.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>项目任务列表</title>
</head>
<body class="list-bgcolor">
	<div class="main-container">
		<div>
			<h3>
				项目任务列表 <i style="margin-left: 15px;">(任务平均用时<fmt:formatNumber
						value="${averageHours}" pattern="0" />(小时),及时完成率${finishRatio},目前
					拖得最长的任务 已经<fmt:formatNumber value="${maxDate}" pattern="0" />天了)
				</i>
			</h3>
		</div>
		<div class="main-table meeting-list-table">
			<span><input type="text" name="searchProjectNo"
				id="searchProjectNo" placeholder="项目号" value="${searchProjectNo}"  /></span> <span><input
				type="text" name="searchName" id="searchName" placeholder="姓名"
				value="${searchName}" /></span> <span>任务类型：<select
				id="sendOrReceive" name="sendOrReceive" class="project-status" value="${sendOrReceive}">
					<option value="0" <c:if test="${sendOrReceive eq '0'}"> selected="selected"</c:if>>全部</option>
					<option value="1" <c:if test="${sendOrReceive eq '1'}"> selected="selected"</c:if>>布置出的任务</option>
					<option value="2" <c:if test="${sendOrReceive eq '2'}"> selected="selected"</c:if>>收到的任务</option>
			</select>
			</span> <span> 状态：<select class="project-status"
				onchange="selectOnchange(this)">	
					<option value="-1"
						<c:if test="${taskStatus eq '-1'}"> selected="selected"</c:if>>所有</option>
					<option value="0"
						<c:if test="${taskStatus eq '0'}"> selected="selected"</c:if>>未完成</option>
					<option value="1"
						<c:if test="${taskStatus eq '1'}"> selected="selected"</c:if>>完成</option>
					<option value="2"
						<c:if test="${taskStatus eq '2'}"> selected="selected"</c:if>>暂停</option>
					<option value="3"
						<c:if test="${taskStatus eq '3'}"> selected="selected"</c:if>>取消</option>
			</select>
			</span> <input type="hidden" value="${taskStatus}" name="taskStatus"
				id="taskStatus"> <input type="hidden" value="${userName}"
				name="userName" id="userName"> <input type="hidden"
				value="${userId}" name="userId" id="userId"> <input
				type="hidden" value="${roleNo}" name="roleNo" id="roleNo"> <input
				type="submit" class="color-blocks-btn" value="查询"
				onClick="searchProjectData(1)" />
			<!--   <input type="submit" class="color-blocks-btn" value="统计列表" onClick="statisticsProjectTask()"/> -->
			<input type="submit" class="color-blocks-btn addInput" value="项目统计"
				id="projectSummary" /> <input type="submit" class="color-blocks-btn addInput"
				value="任务录入" onClick="addProjectTask()" id="goBackHtml" /> <input
				type="submit" class="color-blocks-btn addInput" value="项目列表"
				onClick="goBack()" id="goBackHtml" /> <input type="submit"
				class="color-blocks-btn" style="position: relative;left: 285px;top: 6px;" value="会议列表" onClick="projectMeetingList()"
				id="goBackHtml" />
				<input type="submit"
				class="color-blocks-btn" style="position: relative;left: 285px;top: 6px;" value="60天质量分析表列表" onClick="qualityAnalysisTable()"
				id="goBackHtml" />

			<p>
			<div class="table-wrap">
				<div class="table-head">
					<div class="table-head-wrap">
						<table class="grid">
							<colgroup>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th width="49"><span class="tab-link">项目号</span></th>
									<th width="49"><span class="tab-link">发起人</span></th>
									<th width="48"><span class="tab-link">接受人</span></th>
									<th width="90"><span class="tab-link">时间</span></th>
									<th width="190"><span class="tab-link">简述</span></th>
									<th width="190"><span class="tab-link">紧急的理由</span></th>
									<th width="81"><span class="tab-link">任务状态</span></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="table-content">
					<table class="grid">
						<colgroup>
							<col>
							<col>
							<col>
							<col>
							<col>
							<col>
							<col>
						</colgroup>
						<tbody>
							<c:forEach items="${projectTaskList}" var="projectTask">
								<!-- 接收到的任务 -->
								<c:if test="${roleNo!=100 and  roleNo!=99 and roleNo!=98 and roleNo!=97 }">
									<tr class="project-flag">
										<input type="hidden" name="taskFlag"
											value="${projectTask.taskFlag}">
										<td width="50">
											<div>${projectTask.projectNo}</div>
											<!--  <c:choose>
												<c:when
													test="${fn:containsIgnoreCase(projectTask.accepter,userName) && !fn:containsIgnoreCase(projectTask.initiator,userName)}">
										           (收到的任务)
										     </c:when>
												<c:when
													test="${fn:containsIgnoreCase(projectTask.accepter,userName) && fn:containsIgnoreCase(projectTask.initiator,userName)}">

												</c:when>
												<c:otherwise>
										           (布置出的任务)
										     </c:otherwise>
											</c:choose> -->
										</td>
										<td width="51">
											<div>${projectTask.initiator}</div>
										</td>
										<td width="50">
											<div>${projectTask.accepter}</div>
										</td>
										<td width="91">
											<div>
												发起:
												<fmt:formatDate value="${projectTask.startTime}"
													pattern="yyyy-MM-dd" />
												<br> 要求:
												<fmt:formatDate value="${projectTask.finishTime}"
													pattern="yyyy-MM-dd" />
												<br> 预计:
												<fmt:formatDate value="${projectTask.expectFinishTime}"
													pattern="yyyy-MM-dd" />
												<br> 实际:
												<fmt:formatDate value="${projectTask.operatorTime}"
													pattern="yyyy-MM-dd" />
												<br>
											</div>
										</td>
										<td width="190">
											<div>${projectTask.description}</div>
										</td>
										<td width="190">
											<div>${projectTask.urgentReason}</div>
										</td>
										<td width="81" class="mession-status">
											<div>
												<c:if test="${projectTask.taskStatus eq '0'}">未完成</c:if>
												<c:if test="${projectTask.taskStatus eq '1'}">已完成</c:if>
												<c:if test="${projectTask.taskStatus eq '2'}">暂停</c:if>
												<c:if test="${projectTask.taskStatus eq '3'}">取消</c:if>
												<c:if
													test="${projectTask.operator!= '' and projectTask.operator!= null}">
		                                     (${projectTask.operator})
		                                   </c:if>
												<a
													href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">详情操作</a>
											</div>
										</td>
									</tr>
								</c:if>


								<!-- 企业管理员查看所有用户 -->
								<c:if test="${roleNo==100 or roleNo==99 or roleNo==98 or roleNo==97  }">
									<tr class="project-flag">
										<input type="hidden" name="taskFlag"
											value="${projectTask.taskFlag}">
										<td width="50">
											<div>${projectTask.projectNo}</div>
										</td>
										<td width="51">
											<div>${projectTask.initiator}</div>
										</td>
										<td width="50">
											<div>${projectTask.accepter}</div>
										</td>
										<td width="91">
											<div>
												发起:
												<fmt:formatDate value="${projectTask.startTime}"
													pattern="yyyy-MM-dd" />
												<br> 要求:
												<fmt:formatDate value="${projectTask.finishTime}"
													pattern="yyyy-MM-dd" />
												<br> 预计:
												<fmt:formatDate value="${projectTask.expectFinishTime}"
													pattern="yyyy-MM-dd" />
												<br> 实际:
												<fmt:formatDate value="${projectTask.operatorTime}"
													pattern="yyyy-MM-dd" />
												<br>
											</div>
										</td>
										<td width="190">
											<div>${projectTask.description}</div>
										</td>
										<td width="190">
											<div>${projectTask.urgentReason}</div>
										</td>
										<td width="81" class="mession-status">
											<div>
												<c:if test="${projectTask.taskStatus eq '0'}">未完成</c:if>
												<c:if test="${projectTask.taskStatus eq '1'}">已完成</c:if>
												<c:if test="${projectTask.taskStatus eq '2'}">暂停</c:if>
												<c:if test="${projectTask.taskStatus eq '3'}">取消</c:if>
												<c:if
													test="${projectTask.operator!= '' and projectTask.operator!= null}">
		                                     (${projectTask.operator})
		                                   </c:if>
												<a
													href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">详情操作</a>
											</div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="page-box">
				总数:${totalCount}每页:10条 当前页/总页:<span style='color: red'>${pageNumber}</span>/<span
					style='color: red'>${countPage}</span>&nbsp; <a href="#"
					class='emanagergetpagea first-padding'
					onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a> <a
					href="#" class='emanagergetpagea first-padding'
					onclick="searchProjectData(2)" title='上一页(第1页)' class='a02'>上页</a>
				<a href="#" class='emanagergetpagea' onclick="searchProjectData(3)"
					title='下一页(第3页)' class='a02'>下页</a> <a href="#"
					class='emanagergetpagea' onclick="searchProjectData(4)"
					title='最后一页' class='a02'>尾页</a>
			</div>
			<input type="hidden" id="pageNumber" name="pageNumber"
				value="${pageNumber}"> <input type="hidden" id="countPage"
				name="countPage" value="${countPage}">
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('.project-flag').each(
				function(i) {
					var project_flag = $(this).find('input[name="taskFlag"]')
							.val();
					if (project_flag == 1) {
						//$(this).css("border","1px solid red");
						$(this).css("border", "1px solid red").find("td").css(
								"border-bottom", "none").end().find("td:first")
								.css("border-left", "none");
						$(this).prev("tr").find("td").css("border-bottom",
								"none");
					}
				})
	})
	$(function() {
		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined
				|| userName == "null") {
			$("#goBackHtml").hide();
		}
	})

	//进入项目统计界面
	$("#projectSummary").click(
			function() {
				var roleNo = $("#roleNo").val();
				var userId = $("#userId").val();
				var userName = $("#userName").val();
				window.location.href = "${ctx}/jsp/project-summary.jsp?roleNo="
						+ roleNo + "&userId=" + userId + "&userName="
						+ userName;
			})

	//进入会议列表
	function projectMeetingList() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/meetingRecord/selectMeetingRecordList?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//进入质量会议列表
	function qualityAnalysisTable() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/qualityAnalysisTable/listItems?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//进入录入任务页面
	function addProjectTask() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.location.href = "${ctx}/projectTask/addTask?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName;
	}
	function statisticsProjectTask() {
		window.location.href = "${ctx}/projectTask/statisticsProjectTask";
	}

	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/jsp/project_list.jsp?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}

	function selectOnchange(obj) {
		var taskStatus = $(obj).find('option:selected').val();
		$("#taskStatus").val(taskStatus);
	}

	//筛选列表
	function searchProjectData(obj) {
		var searchName = $("#searchName").val();
		var projectNo = $("#searchProjectNo").val();
		var pageNumber = $("#pageNumber").val();
		var countPage = $("#countPage").val();
		var taskStatus = $("#taskStatus").val();
		var type = obj;
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var sendOrReceive = $('#sendOrReceive').val()

		// 1 第一页  2.上一页  3.下一页 4.尾页
		if (type == 1) {
			pageNumber = 1;
		}
		if (type == 2) {//上一页
			if (pageNumber == 1) {
				pageNumber = 1
			} else {
				pageNumber = Number(pageNumber) - 1;
			}
		}
		if (type == 3) {//下一页
			if (pageNumber == countPage) {
				pageNumber = countPage;
			} else {
				pageNumber = Number(pageNumber) + 1;
			}
		}
		if (type == 4) {//尾页
			pageNumber = countPage;
		}
		window.location.href = "${ctx}/projectTask/projectTaskList?projectNo="
				+ projectNo + "&searchName=" + searchName + "&pageNumber="
				+ pageNumber + "&taskStatus=" + taskStatus + "&userName="
				+ userName + "&roleNo=" + roleNo + "&userId=" + userId
				+ "&sendOrReceive=" + sendOrReceive;
	}
</script>
</html>