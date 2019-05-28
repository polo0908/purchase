<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String roleNo=request.getParameter("roleNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
<head>
  <title>任务录入</title>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="keywords" content="HTML,CSS,XML,JavaScript">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
  <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
  <style type="text/css">
		body{
			font:14px/28px "微软雅黑";
			background-color: #FAFAFA;
		}
		body>h2{
			text-align: center;
			font-size: 24px;
			color: #222;
    		font-weight: 100;
		}
		.contact *:focus{outline :none;}
		.contact{
			width: 1200px;
		    /* height: 800px; */
		    background: #fff;
		    margin: 10px auto 40px;
		    padding: 10px;
		    -webkit-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -moz-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -ms-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -o-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		}
		.contact ul {
			/* width: 650px;
			margin: 0 auto; */
		}
		.contact ul li{
			/* border-bottom: 1px solid #dfdfdf; */
			list-style: none;
			padding: 12px;
		}
		.contact ul li label {
			width: 135px;
		    display: inline-block;
		    float: left;
		    text-align: right;
		    margin-right: 20px;
		    color: #555555;
		    line-height: 34px;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 402px;
    		height: 26px;
			border :1px solid #ddd;
			padding: 3px 8px;
		}
		.contact ul li input:focus{
			/* border-color: #c00; */
			
		}
		.contact ul li input[type=text]{
			transition: padding .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		.contact ul li input[type=password]{
			transition: padding  .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color: #C2C2C2; opacity:1; 
		}
		
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color: #C2C2C2;opacity:1;
		}
		
		input:-ms-input-placeholder,textarea:-ms-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		
		input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		input::-o-input-placeholder,textarea::-o-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		.task-receiver{
			width: 420px;
		    height: 30px;
		    border: 1px solid #ddd;
		    padding: 3px 8px;
		}
		.task-tell{
			width: 622px;
    		height: 107px;
		    border: 1px solid #ddd;
		    padding: 3px 8px;
		    resize: none;
		}
		.date-time{
			background: url(../img/data-calendar.png) right center no-repeat;
    		background-position-x: 98%;
    	}
		.btn{
			position: relative;
		    width: 100%;
		    display: inline-block;
		    text-align: center;
		    margin-bottom: 40px;
		}
		.btn input[type=submit] {
		    padding: 7px 46px;
		    background-color: #4B67E7;
		    border: none;
		    font-size: 14px;
		    color: #fff;
		}
		.btn input[type=submit]:hover{background-color: #4362C5;}
		.tips{
			color: rgba(0, 0, 0, 0.5);
			padding-left: 10px;
		}
		.tips_true,.tips_false{
			padding-left: 10px;
		}
		.tips_true{
			color: green;
		}
		.tips_false{
			color: red;
		}
		#finishTime{cursor: pointer;}
		
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
		
  </style>
</head>
<body>
    <h2 style="text-align:center;">任务录入</h2>
    <input type="hidden" name="userName" id="userName" value="<%=userName%>">
    <input type="hidden" name="roleNo" id="roleNo" value="<%=roleNo%>">
    <input type="hidden" name="userId" id="userId" value="<%=userId%>">
	<div class="contact">
		<div>
			<ul>
				<li>
					<label>任务发起人</label>
					<input type="text" name="initiator" id="initiator" value="<%= userName%>" readonly="readonly"/>
					<input name="" style="height:28px;line-height:28px;background-color: #027cff;border: none;color: #fff;padding: 1px 16px; margin-left: 6px;border-radius: 2px;"type="submit" value="返回主页" onClick="goBack()" id="goBackHtml"/>
				</li>
				<li>
				<label>任务接受人</label>
			
	
	           
				<div class="col-sm-3 box-executive" >
					<input type="text" class="form-control" name="accepter" id="accepter"
						placeholder="请选择公司职员"><span id="accepterHtml"></span>

					<div class="updown-list" style="margin-left: 150px;">
						<ul class="list" style="top: 200px; margin-left: 680px;">
							<li class="active"><span>技术部</span>
								<ol>
									<li>LiuZhongYi</li>
									<li>zhangqun</li>
									<li>OwenCui</li>
									<li>YanRongchao</li>
									<li>wangweiping</li>
									<li>DeanZhang</li>
								</ol></li>
							<li><span>销售部</span>
								<ol>
									<li>jerrylong</li>		
									<li>amyzhao</li>
									<li>tina</li>
									<li>paul</li>
									<li>FionaYan</li>
									<li>Susiehuang</li>
									<li>minniewu</li>
									<li>SherryZhou</li>
									<li>LynnYuan</li>
									<li>elainesheng</li>
									<li>ivywu</li>
									<li>kathypan</li>
									<li>chloema</li>
									<li>annazhu</li>
									<li>KateGong</li>
									<li>ShirleyYu</li>
									<li>NataliaLi</li>
									<li>jennyguo</li>
									<li>Kristinemei</li>
									<li>pandage</li>
									<li>chengmingkun</li>
									<li>MinnXu</li>
									<li>AndsXue</li>
								</ol></li>
							<li><span>采购部</span>
								<ol>
									<li>Jiangwenlong</li>
									<li>Bensonzhang</li>
									<li>ninazhao</li>
									<li>zhaoshuhao</li>
									<li>zhaoqiang</li>
									<li>xuping</li>
									<li>ThomasChen</li>
									<li>xuwei</li>
									<li>RogerQiu</li>
									<li>SISI</li>
									<li>ArnoZhou</li>
								</ol></li>
							<li><span>质检部</span>
								<ol>
									<li>yanggong</li>
									<li>wangjingjun</li>
									<li>maxiaolei</li>
									<li>litie</li>
									<li>zoumin</li>
									<li>wangzhuo</li>
									<li>zhangyouqing</li>
									<li>liuzikai</li>
									<li>zuoliang</li>
									<li>zhaochun</li>
								</ol></li>
						</ul>
						<div>
							<span class="close-ck" onclick="foc()">X</span>
						</div>
					</div>


				</div>
					
					
					
					<!--<select class="task-receiver" onchange="selectAccepter(this)">
					   <option value="-1">请选择</option>
					   <option value="zhangqun">技术部 – 绘图张群</option>
					   <option value="OwenCui">技术部 – 模具OwenCui</option>
					   <option value="wangweiping">技术部 – 总监王工</option>
					   <option value="AlanWang">技术部 – 工程师Alan</option>
					   <option value="LiuZhongYi">技术部 –刘工</option>
					   <option value="YanRongchao">技术部 – 严荣超</option>
					</select>  -->
					
				
				</li>
				<li>
					<label>相关项目号</label>
					<span class="add-on">SHS</span>
					<input type="text" name="projectNo" id="projectNo" placeholder="请输入项目号" style="width:370px;"/><span class="tips" id="projectNoHtml"></span>
				</li>
				<li>
					<label>任务简述</label>
					<textarea class="task-tell" name="description" id="description" placeholder="请输入任务简述"/></textarea><span class="tips" id="descriptionHtml"></span>
				</li>
				<li>
					<label>要求任务完成时间</label>
					<input type="text" name="finishTime" id="finishTime" value="" class="input-text form-control date-time" placeholder="请输入完成时间"/><span class="tips" id="finishTimeHtml"></span>
				</li>
				<li>
					<label>如果紧急,请给出理由</label>
					<textarea class="task-tell" name="urgentReason" id="urgentReason" placeholder="请输入紧急理由"/></textarea><span class="tips" id="urgentReasonHtml"></span>
				</li>
			</ul>
			
		</div>
	</div>
	<b class="btn"><input type="submit" class="subm-but" onclick="addProjectTask()"value="提交"/><span class="tips" id="subHtml"></span>
</body>

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
			startYear: currYear, //开始年份
			endYear: currYear + 50 //结束年份
		};
		$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
	})
	Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
<script type="text/javascript">
function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}

$(function(){
	var d = new Date();
	d.setDate(d.getDate() + 2);
	var day = d.getDay(); 

	if(day==6||day==0){
	  d.setDate(d.getDate()+2);

	}
	$("#finishTime").val(new Date(d.toString()).Format("yyyy-MM-dd"));
});

function selectAccepter(obj){
	var accepter=$(obj).find("option:selected").val();
	$("#accepter").val(accepter);
}

function addProjectTask(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var initiator=$("#initiator").val();
	var accepter=$("#accepter").val();
	var projectNo=$("#projectNo").val();
	var description=$("#description").val();
	var finishTime=$("#finishTime").val();
	var urgentReason=$("#urgentReason").val();
	if(accepter==null || accepter=="" ||accepter==undefined){
		$("#accepterHtml").html('<font class="tips_false">请输入任务接受人</font>');
		return false;
	}
	if(projectNo==null || projectNo=="" ||projectNo==undefined){
		$("#projectNoHtml").html('<font class="tips_false">请输入相关项目号</font>');
		return false;
	}
	if(description==null || description=="" ||description==undefined){
		$("#descriptionHtml").html('<font class="tips_false">请输入任务简述</font>');
		return false;
	}
	if(finishTime==null || finishTime=="" ||finishTime==undefined){
		$("#finishTimeHtml").html('<font class="tips_false">请输入任务完成时间</font>');
		return false;
	}
	
	var projectNoStr="SHS"+projectNo;
	 
	 var ds = new Date();
	 ds.setDate(ds.getDate() + 2);
	 var days = ds.getDay(); 
		if(days==6||days==0){
		  ds.setDate(ds.getDate()+2);

		}
	 var finishTimeStr=new Date(ds.toString()).Format("yyyy-MM-dd");
	 
	 if((new Date(Date.parse(finishTime)))>(new Date(Date.parse(finishTimeStr)))){
		$("#finishTimeHtml").html('<font class="tips_false">任务要求的完成时间不能设为后天以后</font>');
		return false; 
	 }
	 
	 var checkWeekDay = new Date(Date.parse(finishTime))

	 if(checkWeekDay.getDay()==6||checkWeekDay.getDay()==0){
		 $("#finishTimeHtml").html('<font class="tips_false">请选择工作日时间</font>');
			return false; 
	 }
	 
	 
	 
	 $(".subm-but").attr("disabled",true).css("background-color","#999");
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/addProjectTask",           
	     data:{
	        	projectNo:projectNoStr,
	        	accepter:accepter,
	        	initiator:initiator,
	        	description:description,
	        	finishTime:finishTime,
	        	urgentReason:urgentReason
	     },              
	     success:function(data){  
	    	 
	    	  
		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				 $("#subHtml").html('<font class="tips_false">录入失败</font>');
				 $(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
			  }
			  
	     },
	     error : function() {
				$("#subHtml").html('<font class="tips_false">录入失败</font>');
				$(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
			}
	 });  
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



</script>
</html>

