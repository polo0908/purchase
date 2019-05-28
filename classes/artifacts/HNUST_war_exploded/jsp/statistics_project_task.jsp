<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/test.css">
    <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>项目任务统计列表</title>
    <style>
		/* body{
			text-align:center;
		} */
	</style>
</head>
<body class="complate-bgcolor">
	<div class="complate-content">
		<div class="complate-detail">
	        <h3>每个人的未完成数量：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">未完成数量</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${noFinishList}" var="noFinish">
				              <tr>
				                <td>
				                    <div>${noFinish.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty noFinish.noFinishCount?0:noFinish.noFinishCount}</div>
				                </td>
				            </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>最近30天的完成数量：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">完成数量</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${finishList}" var="finish">
				              <tr>
				                <td>
				                    <div>${finish.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty finish.finishCount?0:finish.finishCount}</div>
				                </td>
				              </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>每个人的及时完成率:</h3>
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
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="及时完成数量">及时完成数量</span>
			                    </th>
			                     <th>
			                        <span class="tab-link" title="所有完成数量">所有完成数量</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="及时完成率">及时完成率</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="平均任务用时">平均任务用时</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${onTimeList}" var="onTime">
				              <tr>
				                <td>
				                    <div>${onTime.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty onTime.onTimeFinish?0:onTime.onTimeFinish}</div>
				                </td>
				                 <td>
				                    <div>${empty onTime.allFinish?0:onTime.allFinish}</div>
				                </td>
				                <td>
				                    <div>${onTime.finishRatio}</div>
				                </td>
				                <td>
				                    <div>
				                      <c:if test="${onTime.finishRatio ne '0.0%' }">
				                         ${onTime.averageHours}/小时
				                      </c:if>
				                    </div>
				                </td>
				            </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>会议相关未完成任务数量(7天)：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">未完成数量</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${meetingRecordList}" var="meetingRecord">
				              <tr>
				                <td>
				                    <div>${meetingRecord.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty meetingRecord.meetingTaskNum?0:meetingRecord.meetingTaskNum}</div>
				                </td>
				              </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>