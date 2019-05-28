<%@ page language="java" contentType="text/html; charset=utf-8" 
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String roleNo=request.getParameter("roleNo");
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>任务系统，质检报告</title>
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">		
	</head>
<body>
	<div class="quality">
	    <input type="hidden" id="roleNo" value="${roleNo}">
	    <input type="hidden" id="userId" value="${userId}">
	    <input type="hidden" id="userName" value="${userName}">
	    
		<div class="title">
			<span class="glyphicon glyphicon-menu-left"></span>质检情况
		</div>
		<div class="wrap wrap1">
			<p>项目编号&nbsp;&nbsp;&nbsp;&nbsp;<span id="projectNo">${projectNo}</span></p>
			<!-- <ul>
				<li class="clearfix"><div class="c_l pull-left"><span class="pull-left">检验计划(采购)A版</span><input type="file" class="pull-left"></div>
				<button class="pull-right">上传</button></li>
				<li class="clearfix"><div class="c_l pull-left"><span class="pull-left">检验计划(质检)B版</span><input type="file" class="pull-left"></div>
				<button class="pull-right">上传</button></li>
				<li class="clearfix"><div class="c_l pull-left"><span class="pull-left">验厂报告</span><input type="file" class="pull-left"></div>
				<button class="pull-right">上传</button></li>
				<!-- <li class="clearfix"><div class="c_l pull-left"><span class="pull-left">校验报告</span><input type="file" class="pull-left"></div>
				<button class="pull-right">上传</button></li>
			</ul>-->
		</div>
		
		<div class="wrap wrap2">
			<p>检验报告</p>
			<form  id="create_form" onsubmit="return false;">
			<input type="hidden" name="projectNo" value=""/>
			<div class="imgs">
				<ul class="clearfix">
					<!--<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>
					<li><div class="img_in"><img src="" alt=""></div><span class="glyphicon glyphicon-remove"></span></li>  -->
					<li><em class="glyphicon glyphicon-plus"></em><input id="uploadFile" name="files"  onchange="show_drawingNames(this)" type="file" multiple/></li>
				</ul>
			</div>
			<div class="sele_">
				<label>这次上传的是：</label>
				<select id="type" name="type" value="" >
					<option value="0" checked>样品检验</option>
					<!-- <option value="1">大货样品</option> -->
					<option value="2">中期检验</option>
					<option value="3">终期检验</option>
				</select>
				<span>报告</span>
			</div>
			<div class="report">
				<!-- <p >报告</p> -->
				<div class="radios">
					<div class="radio_0"><input type="radio" name="state" value="0" checked> 没问题</div>
					<div class="radio_0 second_radio"><input type="radio" name="state" value="1" > 有问题，但已经处理</div>
					<div class="radio_0 last_radio"><input type="radio" name="state" value="2"> 有问题，需要采购解决

						<div class="task">
							<div class="select_more">
								<!-- <span><input type="checkbox"><em>采购名1</em></span><span><input type="checkbox"><em>采购名2</em></span><span><input type="checkbox"><em>采购名3</em></span> -->
							    <span><em id="purchase">${purchase}</em></span>
							</div>
							<textarea id="taskDetail" class="form-control" placeholder="任务说明"></textarea>
						</div>
						
						<div class="explain" style="display:none">
							<div class="select_more">
								<!-- <span><input type="checkbox"><em>采购名1</em></span><span><input type="checkbox"><em>采购名2</em></span><span><input type="checkbox"><em>采购名3</em></span> -->
							    <span><em id="purchase">${purchase}</em></span>
							</div>
							<textarea id="explain" class="form-control" placeholder="原因说明"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btns text-right">
			<button class="btn" onclick="savaAll()">提交所有上传图片和问题</button>
			<span class="tips" style="color:red;" id="subHtml"></span>
		</div>
		</form>
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>

<script>
        window.onload = window.onresize = function(){

            var clientWidth =document.documentElement.clientWidth;

            document.getElementsByTagName("html")[0].style.fontSize =

                    clientWidth / 768* 40 + "px";
        }
</script>
<script>
	/*添加图片*/
	var $li = '<li><div class="img_in">'
	+'<img src="" alt="">'
	+'</div><span class="glyphicon glyphicon-remove"></span>'
	+'</li>';
	
	
	
	/*删除当前图片*/
	$('.quality .wrap2 li span').on('click',function(){
		$(this).parent().remove();
	})
	
	
	/*报告结果*/
	//$('.quality .wrap2 .report .task').hide();
	$('.quality .wrap2 .report input').click(function(){
		if($('.quality .last_radio input').is(':checked') || $('.quality .second_radio input').is(':checked')){
			if($('.quality .last_radio input').is(':checked')){
				if($('#purchase').text()){
					$('.quality .wrap2 .report .task').show();
				}else{
					alert('该项目没有分配采购')
					$('#create_form').find('input[name=state][value=0]').prop('checked',true)
				}	
				$('.quality .wrap2 .report .explain').hide();
			}
			//有问题,已解决问题说明
			if($('.quality .second_radio input').is(':checked')){
				if($('#purchase').text()){
					$('.quality .wrap2 .report .explain').show();
				}else{
					alert('该项目没有分配采购')
					$('#create_form').find('input[name=state][value=0]').prop('checked',true)
				}	
				$('.quality .wrap2 .report .task').hide();
			}
			
		}else{
			$('.quality .wrap2 .report .task').hide();
			$('.quality .wrap2 .report .explain').hide();
		}
	})
	
</script>
<script>


$(function(){

	
})



 function show_drawingNames(obj) {

	var uploadFiles = $('#uploadFile')[0].files;

	var fileNames = '';
	for (var i = 0; i < uploadFiles.length; i++) {

		if (i == uploadFiles.length - 1) {
			fileNames += uploadFiles[i].name;
		} else {
			fileNames += uploadFiles[i].name + ';';
		}

	}
	if (fileNames == null || fileNames == '' || fileNames == undefined) {
		return false;

	}
    var projectNo = $('#projectNo').text()
    $('#create_form').find('input[name=projectNo]').val(projectNo)
   
   
	
	// 先上传后获取上传文件路径
	$("#create_form").ajaxSubmit({
		type : "post",
		url : "${ctx}/upload/uploadProductPicAndChangeName.do",
		dataType : "text",
		async : false,
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.ok) {
				var data = result.data;
				var addhtml=''
				for(var i=0;i<data.length;i++){

					addhtml+='<li><div class="img_in">'
					+'<a href="'+data[i].replace('/compressImg','')+'" target="_blank">'
					+'<img src="'+data[i]+'" alt=""></a>'
					+'</div><span class="glyphicon glyphicon-remove"></span>'
					+'</li>';
				}
				
				$('.clearfix').prepend(addhtml)
				
			}

		

		},
		error : function() {
			
		}
	});

}
    	
 
 function savaAll(){
     var projectNo = $('#projectNo').text()
	 var userName = $('#userName').val()
	 var roleNo = $('#roleNo').val()
	 var userId = $('#userId').val()
	 
	 var picUrl=''
	 var purchaseName=''
     $('.clearfix li div').find('a').each(function(){
        if(!picUrl){
        	picUrl=$(this).attr('href')
        }else{
        	picUrl=picUrl+';'+$(this).attr('href')
        }
     })
    
     if(!picUrl){
    	 alert("请上传图片")
    	 return false
     }
     
	 var state = ''
	 $('#create_form').find('input[name=state]').each(function(){
		 if($(this).prop('checked')){
			 state= $(this).val()
		 }
	 })
	 if(state=='2'){
		 if($('#taskDetail').val()==''){
			 alert('请输入任务内容')
			 return false
		 }	 
		 purchaseName=  $('#purchase').text()
	 }
	 if(state=='1'){
		 if($('#explain').val()==''){
			 alert('请输入任务内容')
			 return false
		 }	 
		 purchaseName=  $('#purchase').text()
	 }
     var taskDetail = $('#taskDetail').val()
     var explain=$("#explain").val();
     
     $(".btn").attr("disabled",true).css("background-color","#999");
     
     var type =$('#type').val()
     // $('#type').find('option').each(function(){
     //	  if($(this).prop('checked')){
    //		  type= $(this).val()
 	//	 }
    	  
   //  })
   
     
   
     
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/quality/saveQuality",           
	     data:{
	        	projectNo:projectNo,
	        	userName:userName,
	        	picUrl:picUrl,
	        	type:type,
	        	state:state,
	        	taskDetail:taskDetail,
	        	explain:explain,
	        	purchaseName:purchaseName
	     },              
	     success:function(data){
	    	 var json = eval("(" + data +")");
			 if(json.ok){
				  window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">录入失败'+(json.message?json.message:"")+'</font>');
				  $(".btn").css("background-color", "#027CFF").removeAttr('disabled');
			  }
	     },
	     error:function(){
			  $("#subHtml").html('<font class="tips_false">录入失败'+(json.message?json.message:"")+'</font>');
			  $(".btn").css("background-color", "#027CFF").removeAttr('disabled');
	     }
	     
	     })
	 return false
	
 }
    	
    	
    	
    


</script>

