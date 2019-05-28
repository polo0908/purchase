<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/test.css">
    <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>会议列表</title>
</head>
<body class="list-bgcolor">
	<div class="main-container">
		<div><h3>会议列表</h3></div>
		<div class="main-table meeting-list-table">
		   <span><input type="text" name="searchProjectNo" id="searchProjectNo" placeholder="项目号"/></span>
		   <input type="hidden" value="${userName}" name="userName" id="userName"> 
		   <input type="hidden" value="${userId}" name="userId" id="userId"> 
		   <input type="hidden" value="${roleNo}" name="roleNo" id="roleNo"> 
		   <input name="" type="submit" value="查询" class="color-blocks-btn" onClick="searchProjectData(1)"/>
		   <input name="" type="submit" value="返回主页" class="color-blocks-btn" onClick="goBack()" id="goBackHtml"/>

		   <input name="" type="submit" value="录入会议" class="color-blocks-btn" onClick="addMeetingRecord()" id="goBackHtml"/>
		   
			<div class="table-wrap" style="margin-top:10px;">
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
			                    <th width="127">
			                        <span class="tab-link">项目号</span>
			                    </th>
			                    <th width="126">
			                        <span class="tab-link">会议名</span>
			                    </th>
			                    <th width="106">
			                        <span class="tab-link">录入者</span>
			                    </th>
			                    <th width="296">
			                        <span class="tab-link">会议纪要</span>
			                    </th>
			                    <th width="126"> 
			                       <span class="tab-link">日期</span>
			                    </th>
			                    <th width="116">
			                        <span class="tab-link">会议后任务数</span>
			                    </th>
			                     <th width="106">
			                        <span class="tab-link">操作</span>
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
			                <col>
			                <col>
			            </colgroup>
			            <tbody>
			             <c:forEach items="${meetingRecordList}" var="meetingRecord">
				              <tr>
				                <td width="126">
				                    <div>${meetingRecord.projectNo}</div>
				                </td>
				                <td width="125">
				                    <div>${meetingRecord.meetingName}</div>
				                </td>
				                <td width="106">
				                    <div>${meetingRecord.meetingInputer}</div>
				                </td>
				                <td width="292">
				                    <div>
				                    <c:if test="${fn:length(meetingRecord.meetingDescribe)>100 }">${ fn:substring(meetingRecord.meetingDescribe,0,100)}</c:if>
				                    <c:if test="${fn:length(meetingRecord.meetingDescribe)<=100 }">${meetingRecord.meetingDescribe}</c:if>
				                    </div>
				                </td>
				                <td width="126">
				                    <div><fmt:formatDate value="${meetingRecord.createDate}" pattern="yyyy-MM-dd"/></div>
				                </td>
				                 <td width="116">
				                    <div>${meetingRecord.taskNum}</div>
				                </td>
				           
				                <td width="106">
				                    <div><a href="${ctx}/meetingRecord/selectMeetingRecordDetails?meetingNo=${meetingRecord.meetingNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}">详情</a></div>
				                </td>
				            </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
			<div class="page-box">总数:${totalCount}每页:10条    当前页/总页:<span style='color:red'>${pageNumber}</span>/<span style='color:red'>${countPage}</span>&nbsp; 
		    <a href="#" class='emanagergetpagea first-padding' onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a>
		    <a href="#" class='emanagergetpagea first-padding' onclick="searchProjectData(2)" title='上一页(第1页)' class='a02'>上页</a>
<!-- 		    <a href="#" class='emanagergetpagea' title='1' class='a02'>1</a>
		    <a href="#" class='emanagergetpagea' title='2' class='a02'>2</a>
		    <a href="#" class='emanagergetpagea' title='3' class='a02'>3</a>
		    <a href="#" class='emanagergetpagea' title='4' class='a02'>4</a> -->
		    <a href="#" class='emanagergetpagea' onclick="searchProjectData(3)" title='下一页(第3页)' class='a02'>下页</a>
		    <a href="#" class='emanagergetpagea' onclick="searchProjectData(4)" title='最后一页' class='a02'>尾页</a>
	        </div>
	        <input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}" >
	        <input type="hidden" id="countPage" name="countPage" value="${countPage}" >
        </div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var userName=$("#userName").val();
	if(userName==null || userName=='' ||userName==undefined){
		$("#goBackHtml").hide();
	}
})

function addMeetingRecord(){
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 window.location.href="${ctx}/meetingRecord/inputMeetingRecord?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
};
function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}
//筛选列表
function searchProjectData(obj){
	 var projectNo=$("#searchProjectNo").val();
	 var pageNumber=$("#pageNumber").val();
	 var countPage=$("#countPage").val();
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var type=obj;
	 // 1 第一页  2.上一页  3.下一页 4.尾页
	 if(type==1){
		 pageNumber=1; 
	 }
	 if(type==2){//上一页
		 if(pageNumber==1){
			 pageNumber=1
		 }else{
			 pageNumber= Number(pageNumber)-1;
		 }
	 }
	 if(type==3){//下一页
		 if(pageNumber==countPage){
			 pageNumber=countPage;
		 }else{
			 pageNumber=Number(pageNumber)+1; 
		 }
	 }
	 if(type==4){//尾页
		 pageNumber=countPage;
	 }
	 window.location.href="${ctx}/meetingRecord/selectMeetingRecordList?projectNo="+projectNo+"&pageNumber="+pageNumber+"&userName="+userName+"&userId="+userId+"&roleNo="+roleNo;
}
</script>
</html>