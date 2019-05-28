package com.cn.hnust.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.User;
import com.cn.hnust.print.InspectionPrint;
import com.cn.hnust.print.ProjectPrint;
import com.cn.hnust.service.IInspectionReservationService;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.WebCookie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 验货预约任务
 * @author chenlun
 *
 */
@RequestMapping("/inspection")
@Controller
public class InspectionReservationController {

	@Autowired
	private IInspectionReservationService inspectionReservationService;
	@Autowired
	private IProjectTaskService projectTaskService;
	@Autowired 
	private IProjectReportService projectReportService;
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	
	@RequestMapping("/addInspection")
	@ResponseBody
	public JsonResult addInspection(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		InspectionReservation inspection=new InspectionReservation();
    	String projectNo=request.getParameter("projectNo");
    	String initiator=request.getParameter("initiator");
    	String accepter=request.getParameter("accepter");
    	String description=request.getParameter("description");
    	String finishTime=request.getParameter("finishTime");
    	String urgentReason=request.getParameter("urgentReason");
    	String produceStatus=request.getParameter("produceStatus");
    	String expectedDelivery=request.getParameter("expectedDelivery");
    	String shippingDate=request.getParameter("shippingDate");
    	String inspectionAddress=request.getParameter("inspectionAddress");
        String testType=request.getParameter("testType");
    	inspection.setProjectNo(projectNo);
    	inspection.setProjectNoId(IdGen.uuid());
    	inspection.setInitiator(initiator);
    	inspection.setAccepter(accepter);
    	inspection.setDescription(description);
    	inspection.setUrgentReason(urgentReason);
    	inspection.setFinishTime(DateUtil.StrToDate(finishTime));
		inspection.setProduceStatus(produceStatus);
		inspection.setExpectedDelivery(DateUtil.StrToDate(expectedDelivery));
		inspection.setTestType(testType);
		//大货检验默认50%开箱比例
		if("出货".equals(testType)){
			inspection.setOpenRate("50");
		}
		inspection.setCreateDate(new Date());
		if(StringUtils.isNotBlank(shippingDate)){
			inspection.setShippingDate(DateUtil.StrToDate(shippingDate));
		}
		inspection.setInspectionAddress(inspectionAddress);
		//inspection.setQualityDate(new Date());
		
		ProjectTask projectTask=new ProjectTask();
		projectTask.setProjectNoId(inspection.getProjectNoId());
		projectTask.setProjectNo(projectNo);
    	projectTask.setInitiator(initiator);
    	projectTask.setAccepter(accepter);
    	projectTask.setDescription(description);
    	projectTask.setUrgentReason(urgentReason);
    	projectTask.setFinishTime(DateUtil.StrToDate(finishTime));
    	projectTask.setTaskStatus("0");
    	projectTask.setTaskType("2");
    	projectTask.setStartTime(new Date());
    	projectTask.setCreateTime(new Date());
		try {
			inspectionReservationService.addInspectionReservation(inspection);
			projectTaskService.addProjectTask(projectTask);
			
			//批量更新工厂生产状态
			String factoryList = request.getParameter("factoryList");
			if(StringUtils.isNotBlank(factoryList)){
				List<ProjectFactory> factorys = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
				factorys = objectMapper.readValue(factoryList,javaType);
				if(factorys!=null&&factorys.size()>0){
					projectFactoryService.updateBatch(factorys);
				}			
			}
			
			jsonResult.setOk(true);
			jsonResult.setData("录入成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setData("录入失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@RequestMapping("/selectInspection")
	@ResponseBody
	public JsonResult selectInspection(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
		String inputKey=request.getParameter("inputKey");
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
		String userName=request.getParameter("userName");
		String qualityName=request.getParameter("quality_name");
		String taskStatus=request.getParameter("taskStatus");
		String approvalSelect = request.getParameter("approvalSelect");
		String qualityNames = request.getParameter("qualityNames");
		
		InspectionReservation inspection=new InspectionReservation();
		int pageNumber;
		if(StringUtils.isNotBlank(request.getParameter("pageNumber"))){
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));//第几页,1,2
		}else{
			pageNumber=1;
		}
		Integer pageSize=10;
		String pageSizeStr = request.getParameter("pageSize");
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		User user=userService.selectUserByName(userName);
		if(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 ||user.getRoleNo()==97 || "AndsXue".equals(userName)){
			inspection.setUserName(null);
		}else{
			inspection.setUserName(userName);
		}
		
		//根据质检名进行筛选
		String[] strs ={};
		if(StringUtils.isNotBlank(qualityNames)){	
			strs = qualityNames.split(",");
			inspection.setQualityNames(strs);
		}
		
		
		inspection.setPageSize(pageSize);
		inspection.setPageNumber(pageSize*(pageNumber-1));
		inspection.setProjectNo(projectNo);
		inspection.setInputKey(inputKey);
		inspection.setQualityName(qualityName);
		inspection.setTaskStatus(taskStatus);
		if(StringUtils.isNotBlank(approvalSelect)){
			inspection.setShippingApproval(Integer.parseInt(approvalSelect));
		}
		List<InspectionReservation> inspectionList=inspectionReservationService.selectInspectionReservation(inspection);
		int count=inspectionReservationService.selectInspectionReservationCount(inspection);
		for (InspectionReservation inspectionReservation : inspectionList) {
			//检验计划,图纸,检验员
			//1.查询项目的图纸信息
			List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(inspectionReservation.getProjectNo());
			//2.查询检验计划
			List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(inspectionReservation.getProjectNo());
			inspectionReservation.setInspectionPlanList(inspectionPlanList);
			inspectionReservation.setProjectDrawingList(projectDrawingList);
		}

		List<User> users = userService.queryByJob("质检");
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("inspectionList", inspectionList);
		hashMap.put("userId", userId);
		hashMap.put("roleNo", roleNo);
		hashMap.put("userName", userName);
		hashMap.put("pageSize", pageSize);
		hashMap.put("pageNumber", pageNumber);
		hashMap.put("count", count);
		hashMap.put("users", users);
		jsonResult.setData(hashMap);
		jsonResult.setOk(true);
		return jsonResult;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toSelectInspection")
	public String toSelectInspection(HttpServletRequest request,HttpServletResponse response){
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");

		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		return "task_list";
	}
	
	//更新检验人和初检时间
	@RequestMapping("/updateInspectionReservation")
	@ResponseBody
	public JsonResult updateInspectionReservation(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNoId=request.getParameter("projectNoId");
	    String accepter=request.getParameter("accepter");
	 	String finishTime=request.getParameter("finishTime");
	 	String userName = request.getParameter("userName");
	 	String openRate = request.getParameter("openRate");
	 	InspectionReservation inspectionReservation=new InspectionReservation();
	 	inspectionReservation.setProjectNoId(projectNoId);
	 	inspectionReservation.setAccepter(accepter);
	 	if(StringUtils.isNotBlank(finishTime)){
	 		inspectionReservation.setFinishTime(DateUtil.StrToDate(finishTime));
	 	}	 	
	 	inspectionReservation.setOpenRate(openRate);
	 	User user=userService.findUserByName(userName);
	 	if(!(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 || user.getRoleNo()==97)){
	 		jsonResult.setOk(false);
			jsonResult.setMessage("你没有权限操作");
	 	}else{
	 		try {
				inspectionReservationService.updateInspectionReservation(inspectionReservation);
			    //更新出检时间和任务接受人
				if(accepter!=null){//更新检验员和任务接受人
					ProjectTask projectTask=new ProjectTask();
					projectTask.setProjectNoId(projectNoId);
					projectTask.setAccepter(accepter);
					projectTask.setTaskStatus("0");
					projectTaskService.updateProjectTask(projectTask);
				}
				jsonResult.setOk(true);
				jsonResult.setMessage("操作成功");
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("操作失败");
				e.printStackTrace();
			}
	 	}
		return jsonResult;
	}
	
	
	   //更新开箱比例
		@RequestMapping("/updateOpenRate")
		@ResponseBody
		public JsonResult updateOpenRate(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult=new JsonResult();
			String projectNoId=request.getParameter("projectNoId");
		 	String openRate = request.getParameter("openRate");
		 	InspectionReservation inspectionReservation=new InspectionReservation();
		 	inspectionReservation.setProjectNoId(projectNoId); 	
		 	inspectionReservation.setOpenRate(openRate);
		 		try {
					inspectionReservationService.updateInspectionReservation(inspectionReservation);	
					jsonResult.setOk(true);
					jsonResult.setMessage("操作成功");
				} catch (Exception e) {
					jsonResult.setOk(false);
					jsonResult.setMessage("操作失败");
					e.printStackTrace();
				}
			return jsonResult;
		}
		
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toInputInspection")
	public String toInputInspection(HttpServletRequest request,HttpServletResponse response){
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
        String projectNo=request.getParameter("projectNo");
        Project project=new Project();
        String status="";
        Date today=new Date();
        if(StringUtils.isNotBlank(projectNo)){
        	 project=projectService.selectProjctDetails(projectNo);
        	// 判断项目状态
				if (project.getFinish() == 0) {//大货没有完结
					if (project.getIsPause() == null|| "0".equals(project.getIsPause())) {
						List<ProjectReport> pr = projectReportService.selectProjectReport(project.getProjectNo());
						if(project.getSampleFinish()==0 && pr.size()==0 && 
								project.getDeliveryDate() == null && project.getSampleScheduledDate()==null){
							status = "新立项项目";
						}
						//正在进行的项目判断
						if((project.getFinish()==0 && project.getSampleFinish() ==0 &&   pr.size()> 0 && project.getDeliveryDate()==null && project.getSampleScheduledDate()== null)
							     ||(project.getFinish()==0 && project.getSampleFinish() ==0  && ((project.getDeliveryDate()==null &&  project.getSampleScheduledDate()!=null && project.getSampleScheduledDate().getTime()+7*24*60*60*1000>today.getTime())
							     ||(project.getSampleScheduledDate()==null && project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime())
							     ||(project.getSampleScheduledDate()!=null && project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime()))) 
							     ||(project.getSampleFinish()==1 && project.getFinish()==0 && (/*(project.getDeliveryDate()==null)||*/(project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000 >today.getTime()))
							  )){
							status = "正在进行的项目";
						}
						//样品完结项目(点击样品完结,没有大货交期sortField=2 projectStatus=6 )
						if(project.getSampleFinish()==1 && project.getFinish()==0 && project.getDeliveryDate()==null){
							status = "样品完结项目";
						}
						//样品交期或者大货交期 延期
                     if(project.getDeliveryDate() != null){//大货交期不为空,样品交期不为空
     					if (project.getDeliveryDate() != null && project.getDeliveryDate().getTime()+7*24*60*60*1000< today.getTime() && project.getFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))) {// 交期小于当前时间,算延期
     						status = "延期项目";
     					}	
     				}
     				//2.大货交期为空,样品交期不为空,样品没完结，没取消暂停
     				else if(project.getSampleScheduledDate() != null && project.getDelayType()==null){
     					if(project.getSampleScheduledDate() != null && project.getSampleScheduledDate().getTime()+7*24*60*60*1000< today.getTime() && project.getSampleFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))){
     						status = "延期项目";
     					}
     				}
     				//3.样品交期完结了大货交期没完结并且有大货交期
     				else if(project.getSampleFinish()==1 && project.getDeliveryDate() != null){
     					if (project.getDeliveryDate() != null && project.getDeliveryDate().getTime() +7*24*60*60*1000 < today.getTime() && project.getFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))) {// 交期小于当前时间,算延期
     						status = "延期项目";
     					}	
     				}
					} else if ("1".contains(project.getIsPause())) {
						status = "暂停项目";

					} else if ("2".contains(project.getIsPause())) {
						status = "取消项目";
					}

				} else if (project.getFinish() == 1) {
					status = "完成项目";
				}
				project.setStatus(status);
        } 
        
         //查询下单工厂
        List<String> factoryList = projectFactoryService.selectAllFactory(null);
        
        request.setAttribute("project", project);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("factoryNameList", factoryList);
		return "project_task_entry";
	}
	
	
	/**
	 * 更新允许出货确认单是否收到
	 * @Title updateApprovalShipping 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateApprovalShipping")
	@ResponseBody
	public JsonResult updateApprovalShipping(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNoId = request.getParameter("projectNoId");
		String type=request.getParameter("type");
		// 查询该检测信息(projectNoId)
		try {
			InspectionReservation inspectionReservation = inspectionReservationService.selectInspectionReservationById(projectNoId);
			inspectionReservation.setShippingApproval(Integer.parseInt(type));
			inspectionReservationService.updateInspectionReservation(inspectionReservation);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
		} finally {
			return jsonResult;
		}
		
	}
	
	
	
	
	
	
    /**
     * 导出预约检验任务
     * @Title exportProject 
     * @Description 
     * @param request
     * @param response
     * @return void
     */
	@RequestMapping(value="/exportInspection")
	public void exportInspection(HttpServletRequest request,HttpServletResponse response){
		
        try {
        	String projectNo=request.getParameter("projectNo");
    		String inputKey=request.getParameter("inputKey");
    		String userName=request.getParameter("userName");
    		String qualityName=request.getParameter("quality_name");
    		String taskStatus=request.getParameter("taskStatus");
    		String approvalSelect = request.getParameter("approvalSelect");
    		String qualityNames = request.getParameter("qualityNames");
    		
    		InspectionReservation inspection=new InspectionReservation();
    		User user=userService.selectUserByName(userName);
    		if(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 ||user.getRoleNo()==97 || "AndsXue".equals(userName)){
    			inspection.setUserName(null);
    		}else{
    			inspection.setUserName(userName);
    		}
    		
    		//根据质检名进行筛选
    		String[] strs ={};
    		if(StringUtils.isNotBlank(qualityNames)){	
    			strs = qualityNames.split(",");
    			inspection.setQualityNames(strs);
    		}
    		inspection.setProjectNo(projectNo);
    		inspection.setInputKey(inputKey);
    		inspection.setQualityName(qualityName);
    		inspection.setTaskStatus(taskStatus);
    		if(StringUtils.isNotBlank(approvalSelect)){
    			inspection.setShippingApproval(Integer.parseInt(approvalSelect));
    		}
    		List<InspectionReservation> inspectionList=inspectionReservationService.selectInspectionReservation(inspection);
			String excelPath = InspectionPrint.printExcel(request, inspectionList);
			File outFile = new File(excelPath);  
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
			byte[] buffer = new byte[fis.available()];  
			fis.read(buffer);  
			fis.close();  
			// 清空response  
			response.reset();  
			// 设置response的Header  
			String fileName = "检验任务"+DateFormat.currentDate()+".xls";
			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
			response.addHeader("Content-Length", "" + outFile.length());  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
			response.setContentType("application/octet-stream");  
			toClient.write(buffer);  
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

}
