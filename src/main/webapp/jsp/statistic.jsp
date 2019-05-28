<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<title>统计页</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
	<div class="statistic w1250">
		<div class="top clearfix">
			<div class="pull-left">
				<h3 class="pull-left">统计页</h3>
<!-- 				<div class="form-group pull-left date"> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<div class="input-group date form_date col-sm-6" -->
<!-- 							data-date="" data-date-format="yyyy-mm-dd"> -->
<!-- 							<input id="quoteEndDate" name="quoteEndDate" -->
<!-- 								class="form-control brt brt_7" size="16" type="text" value="" -->
<!-- 								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly -->
<!-- 								requiredate onchange="checkDeadline()"> <span class="input-group-addon"><span -->
<!-- 								class="glyphicon glyphicon-calendar"></span></span> -->
<!-- 						</div> -->
<!-- 						<span></span> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<button class="blue1 btn" onclick="selectWeek(1)">查询一周</button>
				<button class="blue1 btn" onclick="selectWeek(2)">查询一个月</button>
			</div>
			<div class="pull-right mt-18">
				<button class="blue2 btn" onclick="window.location='${ctx}/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}'">返回主页</button>
				<button class="blue2 btn" onclick="window.location = '${ctx}/index.jsp'">退出系统</button>
				<img src="../img/logo.png" alt="">
			</div>			
		</div>
 <form id="form" class="roleform form-horizontal"  method="get" action="/statistics/selectAllByDate" onclick="return false;"> 
		<input type="hidden" name="dateType" id="dateType">
		<div class="wrap">
			<h4>一、启动项目数量<span>${totalCount}</span>，总金额<span>${totalAmount} （万美元）</span></h4>
			<p>启动最多的销售是<span>${saleName}</span>(${saleNewNum}新客户${saleOldNum}老客户)，</p>
			<p>启动最多的跟单是<span>${followName}</span>(${followNewNum}新客户${followOldNum}老客户)，</p>
			<p>启动最多的采购是<span>${purcharName}</span>(${purchaseNewNum}新客户${purchaseOldNum}老客户)。</p>
		</div>
		<div class="wrap">
			<h4>二、报价发出<span>${num}</span>个项目，预计总金额是<span>${total}</span> (万美元)，报价发出平均时间是<span>${time}</span>小时</h4>
			<p>报价最多的销售是<span>${sale}</span>，</p>
			<p>报价最多的报价工程师是<span>${quoter}</span>，</p>
			<p>报价最多的助理是<span>${quotationAssistant}</span>。</p>
		</div>
		<div class="wrap">
			<h4>三、采购统计</h4>
			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th >采购人员</th>
						<th class="w100">生产项目总数</th>
						<th class="w100">返单项目数</th>
						<th class="w100">返单总金额（万美元）</th>
						<th class="w100">新项目数量</th>
						<th class="w100">新项目总金额（万美元）</th>
						<th class="w100">到账一个月的项目数量</th>
						<th class="w100">到账二个月的项目数量</th>
						<th class="w100">到账三个月的项目数量</th>
						<th class="w100">到账三个月以上的项目数量</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${emailUsers}" varStatus="i">
				   <c:if test="${obj.roleNo == 6}">
						<tr>
							<td>${obj.userName}</td>
							<td>${obj.personData.processNum}</td>
							<td>${obj.personData.reorderNum}</td>
							<td>${obj.personData.totalAmount}</td>
							<td>${obj.personData.newNum}</td>
							<td>${obj.personData.totalNewAmount}</td>
							<td>${obj.personData.oneMonthCount}</td>
							<td>${obj.personData.twoMonthCount}</td>
							<td>${obj.personData.threeMonthCount}</td>
							<td>${obj.personData.upThreeMounthCount}</td>
						</tr>
					</c:if>
				</c:forEach>	
				</tbody>
			</table>
		</div>
		<div class="wrap">
			<h4>四、跟单统计</h4>
			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th >跟单人员</th>
						<th class="w100">生产项目总数</th>
						<th class="w100">返单项目数</th>
						<th class="w100">返单总金额（万美元）</th>
						<th class="w100">新项目数量</th>
						<th class="w100">新项目总金额（万美元）</th>
						<th class="w100">到账一个月的项目数量</th>
						<th class="w100">到账二个月的项目数量</th>
						<th class="w100">到账三个月的项目数量</th>
						<th class="w100">到账三个月以上的项目数量</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${emailUsers}" varStatus="i">
				   <c:if test="${obj.roleNo == 5 && obj.job == '跟单'}">
					<tr>
						<td>${obj.userName}</td>
						<td>${obj.personData.processNum}</td>
						<td>${obj.personData.reorderNum}</td>
						<td>${obj.personData.totalAmount}</td>
						<td>${obj.personData.newNum}</td>
						<td>${obj.personData.totalNewAmount}</td>
						<td>${obj.personData.oneMonthCount}</td>
						<td>${obj.personData.twoMonthCount}</td>
						<td>${obj.personData.threeMonthCount}</td>
						<td>${obj.personData.upThreeMounthCount}</td>
					</tr>
					</c:if>
				  </c:forEach>	
				</tbody>
			</table>
		</div>
	  </form>
	</div>
</body>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>	
</html>
<script>
	/* 日历插件*/
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 4,
		forceParse : 0
	});
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	})
	
	
	
	
	function selectWeek(dateType){
		$('#dateType').val(dateType);
		$('#form').submit();
	}
	
	
	
</script>




















