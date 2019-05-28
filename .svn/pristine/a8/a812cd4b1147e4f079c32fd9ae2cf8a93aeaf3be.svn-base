<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/test.css">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/easydialog.css" />
<script src="${ctx}/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/easydialog.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<style type="text/css">
.updown-list {
	display: none;
	width: 432px;
	height: 190px;
	overflow: hidden;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}

.updown-list>div {
	font-size: 20px;
	width: 432px;
	height: 40px;
	line-height: 40px;
	background-color: #FFF;
	margin-top: 150px;
	text-align: right;
	padding: 3px 12px;
	border-top: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	cursor: pointer;
	position: relative;
	z-index: 30;
}

.box-executive ul {
	width: 100%;
	height: 150px;
	position: absolute;
	top: 34px;
	left: 0;
	z-index: 10;
	padding: 0;
	background-color: #FFF;
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
}

.box-executive .list>li, .box-executive .list>li ol li {
	/* width: 300px;
		    height: 30px;
    		line-height: 30px; */
	font-size: 13px;
	color: #666;
	padding: 1px 12px;
	cursor: pointer;
	/* position: relative; */
}

.box-executive .list>li:before {
	content: "";
	display: inline-block;
	width: 10px;
	height: 15px;
	background: url(../img/arrow-right.png) center no-repeat;
	background-size: cover;
	position: relative;
	left: 186px;
	top: 3px;
}

.box-executive .list>li:hover, .box-executive .list>li ol li:hover {
	color: #FFF;
	background-color: #027CFF;
}

.list>li.active ol {
	display: block;
}

.list>li:hover ol {
	display: block;
}

.list>li ol {
	display: none;
	width: 216px;
	height: 150px;
	position: absolute;
	top: 0;
	left: 216px;
	z-index: 20;
	padding: 0;
	background-color: #FFF;
	overflow-y: scroll;
	overflow-x: hidden;
}

#easyDialogBox {
	margin-top: -392px;
}
.big_pic{
	position:fixed;
	width:100%;
	height:100%;
	z-index:10;
	text-align:center;
	display:none;
}
.big_pic .close{	
	position:absolute;
	right:10px;
	top:10px;
	z-index:1000;
	opacity:1;
}
.big_pic .big_bg{
	position:fixed;
	width:100%;
	height:100%;
	background-color:rgba(0,0,0,.5);
	z-index:10;
	text-align:center;
	padding-top:50px;
	
}
.big_pic .imgs{
	width:960px;
	height:720px;
	margin:0 auto;
	margin-top:50px;
	position:relative;
	z-index:100;
	text-align:center;
	padding-top:40px;

}
.big_pic img{
	max-width:960px;
	max-height:720px;
	position:relative;
	z-index:100;
	
}
.tab-link {
    font-size: 18px;
    color: #666666;
    font-weight: bold;
    display: block;
    min-height: unset;
    line-height: unset;
    overflow: unset;
    text-overflow: unset;
    text-align: center;
}
.grid{overflow:hidden;}
.grid tr td{
	font-size: 18px;
    color: #666666;
    border-bottom: 1px #dddddd solid;
    border-left: 1px #dddddd solid;
    padding:8px;
    overflow: unset;
    line-height: unset;
    vertical-align: middle;
    text-overflow: unset;
}
.grid tr td:first-child{
	text-align:center;
}
.grid tr th{
	padding: 8px;
	text-align: center;
}
.form-horizontal .control-label{
	font-size:16px;
}
.page-box{
	font-size:16px;
}
.page-box a{
	font-size:16px;
}
.meeting-list-table .color-blocks-btn{
	font-size:16px;
}
</style>
<title>项目列表</title>
</head>
<body class="list-bgcolor">
	<div class="big_pic">		
		<div class="big_bg"></div>
		<div class="imgs"><img src="../img/lj1.jpg" id="img_big"/><div class="close"><img src="../img/close.png" alt=""></div></div>
	</div>
	<input type="hidden" name="pageNumber" id="pageNumber" value="${pageNumber}">
	<input type="hidden" name="pcType" id="pcType" value="${pcType}">
	<div class="main-container page1">
		<div>
			<h3></h3>
		</div>
		<form id="form" class="roleform form-horizontal" role="form"
			onsubmit="return false;">
			<div class="form-group" style="height: 40px;">
				<label for="firstname" class="col-sm-2 control-label"
					style="margin-top: 10px; margin-left: -90px;"><!-- <i
					class="ffhh">*</i> -->人员筛选</label>
				<div class="col-sm-3 showHtml">
					<select id="purchase_name" name="purchase_name" class="form-control" onchange="searchProjectData(1)">
						<option value="">全部采购</option>
					</select><span></span>
				</div>

				<div class="col-sm-3 showHtml">
					<select id="documentary_name" name="documentary_name" class="form-control" onchange="searchProjectData(1)">
							<option value="">全部跟单</option>
					</select><span></span>
				</div>

				<div class="col-sm-3 showHtml">
					<select id="quality_name" name="quality_name" class="form-control" onchange="searchProjectData(1)">
						<option value="">全部质检</option>
					</select><span></span>
				</div>
			</div>
			<div class="form-group" style="height: 40px;">
				<div class="col-sm-3">
					<label>项目状态</label>
					<select id="projectType" name="projectType" class="form-control"
						style="width: 220px;" onchange="searchProjectData(1)">
						    <!-- <option value="">项目状态</option> -->
							<option value='0'>新立项项目</option>
							<option value='1'>正常进行项目</option>
							<option value='2'>完成项目</option>
							<option value='3'>延期项目</option>
							<option value='4'>暂停项目</option>
							<option value='5'>取消项目</option>
					</select><span></span>
				</div>
				<div class="col-sm-3">
					<label>项目阶段</label>
					<select id="projectStage" name="projectStage" class="form-control"
						style="width: 220px;" onchange="searchProjectData(1)">
						    <!-- <option  value="">项目阶段</option>> -->
							<option value='0'>样品</option>
							<option value='1'>模具</option>
							<option value='2'>大货</option>
					</select><span></span>
				</div>

			</div>

			<div class="form-group" style="height: 40px;">
				<label for="firstname" class="col-sm-2 control-label"
					style="margin-top: 10px; margin-left: -75px;"><!-- <i
					class="ffhh">*</i> -->关键词搜索</label>
				<div class="col-sm-3">
					<input class="form-control" style="width: 220px;" type="text" id="inputKey" name="inputKey"
						placeholder="项目号/项目名/姓名">
				</div>

			</div>

			
		</form>


		<div class="main-table meeting-list-table">
			<span> <!--<input type="text" name="searchProjectNo"
				id="searchProjectNo" placeholder="项目号" />  -->
			</span> <input type="hidden" value="${userName}" name="userName"
				id="userName"> <input type="hidden" value="${userId}"
				name="userId" id="userId"> <input type="hidden"
				value="${roleNo}" name="roleNo" id="roleNo">
				
				<input id="searchBut" type="submit" value="搜索" class="color-blocks-btn"  onclick="searchProjectData(1)"/> 
				<input type="submit" value="清空所有关键词和条件" class="color-blocks-btn" onclick="cleanSelect()"  />
				<input type="submit" value="返回主页" class="color-blocks-btn" onClick="goBack()"/>  
				<input name="" type="submit" value="退出系统" class="color-blocks-btn"  onclick="exitlogin()"/>
			<div class="table-wrap" style="margin-top: 10px;">
				<div class="table-head">
					<div class="table-head-wrap">
						<table class="grid">
							<colgroup>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th style="width: 11%"><span class="tab-link">序号</span></th>
									<th style="width: 25%"><span class="tab-link">等级</span></th>
									<th style="width: 30%"><span class="tab-link">项目号</span></th>
									<th style="width: 25%"><span class="tab-link">项目金额</span></th>
									<th style="width: 30%"><span class="tab-link">合同签订日期</span></th>
									<th style="width: 35%"><span class="tab-link">大货/样品交期</span></th>
									<th style="width: 35%"><span class="tab-link">产品图</span></th>
									<th style="width: 25%"><span class="tab-link">项目状态</span></th>
									<!-- <th style="width: 25%"><span class="tab-link">技术指导</span></th> -->
									<th style="width: 25%"><span class="tab-link">工厂</span></th>
									
									<th style="width: 28%"><span class="tab-link">质检</span></th>
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
						</colgroup>
						<tbody id="tbhtml">	
								<tr>	
								</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="page-box">
				总数:${totalCount}每页:5条 当前页/总页:<span style='color: red' id="pageNumberSpan"></span>/<span id="countPageSpan"
					style='color: red'></span>&nbsp; <a href="#" 
					class='emanagergetpagea first-padding'
					onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a> <a
					href="#" class='emanagergetpagea first-padding'
					onclick="searchProjectData(2)" title='上一页(第1页)' class='a02'>上页</a>
				<!-- 		    <a href="#" class='emanagergetpagea' title='1' class='a02'>1</a>
		    <a href="#" class='emanagergetpagea' title='2' class='a02'>2</a>
		    <a href="#" class='emanagergetpagea' title='3' class='a02'>3</a>
		    <a href="#" class='emanagergetpagea' title='4' class='a02'>4</a> -->
				<a href="#" class='emanagergetpagea' onclick="searchProjectData(3)"
					title='下一页(第3页)' class='a02'>下页</a> <a href="#"
					class='emanagergetpagea' onclick="searchProjectData(4)"
					title='最后一页' class='a02'>尾页</a>
			</div>
			<input type="hidden" id="pageNumber" name="pageNumber"
				value=""> <input type="hidden" id="countPage"
				name="countPage" value="">
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
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
	$(function() {
		 var roleNo = $('#roleNo').val()
		if(roleNo==6){
			$('#purchase_name').parent('.showHtml').hide()
		}
		if(roleNo==5){
			$('#documentary_name').parent('.showHtml').hide()
		}
		if(roleNo==9){
			$('#quality_name').parent('.showHtml').hide();
		} 
			
		ajaxSelectOption()
		searchProjectData(1)
		
		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined) {
			$("#goBackHtml").hide();
		}
		
		
		

	})
	
	
	
	
	function searchProjectData(obj){
		
		var pageNumber = $("#pageNumber").val();
		var countPage = $("#countPage").val();
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var type = obj;
		var pcType=$("#pcType").val();
		if(pcType!=null && pcType!=''&&pcType!=undefined ){
			pageNumber=pageNumber;
		}else{
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
		}
		var inputKey = $("#inputKey").val();//关键字查询
		var projectType = $("#projectType").val();
		var projectStage = $('#projectStage').val()
		var purchase_name = $('#purchase_name').val()
		var documentary_name = $('#documentary_name').val()
		var quality_name = $('#quality_name').val()
		$('#tbhtml').html('')
		
		$('#pageNumber').val("")
		$('#pageNumberSpan').text("")
	    $('#countPage').val("")
	    $('#countPageSpan').text("")
		$("#pcType").val("");
		
			$.ajax({
						type : 'post',
						url : '${ctx}/project/showList',
						data : {
							pageNumber : pageNumber,
							roleNo : roleNo,
							userId : userId,
							userName : userName,
							inputKey : inputKey,
							projectType:projectType,
							projectStage:projectStage,
							purchase_name : purchase_name,
							documentary_name :documentary_name,
							quality_name: quality_name,
							pageSize:'5'
							
							
						},
						success : function(data) {
							var json = eval("(" + data + ")");
							var projectDataList = json.data.list;//项目数据
							var pageNumber = json.data.pageNumber
							var totalCount = json.data.totalCount
							var projectType=json.data.projectType
							var totalCountFinish=json.data.totalCountFinish
							$('#pageNumber').val(pageNumber)
							$('#pageNumberSpan').text(pageNumber)
							if(totalCount){
								if(projectType==""){
									var countPage = Math.ceil((totalCount-totalCountFinish)/5)
									$('#countPage').val(countPage);
								    $('#countPageSpan').text(countPage)
								}else{
									var countPage = Math.ceil(totalCount/5)
									$('#countPage').val(countPage);
								    $('#countPageSpan').text(countPage);
								}
								
							}
							
							if(projectDataList&&projectDataList.length>0){
								
								var tabhtml=''
								for(var i=0;i<projectDataList.length;i++){
									var project =projectDataList[i]
									var actualStartDate="";
									if(project.actualStartDate){
										actualStartDate += '' +(new Date(project.actualStartDate).pattern("yyyy-MM-dd"))+'';
									}
									tabhtml+='<tr calss="index-list">'
									       +'<td style="width: 11%" class="first-td">'+(i+1)+'<input type="hidden" value="'+project.flag+'" class="project-flag"></td>'
									       +'<td style="width: 25%">'+(project.plantAnalysisView?project.plantAnalysisView:"")+'</td>'
									       +'<td style="width: 30%"><a href="${ctx}/project/showDetails?projectNo='
												+ project.projectNo
												+ '&roleNo='
												+ roleNo
												+ '&userId='
												+ userId
												+ '&userName='
												+ userName
												+ '&pageNumber='
												+pageNumber
												+ '">'+project.projectNo+"("+project.projectName+")"+'</a></td>'
									       +'<td style="width: 25%">'+(project.projectAmount?project.projectAmount:"")+'</td>'
									       +'<td style="width: 30%">'+actualStartDate+'</td>'
									       
									       var allTime =''
									       
									       if(project.deliveryDate){
									    	   allTime = '大货：' +(new Date(project.deliveryDate).pattern("yyyy-MM-dd"))+'<br/>'
									       }
								           if(project.sampleScheduledDate){
								    	       allTime += '样品：' +(new Date(project.sampleScheduledDate).pattern("yyyy-MM-dd"))+'<br/>'
								           }
									    tabhtml+='<td style="width: 35%">'+allTime+'</td>'
								    
								    var zhijian='';
								    if(project.zhijian1){
								    	zhijian = project.zhijian1
								    	 if(project.zhijian2){
								    		 zhijian += '<br/>'+project.zhijian2
								    	 }
								     }else{ 
								    	 if(project.zhijian2){
								    		 zhijian = project.zhijian2
								    	 }
								     }
								      pichtml=''
							          if(project.productImg){
							        	if(project.productImg){
								        	 pichtml= '<img style="max-height: 100px;max-width: 150px;" src="/product_img/'+project.productImg+'"></img>'
								        }
							          }
									  tabhtml+='<td style="width: 35%">'+pichtml+'</td>'
									  tabhtml+='<td style="width: 25%">'+(project.weekInfo?project.weekInfo:"")+'</td>'
									       /* +'<td style="width: 25%"></td>' */
									       +'<td style="width: 25%">'+(project.companyName?project.companyName:"")+'</td>'
									      
									       +'<td style="width: 28%">'+zhijian+'</td>'
									tabhtml+='</tr>'
								}
								//项目列表延期与快到期标记
							}		
							$('#tbhtml').html(tabhtml);	
								$('#tbhtml tr').each(function() {									
									var project_flag = $(this).find('td:nth-child(1) input').val();
									if (project_flag == 2) {
										$(this).css({"background-color":"yellow"});
									}
								}) 
								/* 点击图片放大到整屏，并点击关闭按钮关闭 */
								$('.grid tr td img').on('click',function(){
									$('.big_pic').show();
									var $src = $(this).attr('src');
									$('.big_pic #img_big').attr('src',$src);
									
								})
								$('.big_pic .big_bg , .big_pic .close img').on('click',function(){
									$('.big_pic').hide();
								})
						}
	})
	
	}
	
	function exitlogin() {
			$.cookie('name', '', {
				path : '/'
			});
			$.cookie('pass', '', {
				path : '/'
			});
			window.location.href = "${ctx}/index.jsp";
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
	
	
	
	
	
	

	function getDetail(id) {
		if (!id) {
			return false;
		}

		$.ajax({
			url : "${ctx}/trigger/selectRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			data : {
				'triggerId' : id
			},
			datatype : "json",
			success : function(result) {
				var json = eval("(" + result + ")");
				if (json.ok) {
					addTrigger()
					$('#triggerId').val(id)
					var triggerTask = json.data
					$('#roleType').val(triggerTask.roleType)
					$('#taskTitle').val(triggerTask.taskTitle)
				} else {

				}

			}

		})

	}

	function deleteDetail(id) {
		if (!id) {
			return false;
		}

		if (window.confirm('你确定要删除吗？')) {

		} else {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$.ajax({
			url : "${ctx}/trigger/deleteRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			data : {
				'triggerId' : id
			},
			datatype : "json",
			success : function(result) {
				var json = eval("(" + result + ")");

				if (json.ok) {

					window.location.href = "${ctx}/trigger/queryList?userName="
							+ userName + "&roleNo=" + roleNo + "&userId="
							+ userId;

				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 12000
					});
				}

			}

		})
	}

	function saveDetail() {

		$('.page2').find('.ff4').removeClass('ff4').text('')

		if (!$('#projectNo').val()) {
			$('#projectNo').next().addClass('ff4').text('请输入项目号')
		}

		$('.page2').find('select[name=roleType]').each(function() {
			if ($(this).val() == 0) {
				$(this).next().addClass('ff4').text('请选择角色类型')
			}

		})

		$('.page2').find('input[name=taskTitle]').each(function() {
			if ($(this).is(':visible')) {
				if (!$(this).val()) {
					$(this).next().addClass('ff4').text('请输入任务标题')
				}

			}

		})

		if ($('.ff4').length > 0) {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$("#submitButton").attr("disabled", true).css("background-color",
				"#999");

		$("#triggerform")
				.ajaxSubmit(
						{
							type : "post",
							url : "${ctx}/trigger/addRecord",
							dataType : "text",
							success : function(result) {
								var json = eval("(" + result + ")");
								$("#submitButton").css("background-color",
										"#027CFF").removeAttr('disabled');
								if (json.ok) {

									window.location.href = "${ctx}/trigger/queryList?userName="
											+ userName
											+ "&roleNo="
											+ roleNo
											+ "&userId=" + userId;
								} else {

									easyDialog.open({
										container : {
											header : 'Prompt message',
											content : json.message
										},
										overlay : false,
										autoClose : 3000
									});

								}

							}
						})

	}

	
	
	function deleteRole(obj) {

		$(obj).parents('.form-group').remove()

	}
	/*
	 * 
	function addRole() {

		$('#triggerform').append($('#addRole .form-group').clone())
		addClick()

	}
	
	 function addMeetingRecord() {
	 var roleNo = $("#roleNo").val();
	 var userId = $("#userId").val();
	 var userName = $("#userName").val();
	 window.location.href = "${ctx}/meetingRecord/inputMeetingRecord?roleNo="
	 + roleNo + "&userId=" + userId + "&userName=" + userName;
	 };
	
	 */

	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/jsp/project_list.jsp?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}


	$(".box-executive input,.box-executive em").click(
			function(event) {
				$(this).parents(".project-task-list").find(".updown-list")
						.slideUp(30).end().end().siblings(".updown-list")
						.slideDown();
				$(this).parent(".box-executive").find("em").addClass(
						"arrow-icon");
				return false;
			});

	$(".list ol li").click(function(event) {
		var game1 = $(this).text()
		console.log($(this).parents(".updown-list").siblings("input").length);
		$(this).parents(".updown-list").siblings("input").val(game1);
		foc();
	});

	function addClick() {
		$(".box-executive input,.box-executive em").click(
				function(event) {
					$(this).parents(".project-task-list").find(".updown-list")
							.slideUp(30).end().end().siblings(".updown-list")
							.slideDown();
					$(this).parent(".box-executive").find("em").addClass(
							"arrow-icon");
					return false;
				});

		$(".list ol li").click(
				function(event) {
					var game1 = $(this).text()
					console.log($(this).parents(".updown-list").siblings(
							"input").length);
					$(this).parents(".updown-list").siblings("input")
							.val(game1);
					foc();
				});

	}

	function foc() {
		$(".close-ck").parents(".updown-list").slideUp();
		$(".box-executive em").removeClass("arrow-icon");
	}

	
	
	
	/*
	function showWorker(obj) {
		var value = $(obj).val()
		if (value && value != 0) {
			$(obj).parent().next().show()
		} else {
			$(obj).parent().next().hide()
		}
		$(obj).parent().next().find('input[name=staffName]').val('')

	}
	 */
</script>
<script>
	$('.big_pic').hide();
	/* 点击图片放大到整屏，并点击关闭按钮关闭 */
	$('.grid tr td img').on('click',function(){
		alert(1);
		$('.big_pic').show();
		var $src = $(this).attr('str');
		$('.big_pic img').attr('str',$src);
		
	})
	$('.big_pic .big_bg , .big_pic .close img').on('click',function(){
		$('.big_pic').hide();
	})
	
</script>
</html>