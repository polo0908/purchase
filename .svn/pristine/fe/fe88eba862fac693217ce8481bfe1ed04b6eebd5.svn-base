<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo = request.getParameter("projectNo");
	String roleNo = request.getParameter("roleNo");
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>任务系统，质检情况填写</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">
<style type="text/css">
.position_relative ul {
	background-color: #fff;
	position: absolute;
	top: 40px;
	left: 0;
	z-index: 10;
}

.add_tc {
    width: 320px;
    height: 270px;
    box-shadow: 3px -2px 6px rgba(43, 43, 43, 0.5), -3px 2px 6px rgba(43, 43, 43, 0.5);
    position: fixed;
    top: 40px;
    left: 50%;
    margin-left: -160px;
    z-index: 12;
    background-color: #fff;
    text-align: center;
    padding-top: 10px;
    display: none;
    margin-top: 80px;
    padding: 13px 11px 17px 13px;
}

.add_tc_zb {
    height: auto;
    width: 544px;
}

.add_tc .s_c {
    position: relative;
    text-align: left;
    padding-left: 10px;
    margin-bottom: 10px;
}

.add_tc button {
    width: 100px;
    height: 30px;
    line-height: 30px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.add_tc_zb .s_c input {
    position: absolute;
    width: 120px;
    height: 90px;
    opacity: 0;
    top: 0;
    left: 0;
}

.add_tc_zb .imgs li {
    float: left;
    width: 120px;
    height: 90px;
    border: 1px solid #999;
    text-align: center;
    line-height: 90px;
    position: relative;
    margin-right: 12px;
    margin-bottom: 10px;
    color: #666;
    font-size: 14px;
}

.pic-checkbox{
      text-align: left;
}


.li_img{
    width: 146px;
    height: 93px;
    overflow: hidden;
    line-height: 131px;
    text-align: center;
    margin-right: 16px;
}
.add_tc input{height:auto;}
.add_tc .btns2{margin-top:10px;}
@media screen and (min-width:769px){
	.add_tc_zb{width:435px;}
}
@media screen and (max-width:768px){
.add_tc_zb{width:100%;left:0;margin-left:0;}
}

</style>
</head>
<body>



	<div class="quality add_detail">
		<input type="hidden" id="projectNoId" value="${projectNoId}">
		<input type="hidden" id="roleNo" value="${roleNo}"> <input
			type="hidden" id="userId" value="${userId}"> <input
			type="hidden" id="userName" value="${userName}"> <input
			type="hidden" id="projectNo" value="${projectNo}"> <input
			type="hidden" id="inspectionForm"> <input type="hidden"
			id="inspectionPath">

		<div class="title">
			<span class="glyphicon glyphicon-menu-left"
				onclick="window.location='${ctx}/project/showDetails?projectNo=${projectNo}&userName=${userName}'"></span>质检情况
		</div>
		
		<div class="add_tc add_tc1 add_tc_zb">
				<input type="hidden" id="inspectionPlanId">
				<input type="hidden" id="inspectionPlan_li_index">
						<p></p>
						<p class="pic-checkbox">证明图片（最多3张）</p>
						<form id="file_upload_id" onsubmit="return false;" method="post" enctype="multipart/form-data">
							<div class="s_c">
							    <input type="hidden" name="fileNames" id="fileNames" value="">
								<ul class="imgs clearfix" id="inspectionPic">
									<li class="i-upload"><span class="glyphicon glyphicon-plus"></span><input type="file" id="uploadFile" name="files" onchange="fileChange(this)" multiple></li>
								</ul>
							</div>
						</form>		
						<p class="pic-checkbox"><input type="checkbox" id="p_check">加入总体的包装图</p>								
						<p class="pic-checkbox"><input type="checkbox" id="m_check">加入总体的材料图</p>								
						<textarea id="content" name="content" class="form-control" placeholder="请输入改点的文字说明"></textarea>
						<div class="btns2 clearfix">
							<button class="pull-left" onclick="cancelInspection(this)">取消</button>
							<button class="pull-right" onclick="addInspection(this)">保存</button>
						</div>
        </div>
		
		<div class="wrap wrap1">
			<p>
				项目号&nbsp;&nbsp;&nbsp;&nbsp;<span id="projectNo">${projectNo}</span>
			</p>
		</div>

		<div class="wrap wrap2">
			<p>检验报告</p>
			<div class="sele_">
				<label>这次上传的是：</label> <select id="type" name="type"
					onchange="change_stage(this)">
					<option value="0" <c:if test="${checkType eq '样品'}">selected</c:if>>样品检验</option>
					<!-- <option value="1">大货样品</option> -->
					<option value="2" <c:if test="${checkType eq '过程'}">selected</c:if>>中期检验</option>
					<option value="3" <c:if test="${checkType eq '出货'}">selected</c:if>>终期检验</option>
				</select> <span>报告</span>
			</div>
			<table class="table table-bordered table1 mt10">
				<tbody>
					<tr>
						<td>产品名</td>
						<td>${projectName}</td>
					</tr>
					<tr>
						<td>图号</td>
						<td>
						 <c:forEach var="obj" items="${distinctPlan}" varStatus="i">
						    <p><input type="checkbox" class="pic_num" value="${obj.productComponent}" onchange="select_img(this)"><span>${obj.productComponent}</span></p>
						 </c:forEach>
						 <p><input type="checkbox" class="pic_num" value="其他"><span>其他</span><input class="form-control"></p>
						</td>
					</tr>
					<tr>
						<td>检验地点</td>
						<td class="facotry_td"><label class="mr10"><input
								type="checkbox" name="place" value="公司"> 公司</label> <label
							class="mr10"><input type="checkbox" name="place"
								value="仓库"> 仓库</label> <label><input type="checkbox"
								name="place" value="工厂"> 工厂</label>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control" name="factory" placeholder="请输入工厂名">
								<ul>

								</ul>
							</div>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control mt10" name="factory"
									placeholder="请输入工厂名">
								<ul>

								</ul>
							</div>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control mt10" name="factory"
									placeholder="请输入工厂名">
								<ul>

								</ul>
							</div></td>
					</tr>
					<tr>
						<td>检验费时</td>
						<td><input class="form-control" field="spendTime"
							placeholder=""></td>
					</tr>
					<tr>
						<td>检验日期</td>
						<td>
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="checkDate" field="checkDate" name="checkDate"
									class="form-control brt brt_7" size="16" type="text" value=""
									place="选择日期" placeholder="选择日期" readonly requiredate> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>订单量</td>
						<td><input class="form-control" field="orders" placeholder=""></td>
					</tr>
				</tbody>
			</table>
			<form id="detail_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>细节</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="bad_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>不良品</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="material_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>材质证明</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="package_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>包装图(开箱比例${openRate==null?'暂无':openRate}${openRate!=null?'%':''}，质检检验要标好箱号和生产日期并拍照)</span><br>
					<span>实际开箱比例系统会自动计算</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<table class="table table-bordered table2">
				<tbody>
					<tr>
						<td>总箱数</td>

						<td><input class="form-control" field="boxNumber" placeholder="数字"></td>
					</tr>
					<tr>
						<td>每箱数量</td>

						<td><input class="form-control" field="perQty" placeholder=""></td>
					</tr>
					<tr>
						<td>数量清点</td>

						<td><input class="form-control" field="inventoryQty"
							placeholder=""></td>
					</tr>
					<tr>
						<td>开箱数量</td>

						<td><input class="form-control" field="openQty"  
							placeholder=""></td>
					</tr>
				</tbody>
			</table>
			<form id="check_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="" />
				<div class="imgs add_jy">
					<span>检验表格</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<div class="add_inputs">
				<!-- <div class="mb10 add_checkbox" id="stage_div">
					<label>
						<input type="checkbox" name="isAllRight"/>
						<span>含本报告在内如果所有<span class="project-stage">样品</span>阶段产品已经完全没有问题请勾选我（将会导致任务系统内的<span class="project-stage">样品生产阶段</span>结束）</span>
					</label>
				</div> -->
				<div class="mb10">
					<div>有几个尺寸 因为没检具而没法测量？</div>
					<input type="text" id="no_check"
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
				</div>
				<div class="mb10">
					<div>有几个关键尺寸超差？</div>
					<input type="text" id="key_size_exceed"
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
				</div>
				<div class="factory_history">
					<div>工厂有做过：</div>
					<label><input type="checkbox" name="sample" value="首样检验">首样检验</label>
					<label><input type="checkbox" name="process" value="过程检验">过程检验</label>
					<label><input type="checkbox" name="feed" value="进料检验">进料检验</label>
					<label><input type="checkbox" name="none" value="没有">没有</label>
				</div>
				<div>
				    <div>检验计划相关要求：<span style="color:red">请注意，有图的地方务必点击+号插图！！！</span></div>
				    <table class="add_tabel table">
				        <tbody id="check_body">
				        <c:forEach var="obj" items="${distinctPlan}" varStatus="i">
				           <tr class="check check_${i.index}" style="display:none;" filed_date = "<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd" />">
				              <td>
                                <span>${obj.productComponent}</span>	
                                <p>要求完成点：<button class="check_btn" type="button" onclick="openInspection(this,${i.index})">请展开选择</button></p>			              
				              </td>
				              <td><input type="checkbox" onchange="selectWork(this,${i.index})" class="all-check" value="1"><span>做了</span></td>
				              <td><input type="checkbox" onchange="selectWork(this,${i.index})" class="all-check" value="0"><span>没做</span></td>				        
				              <td><span>说明图/文字</span></td>				        			        
				           </tr>
				            <c:forEach var="plan" items="${plans}" varStatus="j">
				                 <c:if test="${obj.productComponent == plan.productComponent}">
				                  <tr class="check_s check_index_${i.index}" style="display:none;" filed="${plan.id}">
					                 <td>
					                   <span>${plan.type}:${plan.productStandards}</span>	
					                 </td>
					                 <td><input type="checkbox" value="1" name="work" onchange="selectWorkOnly(this,${i.index})" <c:if test="${plan.isWork == 1}">checked</c:if>><span>做了</span></td>
				                     <td><input type="checkbox" value="0" name="no_work" onchange="selectWorkOnly(this,${i.index})" <c:if test="${plan.isWork == 0}">checked</c:if>><span>没做</span></td>
				                     <td><button class="t-btn" onclick="openInspectionImg('${plan.id}',${j.index})"
				                     <c:if test="${(plan.content!=null&&plan.content!='') || (plan.inspectionPic!=null&&plan.inspectionPic!='')}">
				                     style="color:green;"
				                     </c:if>
				                     >+</button></td>
				                  </tr>
				                 </c:if>				                 
				            </c:forEach>
			            </c:forEach>	           				        
				        </tbody>
				    </table>
				</div>
			</div>
			<div class="report">
				<div class="radios">
					<div class="radio_0 clearfix">
						<input type="radio" name="state" value="0" checked> <span>没问题</span>
					</div>
					<div class="question1 noIssue">
						<p>注意,可选择将下列工厂的下列生产阶段结束:(已经默认勾选任务发布者觉得可结束的阶段)</p>
				    <c:forEach var="obj" items="${factoryList}" varStatus="i">
								<div class="factorys" filed="${obj.id}">
								<p>${obj.contractNo}  ${obj.factoryName}</p>
									<c:if test="${obj.sampleDate != null && obj.orderNature == 1}">
											<div class="facotry_list clearfix">								
												<div class="s1">
													<span class="mr5 w55">样品</span><input type="checkbox"
														name="select" <c:if test="${obj.projectStage == 0}">checked</c:if> value="0">
												</div>
												<div class="s2">
													<span class="mr5 ml15">完成日期</span>
													<div class="input-group date form_date" data-date=""
														data-date-format="yyyy-mm-dd">
														<input class="form-control brt brt_7" size="16" type="text" value="${obj.sampleFinishTime}"
															place="选择日期" placeholder="选择日期" readonly="" requiredate="">
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-calendar"></span></span>
													</div>
												</div>
											</div>
									</c:if>
									<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">
										<div class="facotry_list clearfix">
											<div class="s1">								
												<span class="mr5 w55">大货</span>
												<input type="checkbox" name="select" <c:if test="${obj.projectStage == 1}">checked</c:if> value="1"> 
											</div>
											<div class="s2">									
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.productFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${obj.orderNature == 2}">
										<div class="facotry_list clearfix">
											<div class="s1">
											<span class="mr5 w55">返修补货</span><input type="checkbox"
												name="select" value="2" <c:if test="${obj.projectStage == 2}">checked</c:if>> 
											</div>	
											<div class="s2">								
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.repairReplenishmentFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>	
									</c:if>							
								</div>
						</c:forEach>
					</div>
					<div class="radio_0 clearfix second_radio">
						<input type="radio" name="state" value="1"> <span>有问题，但已经处理</span>
					</div>
					 <div class="explain" style="display: none">
							<%-- <div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div> --%>
							<textarea id="explain" class="form-control" placeholder="原因说明"></textarea>
						</div> 
					<div class="question1 deal" style="display:none;">
						<p><i class="red">注意</i>,可选择将下列工厂的下列生产阶段结束:(已经默认勾选任务发布者觉得可结束的阶段)</p>
							 <c:forEach var="obj" items="${factoryList}" varStatus="i">
								<div class="factorys" filed="${obj.id}">
								<p>${obj.contractNo}  ${obj.factoryName}</p>
								   <c:if test="${obj.sampleDate != null && obj.orderNature == 1}">
										<div class="facotry_list clearfix">
											<div class="s1">
												<span class="mr5 w55">样品</span><input type="checkbox"
													name="select" value="0" <c:if test="${obj.projectStage == 0}">checked</c:if>>
											</div>
											<div class="s2">
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.sampleFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">
										<div class="facotry_list clearfix">
											<div class="s1">								
												<span class="mr5 w55">大货</span>
												<input type="checkbox" name="select" value="1" <c:if test="${obj.projectStage == 1}">checked</c:if>> 
											</div>
											<div class="s2">									
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.productFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${obj.orderNature == 2}">
									<div class="facotry_list clearfix">
										<div class="s1">
										<span class="mr5 w55">返修补货</span><input type="checkbox"
											name="select" value="2" <c:if test="${obj.projectStage == 2}">checked</c:if>> 
										</div>	
										<div class="s2">								
											<span class="mr5 ml15">完成日期</span>
											<div class="input-group date form_date" data-date=""
												data-date-format="yyyy-mm-dd">
												<input class="form-control brt brt_7" size="16" type="text" value="${obj.repairReplenishmentFinishTime}"
													place="选择日期" placeholder="选择日期" readonly="" requiredate="">
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>	
									</c:if>							
								</div>
							 </c:forEach>
						
					 </div>
					<div class="radio_0 clearfix last_radio">
						<input type="radio" name="state" value="2"> <span>有问题，需要采购解决</span>
						<div class="task">
							<div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div>
							<textarea id="taskDetail" class="form-control" placeholder="任务说明"></textarea>
						</div>

					<%-- 	<div class="explain" style="display: none">
							<div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div>
							<textarea id="explain" class="form-control" placeholder="原因说明"></textarea>
						</div> --%>
					</div>
				</div>
			</div>
			<div class="line mt10"></div>
			<div class="jy_result">
				<p>检验结论:(会随检验报告的连接抄送在邮件正文中)</p>
				<textarea class="form-control" id="conclusion"></textarea>
			</div>
		</div>
		<%-- <div class="btns text-right">
			<label>本质检报告针对${projectNo}项目下的</label>
			<select name="task_sel" id="task_sel">
			    <option></option>
			    <c:forEach var="obj" items="${tasks}" varStatus="i">
			         <option value="${obj.id}">${obj.description}</option>
			    </c:forEach>
			</select>
			<label style="color:red;">如果任务未显示，可能是你已经点击了完成，直接上传即可</label>
		</div>  --%>
		<div class="btns text-right">
			<button class="btn" onclick="savaAll()">提交所有上传图片和问题</button>
			<span class="tips" style="color: red;" id="subHtml"></span>
		</div>


		<div class="w-out-box" id="show_upload_dialog" style="display: none;">
			<div class="weui_mask"></div>
			<div class="w-weui_dialog">
				<div id="container">

					<div class="content">
						<h1>上传进度</h1>
					</div>

					<!-- Progress bar -->
					<div id="progress_bar" class="ui-progress-bar ui-container">
						<div class="ui-progress" style="width: 0%;"
							id="ui-progress-upload">
							<span class="ui-label" style="display: none;">正在加载...<b
								class="value">0%</b></span>
						</div>
					</div>
					<!-- /Progress bar -->
					<a class="close-reveal-modal"
						style="color: #fff; font-size: 30px; position: absolute; right: 10px; top: 10px;"
						href="javascript:void(0);" onclick="cancel_upload()">×</a>
					<div class="content" id="main_content" style="display: none;">
						<p>加载完成。</p>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/upload-base.js"></script>
<script type="text/javascript" src="/js/jquery-form.js"></script>
<script src="/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/dist/lrz.all.bundle.js"></script>
<script>
	//阳工质检工厂检验确认修改
	$('.factory_history label')
			.click(
					function() {
						var name = $(this).find('input').attr('name');
						if ($('input[name="none"]').is(":checked")) {
							$(
									'input[name="sample"],input[name="process"],input[name="feed"]')
									.prop('disabled', true);
						} else {
							$(
									'input[name="sample"],input[name="process"],input[name="feed"]')
									.prop('disabled', false);
						}
						//选了前3个后面一个就不能选了
						if ($('input[name="sample"]').is(":checked")
								|| $('input[name="process"]').is(":checked")
								|| $('input[name="feed"]').is(":checked")) {
							$('input[name="none"]').prop('disabled', true);
						} else {
							$('input[name="none"]').prop('disabled', false);
						}
					});
</script>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	});
</script>
<script>
	window.onload = window.onresize = function() {

		var clientWidth = document.documentElement.clientWidth;

		document.getElementsByTagName("html")[0].style.fontSize =

		clientWidth / 768 * 40 + "px";
	}
</script>
<script>
	/*添加图片*/
	var $li = '<li><div class="img_in">' + '<img src="" alt="">'
			+ '</div><span class="glyphicon glyphicon-remove"></span>'
			+ '</li>';
	$('.quality .wrap2 .report input').click(
			function() {
				if ($('.quality .last_radio input').is(':checked')
						|| $('.quality .second_radio input').is(':checked')) {
					if ($('.quality .last_radio input').is(':checked')) {
						if ($('#purchase').text()) {
							$('.quality .wrap2 .report .task').show();
						} else {
							alert('该项目没有分配采购和跟单')
							$('#create_form')
									.find('input[name=state][value=0]').prop(
											'checked', true)
						}
						$('.quality .wrap2 .report .explain').hide();
					}
					//有问题,已解决问题说明
					if ($('.quality .second_radio input').is(':checked')) {
						if ($('#purchase').text()) {
							$('.quality .wrap2 .report .explain').show();
						} else {
							alert('该项目没有分配采购和跟单')
							$('#create_form')
									.find('input[name=state][value=0]').prop(
											'checked', true)
						}
						$('.quality .wrap2 .report .task').hide();
					}

				} else {
					$('.quality .wrap2 .report .task').hide();
					$('.quality .wrap2 .report .explain').hide();
				}
			})
</script>
<script>
	function show_drawingNames(obj) {
		var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			autTime();
			$('#upload_title').children().text("上传进度");
		}

		var projectNo = $('#projectNo').val();
		$(obj).parents('form').find('input[name=projectNo]').val(projectNo);
		// 先上传后获取上传文件路径
		$(obj)
				.parents('form')
				.ajaxSubmit(
						{
							type : "post",
							url : "${ctx}/upload/uploadProductPicAndChangeName.do",
							dataType : "text",
							async : false,
							success : function(str) {
// 								var result = eval('(' + str + ')');
								var result = str;
								if (result.ok) {
									var data = result.data;
									var addhtml = ''
									for (var i = 0; i < data.length; i++) {

										addhtml += '<li class="li_img"><div class="img_in">'
												+ '<a href="'
												+ data[i].replace(
														'/compressImg', '')
												+ '" target="_blank">'
												+ '<img src="'+data[i]+'" alt=""></a>'
												+ '</div><span class="glyphicon glyphicon-remove"></span>'
												+ '</li>';
									}
									$(obj).parents('form').find('.clearfix')
											.prepend(addhtml);
									/*删除当前图片*/
									$('.quality .wrap2 li span').on('click',
											function() {
												$(this).parent().remove();
											})
								}
							},
							error : function() {

							}
						});
	}

	//获取上传质检报告
	function upload_form(obj) {
		var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			autTime();
			$('#upload_title').children().text("上传进度");
		}
		var projectNo = $('#projectNo').val()
		$(obj).parents('form').find('input[name=projectNo]').val(projectNo)
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadQualityForm",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var inspectionPath = result.data.path;
					var originalFilename = result.data.originalFilename;
					$(obj).next().text(originalFilename);
					$('#inspectionPath').val(inspectionPath);
					$('#inspectionForm').val(originalFilename);
				}
			},
			error : function() {

			}
		});
	}

	function savaAll() {
		var userName = $('#userName').val()
		var roleNo = $('#roleNo').val()
		var userId = $('#userId').val()
		var projectNo = $('#projectNo').val()
		var taskId = $('#task_sel').val();
		var noCheck = $('#no_check').val();
		if(!noCheck){
			alert("请填写有几个尺寸因为没检具?");
			return false
		}		
			
		var keySizeExceed = $('#key_size_exceed').val();
		if(!keySizeExceed){
			alert("请填写有几个关键尺寸超差");
			return false;
		}

		var detailUrl = '';
		var badUrl = '';
		var materialUrl = '';
		var packageUrl = '';
		var checkUrl = '';
		var purchaseName = '';
		$('#detail_form').find('a').each(function() {
			if (!detailUrl) {
				detailUrl = $(this).attr('href')
			} else {
				detailUrl = detailUrl + ';' + $(this).attr('href')
			}
		})
		$('#bad_form').find('a').each(function() {
			if (!badUrl) {
				badUrl = $(this).attr('href')
			} else {
				badUrl = badUrl + ';' + $(this).attr('href')
			}
		})
		$('#material_form').find('a').each(function() {
			if (!materialUrl) {
				materialUrl = $(this).attr('href')
			} else {
				materialUrl = materialUrl + ';' + $(this).attr('href')
			}
		})
		$('#package_form').find('a').each(function() {
			if (!packageUrl) {
				packageUrl = $(this).attr('href')
			} else {
				packageUrl = packageUrl + ';' + $(this).attr('href')
			}
		})
		$('#check_form').find('a').each(function() {
			if (!checkUrl) {
				checkUrl = $(this).attr('href')
			} else {
				checkUrl = checkUrl + ';' + $(this).attr('href')
			}
		})

		if (!detailUrl) {
			alert("请上传细节图片")
			return false
		}

		//各项保存的数据
		var param = {};
		$('.wrap2').find('input').each(function() {
			var field = $(this).attr('field');
			if (field) {
				param[field] = $(this).val()
			}
		})
		//遍历获取图号
		var picNum = '';
		$('.pic_num').each(function(){
			if ($(this).is(":checked")) {
				var pic = $(this).val();
				if(pic == '其他'){
					pic == $(this).next().next().val();
				}
				if(pic){
					picNum +=pic+","; 
				}				
			}
		})
		if(picNum){
			picNum = picNum.substring(0, picNum.length - 1);
			param['picNum'] = picNum;
		}else{
			alert("请选择检验图号");
			return false;
		}
		

		//获取公司和仓库
		var checkPlace = '';
		var factoryFlag = false;
		$('input[name="place"]').each(function() {
			if ($(this).is(":checked")) {
				var placeName = $(this).val();
				if (placeName != '工厂') {
					checkPlace += placeName + ",";
				} else {
					factoryFlag = true;
				}
			}
		})
		//获取工厂
		if (factoryFlag) {
			$('input[name="factory"]').each(function() {
				var factoryName = $(this).val();
				if (factoryName) {
					checkPlace += factoryName + ",";
				}
			})
		}
		if (checkPlace.length > 0) {
			checkPlace = checkPlace.substring(0, checkPlace.length - 1);
		}
		if(!checkPlace){
			alert("请填写检验地点");
			return false;
		}
		param["place"] = checkPlace;

		//获取工厂检验
		var factoryInspection = '';
		$('.factory_history').find('input[type="checkbox"]:checked').each(function() {
			factoryInspection += $(this).val() + ",";
		})
		if (factoryInspection) {
			factoryInspection = factoryInspection.substring(0,
					factoryInspection.length - 1);
			param['factoryInspection'] = factoryInspection;
		}else{
			alert("请勾选工厂有做过检验情况！");
			return false;
		}
		//是否所有产品已经完全没有问题
/* 		if ($('input[type="checkbox"][name="isAllRight"]').is(':checked')) {
			param['isAllRight'] = 1;
		} */
	
		
		var state = ''
		$('.report').find('input[name=state]').each(function() {
			if ($(this).prop('checked')) {
				state = $(this).val()
			}
		})
		if (state == '2') {
			if ($('#taskDetail').val() == '') {
				alert('请输入任务内容')
				return false
			}
			purchaseName = $('#purchase').text()
		}
		if (state == '1') {
			if ($('#explain').val() == '') {
				alert('请输入任务内容')
				return false
			}
			purchaseName = $('#purchase').text()
		}
		
		
		//工厂完成对应合同信息
		var factoryListStr = '';
		var flag = true;
        var factoryList=[];
        if(state == 0){
        	$('.noIssue').find('.factorys').each(function(){       	
            	var This = $(this);
            	var id = This.attr('filed');        	       	
            	var orderState = '';
            	var endDate = '';
            	var sampleFinishTime = null;
            	var productFinishTime = null;
            	var repairReplenishmentFinishTime = null;
            	if(This.find('input[name="select"]').is(":checked")){
            		orderState = This.find('input[name="select"]:checked').val();
            		endDate = This.find('input[name="select"]:checked').parent().siblings('.s2').find('input').val();
            		if(!endDate){
            			alert('请选择结束日期');
            			flag = false;
            			return false;
            		}
            		if(orderState == 0){
            			sampleFinishTime = endDate;
            		}
            		if(orderState == 1){
            			productFinishTime = endDate;
            		}
            		if(orderState == 2){
            			repairReplenishmentFinishTime = endDate;
            		}
            	}
            	if(!orderState){
            		return;
            	}
            	var factory = {"id":id,"productFinishTime":productFinishTime,
            			"sampleFinishTime":sampleFinishTime,"repairReplenishmentFinishTime":repairReplenishmentFinishTime};
            	factoryList.push(factory);
            })
            factoryListStr = JSON.stringify(factoryList);
            
        }else if(state == 1){
        	$('.deal').find('.factorys').each(function(){       	
            	var This = $(this);
            	var id = This.attr('filed');        	       	
            	var orderState = '';
            	var endDate = '';
            	var sampleFinishTime = null;
            	var productFinishTime = null;
            	var repairReplenishmentFinishTime = null;
            	if(This.find('input[name="select"]').is(":checked")){
            		orderState = This.find('input[name="select"]:checked').val();
            		endDate = This.find('input[name="select"]:checked').parent().siblings('.s2').find('input').val();
            		if(!endDate){
            			alert('请选择结束日期');
            			flag = false;
            			return false;
            		}
            		if(orderState == 0){
            			sampleFinishTime = endDate;
            		}
            		if(orderState == 1){
            			productFinishTime = endDate;
            		}
            		if(orderState == 2){
            			repairReplenishmentFinishTime = endDate;
            		}
            	}
            	if(!orderState){
            		return;
            	}
            	var factory = {"id":id,"productFinishTime":productFinishTime,
            			"sampleFinishTime":sampleFinishTime,"repairReplenishmentFinishTime":repairReplenishmentFinishTime};
            	factoryList.push(factory);
            })
            
            factoryListStr = JSON.stringify(factoryList);
        }
        
		
		if(!flag){
			return false;
		}
		
		
		//获取检验计划完成情况
        var planList = [];
		$('.check_s').each(function(){
			var id = $(this).attr('filed');
			This = $(this);
			if(This.find('input[type="checkbox"]').is(':checked')){
				var isWork = This.find('input[type="checkbox"]:checked').val();
				var plan = {"id":id,"isWork":isWork};
				planList.push(plan);
			}
		})
		//获取检验计划时间
		var inspectionCreateDate = $('#check_body').find('.check:first').attr('filed_date');
		
		
		var taskDetail = $('#taskDetail').val()
		var explain = $("#explain").val();
		var checkExplain = $("#checkExplain").val();
		var packageExplain = $("#packageExplain").val();
		$(".btn").attr("disabled", true).css("background-color", "#999");
		var type = $('#type').val();
		$
				.ajax({
					type : "post",
					url : "${ctx}/quality/saveQuality",
					data : {
						projectNoId:$('#projectNoId').val(),
						projectNo : projectNo,
						userName : userName,
						detailUrl : detailUrl,
						badUrl : badUrl,
						materialUrl : materialUrl,
						packageUrl : packageUrl,
						checkUrl : checkUrl,
						type : type,
						state : state,
						taskDetail : taskDetail,
						explain : explain,
						purchaseName : purchaseName,
						checkExplain : checkExplain,
						packageExplain : packageExplain,
						param : JSON.stringify(param),
						inspectionPath : $('#inspectionPath').val(),
						inspectionForm : $('#inspectionForm').val(),
						taskId : taskId,
						conclusion : $('#conclusion').val(),
						keySizeExceed : keySizeExceed,
						noCheck : noCheck,
						factoryList: factoryListStr, 
						inspectionCreateDate:inspectionCreateDate,
						planList:JSON.stringify(planList)  
					},
					success : function(json) {
// 						var json = eval("(" + data + ")");
						if (json.ok) {
							window.location.href = '${ctx}/project/showDetails?projectNo='
									+ projectNo
									+ '&roleNo='
									+ roleNo
									+ '&userId='
									+ userId
									+ '&userName='
									+ userName;
						} else {
							$("#subHtml")
									.html(
											'<font class="tips_false">录入失败'
													+ (json.message ? json.message
															: "") + '</font>');
							$(".btn").css("background-color", "#027CFF")
									.removeAttr('disabled');
						}
					},
					error : function() {
						$("#subHtml").html(
								'<font class="tips_false">录入失败'
										+ (json.message ? json.message : "")
										+ '</font>');
						$(".btn").css("background-color", "#027CFF")
								.removeAttr('disabled');
					}

				})
		return false

	}

	function fileChange(that) {
		var filepath = $(that).val();
		var projectNo = $('#projectNo').val();
		if (filepath == "") {
			return;
		}
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(ext.toUpperCase()) == -1) {
			alert("只允许上传jpg、png、bmp、jpeg格式的图片");
			return false;
		}

		var tl = that.files.length;
		//以图片宽度为800进行压缩 
		var fileName;
		var file;
		for (var i = 0; i < tl; i++) {
			var fileName = that.files[i].name;
			var fileForm = that.files[i];
			var width = fileForm.width;
			var height = fileForm.height;
			synPic(that, fileForm, projectNo, fileName);
		}
	}

	function synPic(obj, fileForm, projectNo, fileName) {

		var width = 800;
		var formId = $(obj).parents('form').attr('id');
		if (formId == 'check_form') {
			width = 1024;
		}

		lrz(fileForm, {
			width : width
		})
				.then(
						function(rst) {

							//压缩后异步上传 
							$
									.ajax({
										url : "/upload/fileUploadPicture",
										type : "POST",
										data : {
											projectNo : projectNo,
											fileName : fileName,
											imgdata : rst.base64
										//压缩后的base值 
										},
										dataType : "json",
										cache : false,
										async : true,
										success : function(json) {
																						
												if (json.ok) {
													var data = json.data;
													
													if(formId == 'file_upload_id'){
														$('#inspectionPic').append('<li class="li_img"><img src="'+data+'"></li>');													
													}else{
														addhtml = '<li><div class="img_in">'
															+ '<a href="'+data+'" target="_blank">'
															+ '<img src="'+data+'" alt=""></a>'
															+ '</div><span class="glyphicon glyphicon-remove"></span><span class="glyphicon glyphicon-repeat rotate" onclick="rotate(\''
															+ data
															+ '\',this,0)"></span>'
															+ '</li>';
														$(obj).parents('form').find(
																'.clearfix').prepend(
																addhtml);
														/*删除当前图片*/
														$('.quality .wrap2 li .glyphicon-remove')
																.on(
																		'click',
																		function() {
																			$(this)
																					.parent()
																					.remove();
																		})
		
													}
													
												} else {
													alert(json.message);///data.message为上传失败原因 
												}

										},
										error : function() {
											alert("上传失败");
										}
									});
						});
	}

	//旋转当前图片
	function rotate(filePath, obj, num) {
		var deg = num + 90;
		$.ajax({
			url : "/upload/updateQualityImg",
			type : "POST",
			data : {
				filePath : filePath,
				degree : 90
			},
			dataType : "json",
			cache : false,
			async : true,
			success : function(json) {
				$(obj).attr('onclick',
						'rotate(\'' + filePath + '\',this,' + deg + ')');
				$(obj).siblings().find('img').css({
					"transform" : "rotate(" + deg + "deg)"
				});
			}
		})
	}

	//改变检验阶段
	function change_stage(obj) {
		if ($(obj).val() == 0) {
			$('.project-stage').text('样品生产阶段');
			$('#stage_div').show();
		} else if ($(obj).val() == 3) {
			$('.project-stage').text('大货生产阶段');
			$('#stage_div').show();
		} else {
			$('#stage_div').hide();
		}
	}

	var names = [];
	$(function() {
		var factoryNameList = '${factoryNameList}';
		factoryNameList = factoryNameList.substring(1,
				factoryNameList.length - 1);
		names = factoryNameList.split(",");

		$('input[name="place"]').change(function() {
			if ($(this).val() == '工厂') {
				if ($(this).is(":checked")) {
					$('.position_relative').show();
					$('.add_detail .facotry_td ul').hide();
				} else {
					$('.position_relative').hide();
				}
			}
		})
	})
	

	
	$('input[name="factory"]').bind("focus keyup",function() {

		var key = $(this).val();
		if(!key){
			key = "厂";
		}
		$(this).next().empty();
		for (var i = 0; i < names.length; i++) {
			if (key) {
				if (names[i].indexOf(key) != -1) {
					$(this).next().append('<li>' + names[i] + '</li>');
				}
			}
		}
		$(this).next('ul').show();
		$(this).next().find('li').show();

		//选中关键词  		 
		$('.facotry_td li').click(function(e) {
			var li_val = $(this).text();

			$('.facotry_td ul li,.facotry_td ul').hide();
			$(this).parent().parent().find('input').val(li_val).css({
				'padding-left' : '0'
			});

			$(document).on("click", function() {
				$('.facotry_td li,.facotry_td ul').hide();
			});
			e.stopPropagation();
		});
	})
	// 选中显示日期，否则不显示选日期
	$('.add_detail .facotry_list .s2').hide();
	$('.facotry_list .s1 input[type="checkbox"]').each(function(){
		if($(this).is(":checked")){
			$(this).parent().siblings('.s2').show();
		}
	})
	
	
	
	$('.facotry_list .s1 input[type="checkbox"]').click(function(){
		var check = $(this).prop('checked');
		if(check == true){
			$(this).parent().siblings('.s2').show();
			$(this).parent().parent().siblings().find('input[name="select"]').attr('checked',false);
			$(this).parent().parent().siblings().find('.s2').hide();
		}else if(check == false){
			$(this).parent().siblings('.s2').hide();
		}		
	})
	
	
	
	
	//显示对应工厂完结勾选
	$('input[name="state"]').change(function(){
		if($(this).val() == 0){
			$('.noIssue').show();
			$('.deal').hide();
		}else if($(this).val() == 1){
			$('.deal').show();
			$('.noIssue').hide();
		}else{
			$('.deal').hide();
			$('.noIssue').hide();
		}
	})
	
	
	//选择计划是否完成，批量
	function selectWorkOnly(obj,index){
		if($(obj).is(":checked")){
			$(obj).parent().siblings().find('input[type="checkbox"]').removeAttr('checked');
		}else{
			if($(obj).val() == 0){
				$('#check_body').find('.check_'+index+' input[type="checkbox"][value="0"]').removeAttr('checked');
			}
			if($(obj).val() == 1){
				$('#check_body').find('.check_'+index+' input[type="checkbox"][value="1"]').removeAttr('checked');
			}			
		}
		checkboxSelect(index);
	}
	
	
	//选择计划是否完成，批量
	function selectWork(obj,index){
		if($(obj).is(":checked")){
			$(obj).parent().siblings().find('input[type="checkbox"]').removeAttr('checked');
			if($(obj).val()==0){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').prop('checked','true'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
			}
			if($(obj).val()==1){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').prop('checked','true'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked'); 
			}
		}else{
			if($(obj).val()==0){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked');
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
			}
			if($(obj).val()==1){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked'); 
			}
		}		
	}
	
	
	function checkboxSelect(index){
		//用于判断是否全选
		var c_work_flag = true;
		var c_no_work_flag = true;
		$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').each(function(){
			if(!$(this).is(":checked")){
				c_work_flag = false;
			}
		})
		$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').each(function(){
			if(!$(this).is(":checked")){
				c_no_work_flag = false;
			}
		})
		//判断是否全选
		if(c_work_flag){
			$('#check_body').find('.check_'+index+' input[type="checkbox"][value="1"]').prop('checked','true'); 
		}
		if(c_no_work_flag){
			$('#check_body').find('.check_'+index+' input[type="checkbox"][value="0"]').prop('checked','true'); 
		}
	}
	
	
	//展开
	function openInspection(obj,index){
		if($(obj).text() == '收起'){
			$(obj).text('请展开选择');
			$(obj).parents('tbody').find('.check_index_'+index+'').hide();		
		}else{
			$(obj).text('收起');
			$(obj).parents('tbody').find('.check_index_'+index+'').show();		
		}		
	}
	
	
	
	$(function(){
		var tl = $('#check_body').find('.check').length;
		for(var i=0;i<tl;i++){
			checkboxSelect(i);
		}
	})
	
	//选择图号事件
	function select_img(obj){			
	    $('.check').each(function(){
   		     if($(obj).val() == $(this).find('span:first').text()){
   		    	 if($(obj).is(":checked")){
   		    		 $(this).show();
   		    	 }else{
   		    		 $(this).hide();
   		    	 }
   		     }
   	    })	    
	}
	
	//打开上传质检证明框
	function openInspectionImg(id,index){
		$('#inspectionPlanId').val(id);
		$('#inspectionPlan_li_index').val(index);
		$('.add_tc').show();
	}
	
	
	//关闭录入质检证明
	function cancelInspection(obj){
		$(obj).parents('.add_tc').find("ul li:not(.i-upload)").remove();
		$('#content').val('');
		$(obj).parents('.add_tc').hide();		
	}
	
	//保存检验说明
	function addInspection(obj){
		var id = $('#inspectionPlanId').val();
		var index = $('#inspectionPlan_li_index').val();
		if(!id){
			alert("未获取到id");
			return false;
		}
		//获取图片
		var images='';
		var tl = $('.add_tc ul li:not(.i-upload)').length;
		if(tl>3){
			alert("最多上传3张");
			return false;
		}
		$('.add_tc ul li:not(.i-upload)').each(function(){
			var img = $(this).find('img').attr('src');
			if(img){
				images+=img+",";
			}
		})
		//查询是否选中包装图
		if($('.p_check').is(":checked")){
			$('#package_form').find('a').each(function() {
				if (packageUrl) {
					images+= $(this).attr('href')+",";
				} 
			})
		}
		//查询是否选中材料图
		if($('.m_check').is(":checked")){
			$('#material_form').find('a').each(function() {
				if (packageUrl) {
					images+= $(this).attr('href')+",";
				} 
			})
		}		
		
		if(images){
			images = images.substring(0,images.length - 1);
		}
		//获取说明文字
		var content = $('#content').val();
        if(!images && !content){
        	alert("请提供证明");
        	return false;
        }		
		
		$.ajax({
			url : "/quality/updateInspectionPlan",
			type : "POST",
			data : {
				id:id,
				images : images,
				content : content
			},
			dataType : "json",
			success : function(json) {
				if(json.ok){
					cancelInspection(obj);
					$('.check_s').eq(index).find('button').css('color','green');
				}else{
					alert(json.message);
				}
			}
		})	
	}
	
	
	
</script>



















