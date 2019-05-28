package com.cn.hnust.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipOutputStream;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcReportHelper;
import com.cn.hnust.enums.QualityImgTypeEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.enums.TaskStatusEnum;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.InspectionPlan;
import com.cn.hnust.pojo.ProductionPlan;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QualityReportQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.print.QualityReportPrint;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;
import com.cn.hnust.service.IInspectionReservationService;
import com.cn.hnust.service.IProductionPlanService;
import com.cn.hnust.service.IProjectCommentService;
import com.cn.hnust.service.IProjectERPService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IQualityPicExplainService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.InspectionPlanService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;
import com.cn.hnust.util.WebCookie;
import com.cn.hnust.util.ZipUtil;

/**
 * 质检报告生成处理controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/quality")
public class QualityController {

	private static final Log LOG = LogFactory.getLog(QualityController.class);

	@Resource
	private IProjectERPService projectErpService;

	@Resource
	private IProjectTaskService projectTaskService;

	@Resource
	private IQualityReportService qualityReportService;
	
	@Autowired
	private IQualityPicExplainService qualityPicExplainService;
	
	@Autowired
	private IInspectionReservationService inspectionReservationService;

	@Autowired
	private IProjectService projectService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IProjectCommentService projectCommentService;
	
	@Autowired
	private ProjectFactoryService projectFactoryService;
	
	@Autowired
	private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	
	@Autowired
	private IProductionPlanService productionPlanService;
	
	@Autowired
	private InspectionPlanService inspectionPlanService;

	/**
	 * 跳转质检报告录入页面
	 * @Title showDetails 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/addQuality")
	public String showDetails(HttpServletRequest request,
			HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String checkType = request.getParameter("checkType");
		String openRate = request.getParameter("openRate");
		String projectNoId = request.getParameter("projectNoId");
		

//		ProjectERP proErp = projectErpService.selectProjectErpByNo(projectNo);
		Project project = projectService.selectProjctDetails(projectNo);
		//检验任务，查询未完成的检验任务		
		ProjectTask projectTask = new ProjectTask();
		projectTask.setProjectNo(projectNo);
		projectTask.setAccepter(userName);
		projectTask.setTaskStatus(TaskStatusEnum.DEFAULT.getCode()+""); 
		projectTask.setTaskType("2");	
		projectTask.setPageNumber(0);
		projectTask.setPageSize(50);
		List<ProjectTask> tasks = projectTaskService.selectProjectTask(projectTask);

		if (project != null) {
			if (StringUtils.isNotBlank(project.getPurchaseName())) {
				request.setAttribute("purchase", project.getPurchaseName());
			}
		}
        
		//查询工厂
		String inputKey = request.getParameter("inputKey");
		List<String> factoryNameList = projectFactoryService.selectAllFactory(inputKey);	
		request.setAttribute("factoryNameList", factoryNameList);
		//根据项目号查询合同工厂
		List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
		//查询检验计划
		List<InspectionPlan> plans = inspectionPlanService.selectByProjectNo(projectNo,null);
		
		 //去重后
		List<InspectionPlan> distinctPlan = plans.stream().filter(distinctByKey(plan->plan.getProductComponent())).collect(Collectors.toList());



		
		
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("project", project);
		request.setAttribute("projectNo", projectNo);
		request.setAttribute("tasks", tasks);
		request.setAttribute("projectName",project.getProjectName());
		request.setAttribute("factoryList",factoryList);
		request.setAttribute("checkType",checkType);
		request.setAttribute("openRate",openRate);
		request.setAttribute("projectNoId",projectNoId);
		request.setAttribute("plans",plans);
		request.setAttribute("distinctPlan",distinctPlan);

		return "detail";
	}

	
	/**
	 * 保存质检报告
	 * @Title showDetails 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/saveQuality")
	@ResponseBody
	public JsonResult saveQuality(HttpServletRequest request,
			HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();

		String projectNoId = request.getParameter("projectNoId");
		String projectNo = request.getParameter("projectNo");
		String userName = request.getParameter("userName");
		String detailUrl = request.getParameter("detailUrl");
		String badUrl = request.getParameter("badUrl");
		String materialUrl = request.getParameter("materialUrl");
		String packageUrl = request.getParameter("packageUrl");
		String checkUrl = request.getParameter("checkUrl");
		String typeStr = request.getParameter("type");
		String stateStr = request.getParameter("state");
		String taskDetail = request.getParameter("taskDetail");
		String purchaseName = request.getParameter("purchaseName");
		String explain=request.getParameter("explain");
		String checkExplain=request.getParameter("checkExplain");
		String packageExplain=request.getParameter("packageExplain");
		String inspectionPath=request.getParameter("inspectionPath");
		String inspectionForm=request.getParameter("inspectionForm");
		String conclusion=request.getParameter("conclusion");
		String taskId = request.getParameter("taskId");
		String inspectionCreateDate = request.getParameter("inspectionCreateDate");
		Integer type = 0;
		
		String jsonString = request.getParameter("param");
		ObjectMapper mapper = new ObjectMapper();


		
		try {
			QualityReport qr = mapper.readValue(jsonString, QualityReport.class);
			
			if (StringUtils.isNotBlank(typeStr)) {
				type = Integer.parseInt(typeStr);
			}
			Integer state = Integer.parseInt(stateStr);

//			QualityReport qr = new QualityReport();
//			qr.setPicUrl(picUrl);
			qr.setProjectNoId(projectNoId);
			qr.setProjectNo(projectNo);
			qr.setUser(userName);
			qr.setStatus(state);
			qr.setType(type);
			qr.setCheckExplain(checkExplain);
			qr.setPackageExplain(packageExplain);
			qr.setInspectionForm(inspectionForm);
			qr.setInspectionPath(inspectionPath);
			qr.setConclusion(conclusion);
			//检验计划日期
			if(StringUtils.isNotBlank(inspectionCreateDate)){
				qr.setInspectionCreateDate(inspectionCreateDate);
			}
			//关键尺寸超差的数量
			if(StringUtils.isNotBlank(request.getParameter("keySizeExceed"))){
				qr.setKeySizeExceed(Integer.parseInt(request.getParameter("keySizeExceed")));
			}
			//因为没检测工具无法测量数量
			if(StringUtils.isNotBlank(request.getParameter("noCheck"))){
				qr.setNoCheck(Integer.parseInt(request.getParameter("noCheck")));
			}
			
			if(StringUtils.isNotBlank(explain)){
				qr.setExplainCause(explain);
			}else{
				qr.setExplainCause(taskDetail);
			}
			qr.setCreatetime(new Date());
			//工厂名
			Project project = projectService.selectProjctDetails(projectNo);
			if(project!=null){
				qr.setCompanyName(project.getCompanyName());
			}
			
			//批量更新工厂生产状态
			String factoryList = request.getParameter("factoryList");
			if(StringUtils.isNotBlank(factoryList)){
				List<ProjectFactory> factorys = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
				factorys = objectMapper.readValue(factoryList,javaType);
				//当返修补货完结时，对应样品合同或者大货合同也需要有完结时间
				for (ProjectFactory projectFactory : factorys) {
					if(projectFactory.getRepairReplenishmentFinishTime()!=null && projectFactory.getOrderNature() == 2 && projectFactory.getSampleDate() != null){
						projectFactory.setSampleFinishTime(projectFactory.getRepairReplenishmentFinishTime());
					}
					if(projectFactory.getRepairReplenishmentFinishTime()!=null && projectFactory.getOrderNature() == 2 && projectFactory.getDeliveryDate() != null){
						projectFactory.setProductFinishTime(projectFactory.getRepairReplenishmentFinishTime());
					}
				}
				if(factorys!=null&&factorys.size()>0){
					projectFactoryService.updateBatch(factorys);
				}	
			}
			
			qualityReportService.insertSelective(qr);
			
			//如果存在检测计划，选中后自动更新为已完成
			if(StringUtils.isNotBlank(taskId)){
				projectTaskService.selectProjectTaskById(Integer.parseInt(taskId));
				ProjectTask task = new ProjectTask();
				task.setId(Integer.parseInt(taskId));
				task.setTaskStatus(TaskStatusEnum.FINISH.getCode()+"");
				task.setOperator(userName);
				task.setOperatorTime(new Date());
				task.setFinishTime(new Date());
				projectTaskService.updateProjectTask(task);
			}
			
			
			
			//将详情图片路径拆分
			if(StringUtils.isNotBlank(detailUrl)){
				String detailUrls[]=detailUrl.split(";");
				for (int i = 0; i < detailUrls.length; i++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(detailUrls[i]);
				    qualityPicExplain.setProjectNo(projectNo);
				    qualityPicExplain.setQualityReportId(qr.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplain.setPicType(QualityImgTypeEnum.DETAIL_IMG.getCode());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}
			//将不合格产品图片路径拆分
			if(StringUtils.isNotBlank(badUrl)){
				String badUrls[]=badUrl.split(";");
				for (int i = 0; i < badUrls.length; i++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(badUrls[i]);
				    qualityPicExplain.setProjectNo(projectNo);
				    qualityPicExplain.setQualityReportId(qr.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplain.setPicType(QualityImgTypeEnum.BAD_IMG.getCode());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}

			//将材质图片路径拆分
			if(StringUtils.isNotBlank(materialUrl)){
				String materialUrls[]=materialUrl.split(";");
				for (int i = 0; i < materialUrls.length; i++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(materialUrls[i]);
				    qualityPicExplain.setProjectNo(projectNo);
				    qualityPicExplain.setQualityReportId(qr.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplain.setPicType(QualityImgTypeEnum.MATERIAL_IMG.getCode());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}
			//将包装图片路径拆分
			if(StringUtils.isNotBlank(packageUrl)){
				String packageUrls[]=packageUrl.split(";");
				for (int i = 0; i < packageUrls.length; i++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(packageUrls[i]);
				    qualityPicExplain.setProjectNo(projectNo);
				    qualityPicExplain.setQualityReportId(qr.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplain.setPicType(QualityImgTypeEnum.PACKAGE_IMG.getCode());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}
			//将质检报告表图片路径拆分
			if(StringUtils.isNotBlank(checkUrl)){
				String checkUrls[]=checkUrl.split(";");
				for (int i = 0; i < checkUrls.length; i++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(checkUrls[i]);
				    qualityPicExplain.setProjectNo(projectNo);
				    qualityPicExplain.setQualityReportId(qr.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplain.setPicType(QualityImgTypeEnum.TABLE_IMG.getCode());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}
			
	
			
			if(state == 2){
				ProjectTask pt = new ProjectTask();
				pt.setAccepter(purchaseName);
				pt.setInitiator(userName);
				pt.setProjectNo(projectNo);
				pt.setDescription("质检发现有问题，需要采购解决："+ taskDetail);
				pt.setTaskStatus("0");
				pt.setStartTime(new Date());
				pt.setCreateTime(new Date());
				pt.setFinishTime(getFinishDate());
				pt.setQualityId(qr.getId());	
				pt.setTaskType("9");
				projectTaskService.addProjectTask(pt);//有问题需要给采购布置任务	
			}
			
			//需要提示质检去erp上是否需要更新检验报告
			ProjectTask qualityTask=new ProjectTask();
			qualityTask.setAccepter(userName);
			qualityTask.setInitiator("system");
			qualityTask.setProjectNo(projectNo);
			qualityTask.setDescription("需要在ERP系统上更新检验计划");
			qualityTask.setTaskStatus("0");
			qualityTask.setStartTime(new Date());
			qualityTask.setCreateTime(new Date());
			qualityTask.setFinishTime(getFinishDate());
//			qualityTask.setQualityId(qr.getId());			
			projectTaskService.addProjectTask(qualityTask);
			
			qr.setQualityReportUrl("http://112.64.174.34:10010/quality/download?id="+qr.getId());
			//将质检报告信息同步到ERP
			RpcReportHelper.sendRequest("", qr);//同步到ERP系统上
			
			
			//检验计划完成情况更新
			String planList = request.getParameter("planList");
			if(StringUtils.isNotBlank(planList)){
				List<InspectionPlan> plans = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, InspectionPlan.class);
				plans = objectMapper.readValue(planList,javaType);
				if(plans!=null && plans.size()>0){
					inspectionPlanService.updateBatch(plans);
				}
			}

			jsonResult.setOk(true);
			jsonResult.setData(qr.getId());

		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setData("录入失败");
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}

	private Date getFinishDate() {

		Calendar ca = Calendar.getInstance();

		ca.add(Calendar.DATE, 7);

		int day = ca.get(Calendar.DAY_OF_WEEK);

		if (day == 7 || day == 1) {
			ca.add(Calendar.DATE, 2);
		}
		Date finishTime = ca.getTime();

		return finishTime;

	}
	
	/**
	 * 删除质检报告
	 * @Title deleteQuality 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping("/deleteQuality")
	@ResponseBody
	public JsonResult deleteQuality(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {
            
			String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				qualityReportService.deleteByPrimaryKey(id);
				projectTaskService.deleteByQualityId(id);
				jsonResult.setOk(true);
				jsonResult.setMessage("删除成功");

			return jsonResult;
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("删除失败");
			e.printStackTrace();
			return jsonResult;
		}

	}
	
	/**
	 * 质检报告列表
	 * @Title viewQuality 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/qualityList")
	public String qualityList(HttpServletRequest request,HttpServletResponse response) {
		try {

			String pageStr = request.getParameter("pageStr"); 
			String inputKey = request.getParameter("inputKey");   // 搜索词
			String pageSizeStr = request.getParameter("pageSize");
			Integer roleNo = null;          // 判断是管理员，销售，采购
            String userName = WebCookie.getUserName(request);
            if(StringUtils.isNotBlank(userName)){
            	User user = userService.findUserByName(userName);
            	roleNo = user.getRoleNo();
            }else{
            	return "redirect:/index.jsp";
            }                            
        	//页数
			Integer page = null;
			if(StringUtils.isNotBlank(pageStr)){
				page = Integer.parseInt(pageStr);
			}else{
				page = 1;
			}
			//每页显示数
			Integer pageSize = null;
			if(StringUtils.isNotBlank(pageSizeStr)){
				pageSize = Integer.parseInt(pageSizeStr);
			}else{
				pageSize = 100;
			}
			QualityReportQuery qualityReportQuery = new QualityReportQuery();
			qualityReportQuery.setPageSize(pageSize);
			qualityReportQuery.setPageNumber(pageSize * (page - 1));
			qualityReportQuery.setInputKey(inputKey);
			qualityReportQuery.setRoleNo(roleNo);
			qualityReportQuery.setUserName(userName);
			
			//查询
			List<QualityReport> reports = qualityReportService.selectAllReport(qualityReportQuery);
			for (QualityReport qualityReport : reports) {
				
				//查询采购是否进行回复
				Boolean purchaseReply = false;				
				//查询阳工是否进行回复
				Boolean yangReply = false; 
				
				String sellName = qualityReport.getSellName();	
				String purchaseName = qualityReport.getPurchaseName();				
				List<Comment> comments = projectCommentService.selectByReportId(qualityReport.getId());
				for (Comment comment : comments) {
					if(comment.getReviewer().equalsIgnoreCase(purchaseName)){
						purchaseReply = true;
						qualityReport.setPurchaseReplyComment(comment);
					}
					if("yanggong".equalsIgnoreCase(comment.getReviewer())){
						yangReply = true;
						 //阳工回复内容
						qualityReport.setYangReplyContent(comment.getComment());
					}
					//销售回复
					if(comment.getReviewer().equalsIgnoreCase(sellName)){
						qualityReport.setSellReplyContent(comment.getComment());
					}
				}
				qualityReport.setPurchaseReply(purchaseReply);
				qualityReport.setYangReply(yangReply);
				//查询是否有关联任务，是否完成
//				List<ProjectTask> projectTasks = projectTaskService.selectByQualityId(qualityReport.getId());
//				qualityReport.setProjectTaskList(projectTasks);
				
			}
			int totalCount = qualityReportService.totalReports(qualityReportQuery);
			request.setAttribute("reports", reports);
			request.setAttribute("inputKey", inputKey);
			request.setAttribute("page", page);
			request.setAttribute("count", totalCount);
			//计算尾页
			Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
			request.setAttribute("lastNum", lastNum);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "quality_report_list";
	}
	
	
	/**
	 * 根据id查询质检报告
	 * @Title viewQuality 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/viewQuality")
	public String viewQuality(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			QualityReport qr =  qualityReportService.selectByPrimaryKey(id);
			List<ProjectTask> ptList =  projectTaskService.selectByQualityId(id);
			if(ptList!=null){
				qr.setProjectTaskList(ptList);
			}
			qr.setTypeView(QualityTypeEnum.getEnum(qr.getType()).getValue());
			qr.setStatusView(QualityStatusEnum.getEnum(qr.getStatus()).getValue());
			
//			qr.setPicUrls(qr.getPicUrl().split(";"));
			
			qr.setCreatetimeView(DateFormat.date2String(qr.getCreatetime()));
			
			List<QualityPicExplain> picList = qualityPicExplainService.queryByReportId(id);
			
			request.setAttribute("qualityReport", qr);
			request.setAttribute("picList", picList);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "quality_report";
	}
	
	
	/**
	 * 分享页面查看质检报告
	 * @Title viewQuality 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/shareQuality")
	public String shareQuality(HttpServletRequest request,HttpServletResponse response) {
		try {

			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			QualityReport qr =  qualityReportService.selectByPrimaryKey(id);
			String conclusion = qr.getConclusion();
			//去除conclusion内换行
			if(StringUtils.isNotBlank(conclusion)){				
			    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(conclusion);
	            conclusion = m.replaceAll("");
	            qr.setConclusion(conclusion);
			}			
			List<ProjectTask> ptList =  projectTaskService.selectByQualityId(id);
			if(ptList!=null){
				qr.setProjectTaskList(ptList);
			}
			qr.setTypeView(QualityTypeEnum.getEnum(qr.getType()).getValue());
			qr.setStatusView(QualityStatusEnum.getEnum(qr.getStatus()).getValue());
			
			qr.setCreatetimeView(DateFormat.date2String(qr.getCreatetime()));
			
			List<QualityPicExplain> picList = qualityPicExplainService.queryByReportId(id);
			Project project = projectService.selectProjctDetails(qr.getProjectNo());
			
			//项目号内特殊字符处理
			String projectName = project.getProjectName();
			if(StringUtils.isNotBlank(projectName)){			    
	            project.setProjectName(projectName.replace("\"",""));
			}
			
			//查询评论
			List<Comment> comments = projectCommentService.selectByReportId(id);
			//查询采购布置任务情况
			String purchaseTask = "";
			List<ProjectTask> tasks = projectTaskService.selectByQualityId(id);
			if(tasks != null && tasks.size() > 0){
				int tl = tasks.size();
				if((TaskStatusEnum.DEFAULT.getCode()+"").equals(tasks.get(tl-1).getTaskStatus())){
					purchaseTask = "未完成";
				}else if((TaskStatusEnum.FINISH.getCode()+"").equals(tasks.get(tl-1).getTaskStatus())){
					purchaseTask = "已完成  " + (tasks.get(tl-1).getOperateExplain() == null ? "" : tasks.get(tl-1).getOperateExplain());
				}else if((TaskStatusEnum.PAUSE.getCode()+"").equals(tasks.get(tl-1).getTaskStatus())){
					purchaseTask = "已暂停";
				}else if((TaskStatusEnum.CANCEL.getCode()+"").equals(tasks.get(tl-1).getTaskStatus())){
					purchaseTask = "已取消";
				}
			}
			
			//查询cookie中用户
			String userName = WebCookie.getUserName(request);
			if(StringUtils.isNotBlank(userName)){
				User user = userService.selectUserByName(userName);
				request.setAttribute("user", user);
			}
			//查询下单工厂列表
			List<ProjectFactory> projectFactoryList = projectFactoryService.selectByProjectNo(qr.getProjectNo());
			//根据工厂名去除重复工厂
			projectFactoryList = projectFactoryList.stream().filter(distinctByKey(factory->factory.getFactoryName())).collect(Collectors.toList());
			//质检视频
			List<FactoryQualityInspectionVideo> videos = factoryQualityInspectionVideoService.selectByProjectNo(qr.getProjectNo());
			//查询需求汇总
			ProductionPlan productionPlan = productionPlanService.selectDemandByProjectNo(qr.getProjectNo());
			if(productionPlan!=null){
				String node = productionPlan.getPlanNode();
				if(StringUtils.isNotBlank(node)){
					productionPlan.setPlanNode(URLEncoder.encode(node,"utf-8"));
				}
			}			
			//查询检验计划完成情况
			List<InspectionPlan> planList = null;
			List<InspectionPlan> plans = null;
			if(qr.getInspectionCreateDate()!=null){
				planList = inspectionPlanService.selectByProjectNo(qr.getProjectNo(),qr.getInspectionCreateDate());
				plans = planList.stream().filter(distinctByKey(plan->plan.getProductComponent())).collect(Collectors.toList());
			}
	
			
			
			request.setAttribute("qualityReport", qr);
			request.setAttribute("picList", picList);
			request.setAttribute("project", project);
			request.setAttribute("comments", comments);
			request.setAttribute("userName", userName);
			request.setAttribute("purchaseTask", purchaseTask);
			request.setAttribute("projectFactoryList", projectFactoryList);
			request.setAttribute("videos", videos);
			request.setAttribute("productionPlan", productionPlan);
			request.setAttribute("planList", planList);
			request.setAttribute("plans", plans);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "share_report";
	}
	
	
	
	/**
	 * 分享页面查看质检报告(给国外分享)
	 * @Title viewQuality 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/shareQualityEn")
	public String shareQualityEn(HttpServletRequest request,HttpServletResponse response) {
		try {

			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			QualityReport qr =  qualityReportService.selectByPrimaryKey(id);
			String conclusion = qr.getConclusion();
			//去除conclusion内换行
			if(StringUtils.isNotBlank(conclusion)){				
			    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(conclusion);
	            conclusion = m.replaceAll("");
	            qr.setConclusion(conclusion);
			}			
			List<ProjectTask> ptList =  projectTaskService.selectByQualityId(id);
			if(ptList!=null){
				qr.setProjectTaskList(ptList);
			}
			qr.setTypeView(QualityTypeEnum.getEnum(qr.getType()).getValue());
			qr.setStatusView(QualityStatusEnum.getEnum(qr.getStatus()).getValue());			
			qr.setCreatetimeView(DateFormat.date2String(qr.getCreatetime()));			
			List<QualityPicExplain> picList = qualityPicExplainService.queryByReportId(id);
			Project project = projectService.selectProjctDetails(qr.getProjectNo());
			
			//项目号内特殊字符处理
			String projectName = project.getProjectName();
			if(StringUtils.isNotBlank(projectName)){			    
	            project.setProjectName(projectName.replace("\"",""));
			}

			request.setAttribute("qualityReport", qr);
			request.setAttribute("picList", picList);
			request.setAttribute("project", project);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "share_report_en";
	}
	

	
	/**
	 * 导出压缩包增加生成文档打包
	 * @Title drawingFileDownload 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @return ResponseEntity<byte[]>
	 */
	 @RequestMapping(value = "download")
	    public ResponseEntity<byte[]> drawingFileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
	    	
	    	
	    	ResponseEntity<byte[]> download =null;
			try {
				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				QualityReport qr =  qualityReportService.selectByPrimaryKey(id);
//				String[] imgurls = qr.getPicUrl().split(";");
				List<QualityPicExplain> picList = qualityPicExplainService.queryByReportId(id);
				
				//各种质检图片生成报告
				QualityReport qualityReport =qualityReportService.selectByPrimaryKey(id);
				Project project = projectService.selectProjctDetails(qualityReport.getProjectNo());
				List<QualityPicExplain> details = qualityPicExplainService.queryPicByType(id, QualityImgTypeEnum.DETAIL_IMG.getCode());
				List<QualityPicExplain> bads = qualityPicExplainService.queryPicByType(id, QualityImgTypeEnum.BAD_IMG.getCode());
				List<QualityPicExplain> materials = qualityPicExplainService.queryPicByType(id, QualityImgTypeEnum.MATERIAL_IMG.getCode());
				List<QualityPicExplain> packages = qualityPicExplainService.queryPicByType(id, QualityImgTypeEnum.PACKAGE_IMG.getCode());
				List<QualityPicExplain> checks = qualityPicExplainService.queryPicByType(id, QualityImgTypeEnum.TABLE_IMG.getCode());
				String excelPath = QualityReportPrint.printExcel(request, request.getSession().getServletContext().getRealPath(File.separator), project, qualityReport, details, bads, materials, packages,checks);
		     
				
				//生成压缩包名
				StringBuffer fileName = new StringBuffer();
				fileName.append(DateFormat.currentDate().replace("-", ""));
				fileName.append("_");
				fileName.append(project.getProjectNo());
				fileName.append("_");
				if(StringUtils.isNotBlank(project.getProjectName())){
					String projectName = project.getProjectName();
					projectName = projectName.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
					fileName.append(projectName);
					fileName.append("_");
				}
				if(StringUtils.isNotBlank(qualityReport.getPicNum())){
					String picNum = qualityReport.getPicNum();
					picNum = picNum.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
					fileName.append(picNum);
					fileName.append("_");
				}
				fileName.append(QualityTypeEnum.getEnum(qualityReport.getType()).getValue());
				fileName.append("_");
				fileName.append(qualityReport.getUser());
				fileName.append(".zip");				
				FileOutputStream fous = new FileOutputStream(UploadAndDownloadPathUtil.getFilePath() + fileName);   
		            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
		             * 所以这里我们把输出流转换一下*/
		       ZipOutputStream zipOut = new ZipOutputStream(fous);
				for (QualityPicExplain qualityPicExplain : picList) {
					String picPath = qualityPicExplain.getPicName();
					File file = new File(picPath);
					String picName = file.getName();
					String path = UploadAndDownloadPathUtil.getProjectImg()
							+ File.separator + qr.getProjectNo() + File.separator + "1"+File.separator + picName; 
					File picFile = new File(path);
					ZipUtil.zipFile(picFile, zipOut);
				}
				File excelFile = new File(excelPath);
				ZipUtil.zipFile(excelFile, zipOut);
				zipOut.close();
				fous.close();
				fous.flush();
//				ZipUtils.doZip(picList,UploadAndDownloadPathUtil.getFilePath(),excelPath,qr.getProjectNo()+".zip");
			    download = OperationFileUtil.download(UploadAndDownloadPathUtil.getFilePath()+fileName);	
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			return download;

	    }
	    
	    /**
	     * 删除质检图片
	     * @Title deletePic 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return JsonResult
	     */
	    @RequestMapping("/deletePic")
		@ResponseBody
		public JsonResult deletePic(HttpServletRequest request,
				HttpServletResponse response) {
			JsonResult jsonResult = new JsonResult();
			try {

				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
			    qualityPicExplainService.deleteByPrimaryKey(id);
				jsonResult.setOk(true);
				jsonResult.setMessage("更新成功");
				return jsonResult;

			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
				return jsonResult;

			}

		}
		
	    	
	    /**
	     * PC端查看质检报告
	     * @Title viewQualityPc 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return JsonResult
	     */
	    @RequestMapping("/viewQualityPc")
	    @ResponseBody
		public JsonResult viewQualityPc(HttpServletRequest request,HttpServletResponse response) {
	    	JsonResult jsonResult=new JsonResult();
			try {
				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				//质检报告主表
				QualityReport qr =  qualityReportService.selectByPrimaryKey(id);
				List<ProjectTask> ptList =  projectTaskService.selectByQualityId(id);
				//质检报告图片说明表
				List<QualityPicExplain> qualityPicExplainList=qualityPicExplainService.queryByReportId(id);
				if(ptList!=null){
					qr.setProjectTaskList(ptList);
				}
				qr.setTypeView(QualityTypeEnum.getEnum(qr.getType()).getValue());
				qr.setStatusView(QualityStatusEnum.getEnum(qr.getStatus()).getValue());
				qr.setQualityPicExplainList(qualityPicExplainList);
				qr.setCreatetimeView(DateFormat.date2String(qr.getCreatetime()));
				//根据项目号查询项目名和等级
				ProjectERP projectErp=projectErpService.selectProjectErpByNo(qr.getProjectNo());
				qr.setProjectName(projectErp.getProjectNameC());
				if(projectErp.getPlantAnalysis()==0){
					qr.setGrade("未定");	
				}
				if(projectErp.getPlantAnalysis()==1){
					qr.setGrade("A");	
				}
				if(projectErp.getPlantAnalysis()==2){
					qr.setGrade("B");	
				}
				if(projectErp.getPlantAnalysis()==3){
					qr.setGrade("C");	
				}
				jsonResult.setData(qr);
				jsonResult.setOk(true); 
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setMessage("查询失败");
				jsonResult.setOk(false); 
			}
			return jsonResult;
		}
	    
	    
	    /**
	     * 根据项目号查询质检报告
	     * @Title queryByProjectNo 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return String
	     */
	    @ResponseBody
		@RequestMapping("/queryByProjectNo")
		public JsonResult queryByProjectNo(HttpServletRequest request,HttpServletResponse response) {
	    	JsonResult jsonResult=new JsonResult();
			try {			 	
				String projectNo = request.getParameter("projectNo");
				Integer type = null;
				if(StringUtils.isNotBlank(request.getParameter("type"))){
					type = Integer.parseInt(request.getParameter("type"));
				}
				List<QualityReport> reports = qualityReportService.selectByProjectNoAndType(projectNo,type);
				jsonResult.setData(reports);
				jsonResult.setOk(true); 
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setMessage("查询失败");
				jsonResult.setOk(false); 
			}
			return jsonResult;
		}
	    
	    
	    
	    /**
	     * 根据id查询质检报告
	     * @Title queryByProjectNo 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return String
	     */
	    @ResponseBody
		@RequestMapping("/queryByReportId")
		public JsonResult queryByReportId(HttpServletRequest request,HttpServletResponse response) {
	    	JsonResult jsonResult=new JsonResult();
			try {			 	
				String reportId = request.getParameter("reportId");
				if(StringUtils.isNotBlank(reportId)){
					QualityReport qualityReport = qualityReportService.selectByPrimaryKey(Integer.parseInt(reportId));
					jsonResult.setData(qualityReport);
					jsonResult.setOk(true); 
				}			
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setMessage("查询失败");
				jsonResult.setOk(false); 
			}
			return jsonResult;
		}
	    
	    
	    
	    
	    /**
		 * 评论上传
		 * @Title upload 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return JsonResult
		 */
		@RequestMapping("/commentUpload")
		@ResponseBody
		public JsonResult commentUpload(HttpServletRequest request,HttpServletResponse response){  
				JsonResult jsonResult = new JsonResult();
				 try {
			 		 String projectNo = request.getParameter("projectNo");
			 		 String drawingName = request.getParameter("fileName");
			 		 String path1 = UploadAndDownloadPathUtil.getProjectImg()
			 				 + File.separator + projectNo + File.separator ; 
			 		 String path = UploadAndDownloadPathUtil.getProjectImg()
							+ File.separator + projectNo + File.separator + "comment"+ File.separator ; 
			 	
			 		 File file1 = new File(path1);
					 File file = new File(path);
					 if  (!file1.exists()  && !file1.isDirectory())      
					 {         
						 file1.mkdir();     
					 }  	    
					 if  (!file.exists()  && !file.isDirectory())      
					 {         
						 file.mkdir();     
					 }  	    
		    
				    //根据文件名获取上传文件的位置  数据库保存原始文件名称
			 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
			 		String fileName = "";
			 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
			 			 fileName = multiFileUpload.get(drawingName);			 			 
			 		} 				
				 	jsonResult.setOk(true);
				 	jsonResult.setData(fileName);
			 		return jsonResult;
				 	} catch (IllegalStateException e) {
				 		e.printStackTrace();
				 		jsonResult.setOk(false);
				 		jsonResult.setMessage("上传失败");
				 		return jsonResult;
				 	} catch (IOException e) {
				 		e.printStackTrace();
				 		jsonResult.setOk(false);
				 		jsonResult.setMessage("上传失败");
				 		return jsonResult;
				 	} 			
		 }
	    
	   
		
		
		/**
	     * 根据id查询视频 或者根据任务id查询（根据视频类型查询  3：任务视频）
	     * @Title queryByProjectNo 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return String
	     */
		@RequestMapping("/queryByVideoId")
		public String queryByVideoId(HttpServletRequest request,HttpServletResponse response) {
			List<FactoryQualityInspectionVideo> list = new ArrayList<FactoryQualityInspectionVideo>();
			String ids = request.getParameter("id");
			String taskId = request.getParameter("taskId");
			if(StringUtils.isNotBlank(taskId)){
				ProjectTask projectTask = projectTaskService.selectProjectTaskById(Integer.parseInt(taskId));
				list = factoryQualityInspectionVideoService.selectByTaskId(Integer.parseInt(taskId));
				request.setAttribute("videoList", list);
				request.setAttribute("projectTask", projectTask);
			}else{
				if(StringUtils.isNotBlank(ids)){					
					FactoryQualityInspectionVideo factoryQualityInspectionVideo = factoryQualityInspectionVideoService.selectByPrimaryKey(Integer.parseInt(ids));
					list.add(factoryQualityInspectionVideo);
					request.setAttribute("videoList", list);
				}				
			}

			return "video_share";
		}
	    
		
		
		
		/**
	     * 预览excel
	     * @Title queryByProjectNo 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return String
	     */
		@ResponseBody
		@RequestMapping("/viewExcel")
		public JsonResult viewExcel(HttpServletRequest request,HttpServletResponse response) {
			JsonResult jsonResult = new JsonResult();
			String htmlPath = "";
			try {
				String filePath = request.getParameter("filePath");
				String idStr = request.getParameter("id");
				if(StringUtils.isNotBlank(filePath)){
					filePath = URLEncoder.encode(filePath, "utf-8");
					Integer id = Integer.parseInt(idStr);
					htmlPath = Excel2Html.excel2Html("http://112.64.174.34:33168/upload/zhongwentuzhi/"+filePath,UploadAndDownloadPathUtil.getExcelHtmlPath(),id);
					jsonResult.setOk(true);
			 		jsonResult.setData(htmlPath);
				}else{
					jsonResult.setOk(false);
			 		jsonResult.setMessage("未获取到文件");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setOk(false);
		 		jsonResult.setMessage("预览失败");
			}
			return jsonResult;
		}		
		
		
		
		
		/**
	     * 更新检验计划
	     * @Title updateInspectionPlan 
	     * @Description 
	     * @param request
	     * @param response
	     * @return
	     * @return String
	     */
		@ResponseBody
		@RequestMapping("/updateInspectionPlan")
		public JsonResult updateInspectionPlan(HttpServletRequest request,HttpServletResponse response) {
			JsonResult jsonResult = new JsonResult();
			try {
				String ids = request.getParameter("id");
				String images = request.getParameter("images");
				String content = request.getParameter("content");
				List<InspectionPlan> list = new ArrayList<InspectionPlan>();
				InspectionPlan inspectionPlan = new InspectionPlan();
				if(StringUtils.isNotBlank(ids)){
					inspectionPlan.setId(Integer.parseInt(ids));
					inspectionPlan.setInspectionPic(images);
					inspectionPlan.setContent(content);
					list.add(inspectionPlan);
					inspectionPlanService.updateBatch(list);
				}
				jsonResult.setOk(true);
				
			} catch (Exception e) {				
				e.printStackTrace();
				jsonResult.setOk(false);
		 		jsonResult.setMessage("更新失败");
			}			
			return jsonResult;
		}
		
		
		
		/**
		 * 针对对象去去重
		 * @Title distinctByKey 
		 * @Description 
		 * @param keyExtractor
		 * @return
		 * @return Predicate<T>
		 */
		 private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
		        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
		 }

}
