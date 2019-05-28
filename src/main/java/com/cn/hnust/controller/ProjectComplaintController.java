package com.cn.hnust.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.cn.hnust.dao.ShippingConfirmationMapper;
import com.cn.hnust.enums.IssueReplyTypelEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.AnalysisIssue;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.ComplaintIssue;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.IssueReply;
import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.Progress;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectComplaintQuery;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityAnalysis;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.AnalysisIssueService;
import com.cn.hnust.service.ComplaintIssueService;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectCommentService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.IssueReplyService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.QualityAnalysisService;
import com.cn.hnust.service.ShippingConfirmationService;
import com.cn.hnust.service.impl.ProjectTaskServiceImpl;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.PropertiesUtils;
import com.cn.hnust.util.PropertisUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;
import com.cn.hnust.util.WebCookie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 客户投诉
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/complaint")
public class ProjectComplaintController {

	@Autowired
	private IProjectService projectService;
	@Autowired
	private ProjectComplaintService projectComplaintService;
	@Autowired
	private IProjectTaskService projectTaskService;
	@Autowired
	private IssueReplyService issueReplyService;
	@Autowired
	private ComplaintIssueService complaintIssueService;
	@Autowired
	private IQualityReportService qualityReportService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private ShippingConfirmationService shippingConfirmationService;
	@Autowired
	private IMeetingRecordService meetingRecordService;
	@Autowired
	private AnalysisIssueService analysisIssueService;
	@Autowired
	private QualityAnalysisService qualityAnalysisService;
	@Autowired
	private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	@Autowired
	private IProjectCommentService projectCommentService;
	
	
	private static final Log LOG = LogFactory.getLog(ProjectComplaintController.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
    private static final int SAMPLE = 0;           //样品
    private static final int PRODUCT = 1;          //大货
    private static final int LAST = 3;             //终期检验
    private static final int SALES = 0;            //销售
    private static final int PURCHASE = 1;         //采购
    private static final int QUALITY_LEADER = 2;  //质检总监
    private static final int PURCHASE_LEADER = 3; //采购总监
    private static final int BOSS = 4;         //老板
    private static final int PRODUCT_VIDEO=4;  //产品360视频
	/**
	 * 查询投诉列表
	 * @Title queryList 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/queryList")
	public String queryList(HttpServletRequest request,HttpServletResponse response){
		 
		try {
			String pageStr = request.getParameter("pageStr"); 
			String userIdStr = request.getParameter("userId");
			String inputKey = request.getParameter("inputKey");// 搜索词
			String pageSizeStr = request.getParameter("pageSize");
			String solve = request.getParameter("solve");
			Integer roleNo = null;                            // 判断是管理员，销售，采购
            String userName = WebCookie.getUserName(request);
            if(StringUtils.isNotBlank(userName)){
            	User user = userService.findUserByName(userName);
            	roleNo = user.getRoleNo();
            	userIdStr = user.getId()+"";
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
			ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
			projectComplaintQuery.setPageSize(pageSize);
			projectComplaintQuery.setPageNumber(pageSize * (page - 1));
			projectComplaintQuery.setRoleNo(roleNo);
			projectComplaintQuery.setUserId(userIdStr);
			projectComplaintQuery.setInputKey(inputKey);
			projectComplaintQuery.setZhijian1(userName);
			if(StringUtils.isNotBlank(solve)){
				projectComplaintQuery.setIsSolve(Integer.parseInt(solve));
			}else{
				projectComplaintQuery.setIsSolve(0);
			}
			if(StringUtils.isNotBlank(request.getParameter("projectNo"))){
				projectComplaintQuery.setInputKey(request.getParameter("projectNo"));
			}
			List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
			complaintList.forEach(c->{
				List<Comment> comments = projectCommentService.selectByComplaintId(c.getId());
				if(comments!=null&&comments.size()>0){
					c.setTechnicianReplyTime(comments.get(0).getCreateDate());
				}
			});
			//客户投诉关联任务(已取消会议投诉录入方式)
//			for (ProjectComplaint projectComplaint : complaintList) {
//				if(StringUtils.isNotBlank(projectComplaint.getMeetingNo())){
//					List<ProjectTask> tasks = projectTaskService.selectMeetingRecordTask(projectComplaint.getMeetingNo());
//					projectComplaint.setTasks(tasks);
//				}
//			}
			int totalCount = projectComplaintService.queryCount(projectComplaintQuery);
			request.setAttribute("complaintList", complaintList);
			request.setAttribute("userId", userIdStr);
			request.setAttribute("roleNo", roleNo);
			request.setAttribute("inputKey", inputKey);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("page", page);
			request.setAttribute("userName", userName);
			request.setAttribute("count", totalCount);
			request.setAttribute("solve", solve==null?0:solve);
			//计算尾页
			Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
			request.setAttribute("lastNum", lastNum);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}		 
		return "customer_complaint_B";
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 插入客户投诉信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addComplaint")
	@ResponseBody
	public JsonResult addComplaint(HttpServletRequest request,HttpServletResponse response){
		 JsonResult json =new JsonResult();
		 
		 try {
			 String projectNo=request.getParameter("projectNo");
			 String userName=request.getParameter("userName");
			 String complaintContent=request.getParameter("complaintContent");
			 String fileName = request.getParameter("fileName");
			 String filePath = request.getParameter("filePath");
			 String seriousLevel = request.getParameter("seriousLevel");
			 String issueList = request.getParameter("issueList");
			 String projectStage = request.getParameter("projectStage");
			 if(StringUtils.isNotBlank(projectNo)){
				 Project project = projectService.selectProjctDetails(projectNo);
				 if(project == null){
					 json.setOk(false);
					 json.setMessage("该项目还未录入");
					 return json;
				 }else{
					 
					 //如果不是项目组成员，提醒
					 if(!(userName.equalsIgnoreCase(project.getSellName()) || userName.equalsIgnoreCase(project.getPurchaseName()) || userName.equalsIgnoreCase(project.getZhijian1())
							 || userName.equalsIgnoreCase(project.getZhijian1()) || userName.equalsIgnoreCase(project.getZhijian1()) || userName.equalsIgnoreCase("ninazhao")
							 )){
						 json.setOk(false);
						 json.setMessage("你不是该项目组成员，请先ERP添加。");
						 return json;
					 }
					 
					 
					 ProjectComplaint projectComplaint = new ProjectComplaint();
					 projectComplaint.setProjectNo(projectNo);
					 projectComplaint.setComplaintContent(complaintContent);
					 projectComplaint.setCreatePerson(userName);
					 projectComplaint.setIsPurchase(0);
					 projectComplaint.setIsSell(0);
					 projectComplaint.setPurchaseId(project.getPurchaseId());
					 projectComplaint.setSellId(project.getEmailUserId());
					 projectComplaint.setFileName(fileName);
					 projectComplaint.setFilePath(filePath);
					 projectComplaint.setCreateTime(new Date());
					 if(StringUtils.isNotBlank(seriousLevel)){
						 projectComplaint.setSeriousLevel(Integer.parseInt(seriousLevel));
					 }
					 //项目阶段
					 if(StringUtils.isNotBlank(projectStage)){
						 projectComplaint.setProjectStage(Integer.parseInt(projectStage));
					 }
					 
					 //投诉问题列表
					 List<ComplaintIssue> complaintIssues = null;
					 ObjectMapper objectMapper = new ObjectMapper();
					 JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ComplaintIssue.class);
					 complaintIssues = objectMapper.readValue(issueList,javaType);				 
					 
					 //批量更新问题标签表 complaint_id
					 String issues = request.getParameter("issues");
					 List<AnalysisIssue> analysisIssueList = null;
					 if(StringUtils.isNotBlank(issues)){				
							ObjectMapper objectMapper1 = new ObjectMapper();
							JavaType javaType1 = objectMapper1.getTypeFactory().constructParametricType(List.class, AnalysisIssue.class);
							analysisIssueList = objectMapper.readValue(issues,javaType1);			 				 
					 }
					 
					 projectComplaintService.insertSelective(projectComplaint,complaintIssues,analysisIssueList);
					 json.setOk(true);
					 json.setData(projectComplaint.getId());
					 return json;
				 }
			 }else{
				 json.setOk(false);
				 json.setMessage("项目号不能为空");
				 return json;
			 }
         	
		} catch (Exception e) {
			json.setOk(false);
			json.setMessage("客户投诉添加错误");
			e.printStackTrace();
		}
		return json;
	}
	
	
	
	/**
	 * 更新项目投诉
	 * @Title showUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping("/updateComplaint")
	@ResponseBody
	public JsonResult updateComplaint(HttpServletRequest request,HttpServletResponse response){
		
		 JsonResult json =new JsonResult();
		 String id = request.getParameter("id");
		 //更新类型
		 //1:更新投诉内容 （杨工操作）
		 //2:更新成本
		 //3:采购确认
		 //4:跟单确认		 
		 String type = request.getParameter("type");
		 try {
			if(StringUtils.isNotBlank(id)){
				 ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
				 if("1".equals(type)){
					 String complaintDate = request.getParameter("complaintDate");
					 String complaintContent=request.getParameter("complaintContent");
					 String fileName = request.getParameter("fileName");
					 String filePath = request.getParameter("filePath");
					 projectComplaint.setComplaintDate(java.sql.Date.valueOf(complaintDate));
					 projectComplaint.setComplaintContent(complaintContent);	
					 projectComplaint.setFileName(fileName);
					 projectComplaint.setFilePath(filePath);
				 }else if("2".equals(type)){
					 String costAnalysis = request.getParameter("costAnalysis");
					 projectComplaint.setCostAnalysis(costAnalysis); 		
				 }else if("3".equals(type)){
					 String userId=request.getParameter("userId");
					 String purchaseContent=request.getParameter("purchaseContent");
					 String isPurchase = request.getParameter("isPurchase");
					 projectComplaint.setPurchaseId(Integer.parseInt(userId));
					 projectComplaint.setPurchaseContent(purchaseContent);
					 projectComplaint.setIsPurchase(Integer.parseInt(isPurchase));
					 projectComplaint.setPurchaseConfirmDate(new Date());
				 }else if("4".equals(type)){
					 String userId=request.getParameter("userId");
					 String sellContent=request.getParameter("sellContent");
					 String isSell = request.getParameter("isSell");
					 projectComplaint.setSellId(Integer.parseInt(userId));
					 projectComplaint.setSellContent(sellContent);
					 projectComplaint.setIsSell(Integer.parseInt(isSell));	
					 projectComplaint.setSellConfirmDate(new Date());
				 }else if("5".equals(type)){
					 projectComplaint.setPurchaseLeaderConfirmDate(new Date());
					 projectComplaint.setPurchaseLeaderConfirm(1);
				 }else if("6".equals(type)){
					 projectComplaint.setInspectionLeaderConfirmDate(new Date());
					 projectComplaint.setInspectionLeaderConfirm(1);
				 }
				 projectComplaintService.updateByPrimaryKeySelective(projectComplaint);	
				 
				 json.setOk(true);
				 json.setMessage("修改成功");
			 }else{
				 json.setOk(false);
				 json.setMessage("修改id不能为空");
				 return json;
			 }
		} catch (NumberFormatException e) {
			json.setOk(false);
			json.setMessage("修改失败");
			e.printStackTrace();
		}		 
		 return json;
		
	}
	
	/**
	 * 下载
	 * @Title fileDownload 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @return ResponseEntity<byte[]>
	 */
    @RequestMapping(value = "download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
    	if(StringUtils.isNotBlank(request.getParameter("id"))){
    		  Integer id = Integer.parseInt(request.getParameter("id"));
    		  ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(id);
    		  ResponseEntity<byte[]> download = OperationFileUtil.download(projectComplaint.getFilePath(), projectComplaint.getFileName());
		      return download;
	    	}else{
	    		throw new RuntimeException("未获取到路径");
	    	}	       
   }
    
    
    
    
    
    /**
	 * 查询投诉详情
	 * @Title queryList 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/queryComplaint")
	public String queryComplaint(HttpServletRequest request,HttpServletResponse response){
		 
		try {
			String id = request.getParameter("id");      //项目号
			String type = request.getParameter("type");  //判断是否是微信分享发出
			Integer existPurchaseReply = 0;              //是否存在采购回复0 否 1 是
			Integer existInspectionReply = 0;            //是否存在质检回复0 否 1 是
			Integer existTechnicianReply = 0;            //是否存在技术回复0 否 1 是
			Integer existRectificationReply = 0;         //是否存在整改回复0 否 1 是
			//查询cookie中用户
			String userName = WebCookie.getUserName(request);
			if(StringUtils.isNotBlank(userName)){
				User user = userService.selectUserByName(userName);
				request.setAttribute("user", user);
			}
			
			
			//查询技术回复
		    List<Comment> comments = projectCommentService.selectByComplaintId(Integer.parseInt(id));
		    if(comments!=null && comments.size()>0){
		    	existTechnicianReply = 1;
		    }
			//邮件正文内容
			//n个问题点+客户问题正文
			StringBuffer mailBody = new StringBuffer();
			int index = 0;
			if(StringUtils.isNotBlank(request.getParameter("id"))){
				ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
				//投诉问题
				List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));	
				for (ComplaintIssue complaintIssue : issueList) {
					List<IssueReply> replyList = complaintIssue.getReplyList();
					for (IssueReply issueReply : replyList) {
						//判断是否存在各类型回复消息
						if(issueReply.getReplyType() == IssueReplyTypelEnum.PURCHASE_REPLY.getCode()){
							existPurchaseReply = 1;
						}
						if(issueReply.getReplyType() == IssueReplyTypelEnum.INSPECTION_REPLY.getCode()){
							existInspectionReply = 1;
						}
						if(issueReply.getReplyType() == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()){
							existTechnicianReply = 1;
						}
						if(issueReply.getReplyType() == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()){
							existRectificationReply = 1;
						}
					}
					index++;
					mailBody.append("问题"+index+":");
					mailBody.append(complaintIssue.getIssue());
				}
				mailBody.append(projectComplaint.getComplaintContent());
				
				//质检报告
				List<QualityReport> qualityReports = qualityReportService.selectByProjectNo(projectComplaint.getProjectNo());
				if (qualityReports != null && qualityReports.size() > 0) {
					for (QualityReport qr : qualityReports) {
						StringBuilder detailView = new StringBuilder("[");
						detailView.append(qr.getProjectNo()).append("]");
						detailView.append(
								QualityTypeEnum.getEnum(qr.getType()).getValue())
								.append(",");
						detailView.append(
								QualityStatusEnum.getEnum(qr.getStatus())
										.getValue()).append(",[");
						detailView.append(qr.getUser()).append("/")
								.append(DateFormat.date2String(qr.getCreatetime())).append("]");
						qr.setDetailView(detailView.toString());
					}

				}
				//项目详情
				Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
				//查询检验计划
				List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(projectComplaint.getProjectNo());
				//查询项目相关任务
				List<ProjectTask> tasks = projectTaskService.selectByProjectNo(projectComplaint.getProjectNo());
				//查询质量、技术关键词
				List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByComplaintId(Integer.parseInt(id));
				QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(projectComplaint.getProjectNo());				 
				//查询项目所有工厂
				List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectComplaint.getProjectNo());
				//查询问题出现的次数				
				for (AnalysisIssue analysisIssue : analysisIssueList) {
					if(StringUtils.isNotBlank(analysisIssue.getIssue())){
						List<String> issues = qualityAnalysisService.selectByIssue(analysisIssue.getIssue());
						analysisIssue.setOccurrenceNum(issues != null ? issues.size() : 0);
					}
				}
				//查询问题
				List<AnalysisIssue> list = analysisIssueService.selectTop10(null);
			
				
				request.setAttribute("projectComplaint", projectComplaint);
				request.setAttribute("issueList", issueList);
				request.setAttribute("qualityReports", qualityReports);
				request.setAttribute("project", project);
				request.setAttribute("existPurchaseReply", existPurchaseReply);
				request.setAttribute("existInspectionReply", existInspectionReply);
				request.setAttribute("existTechnicianReply", existTechnicianReply);
				request.setAttribute("existRectificationReply", existRectificationReply);
				request.setAttribute("type", type);
				request.setAttribute("mailBody", mailBody);
				request.setAttribute("inspectionPlanList", inspectionPlanList);
				request.setAttribute("tasks", tasks);
				request.setAttribute("analysisIssueList", analysisIssueList);
				request.setAttribute("qualityAnalysis", qualityAnalysis);
				request.setAttribute("factoryList", factoryList);
				request.setAttribute("list", list);
				request.setAttribute("comments", comments);
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		 
		return "customer_complaint_B_detail";
	}
	
	
	
	
	
	  /**
		 * 跳转采购录入信息页面
		 * @Title purchaseReply 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@RequestMapping("/purchaseReply")
		public String purchaseReply(HttpServletRequest request,HttpServletResponse response){
			 
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isNotBlank(userName)){
					User user = userService.selectUserByName(userName);
					request.setAttribute("user", user);
				}
				
				
				if(StringUtils.isNotBlank(request.getParameter("id"))){
					ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
					//投诉问题
					List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));	
					//项目详情
					Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
					
					request.setAttribute("projectComplaint", projectComplaint);
					request.setAttribute("issueList", issueList);
					request.setAttribute("project", project);
				}
			
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	 
			return "purchase_entry_reply";
		}
	
		 /**
		 * 跳转质检录入信息页面
		 * @Title inspectionReply 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@RequestMapping("/inspectionReply")
		public String inspectionReply(HttpServletRequest request,HttpServletResponse response){
			 
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isNotBlank(userName)){
					User user = userService.selectUserByName(userName);
					request.setAttribute("user", user);
				}
				
				
				if(StringUtils.isNotBlank(request.getParameter("id"))){
					ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
					//投诉问题
					List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));	
					//项目详情
					Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
					
					request.setAttribute("projectComplaint", projectComplaint);
					request.setAttribute("issueList", issueList);
					request.setAttribute("project", project);
				}
			
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	 
			return "quality_reply";
		}
		
		
		 /**
		 * 跳转技术录入信息页面
		 * @Title technicianReply 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@RequestMapping("/technicianReply")
		public String technicianReply(HttpServletRequest request,HttpServletResponse response){
			 
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isNotBlank(userName)){
					User user = userService.selectUserByName(userName);
					request.setAttribute("user", user);
				}
				
				//投诉问题id
				Integer issueId = null;
				if(StringUtils.isNotBlank(request.getParameter("id"))){
					ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
					//投诉问题
					List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
					if(issueList!=null && issueList.size()>0){
						issueId = issueList.get(0).getId();
					}
					//项目详情
					Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
					//查询技术回复
				    List<Comment> comments = projectCommentService.selectByComplaintId(Integer.parseInt(id));
					
					request.setAttribute("projectComplaint", projectComplaint);
					request.setAttribute("issueId", issueId);
					request.setAttribute("project", project);
					request.setAttribute("comments", comments);
					request.setAttribute("userName", userName);
				}
			
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	 
			return "technology_entry_reply";
		}
		/**
		 * 跳转整改结果录入信息页面
		 * @Title remediaReply 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@RequestMapping("/remediaReply")
		public String remediaReply(HttpServletRequest request,HttpServletResponse response){
			
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isNotBlank(userName)){
					User user = userService.selectUserByName(userName);
					request.setAttribute("user", user);
				}
				
				if(StringUtils.isNotBlank(request.getParameter("id"))){
					ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
					//投诉问题
					List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));					
					//项目详情
					Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
					
					request.setAttribute("projectComplaint", projectComplaint);
					request.setAttribute("issueList", issueList);
					request.setAttribute("project", project);
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	 
			return "remedia_entry_reply";
		}
		
		
		
		/**
		 * 删除投诉表
		 * @Title delete 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@ResponseBody
		@RequestMapping("/delete")
		public JsonResult delete(HttpServletRequest request,HttpServletResponse response){
			
			JsonResult jsonResult = new JsonResult();
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
				}				
				if(StringUtils.isNotBlank(id)){
					ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
					if(userName.equals(projectComplaint.getCreatePerson()) || userName.equals("ninazhao")){
						projectComplaintService.deleteByPrimaryKey(Integer.parseInt(id));
						jsonResult.setOk(true);
						jsonResult.setMessage("已删除");
					}else{
						jsonResult.setOk(false);
						jsonResult.setMessage("你没有权限删除");
					}
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage("删除失败");
				LOG.error("====客户投诉删除失败=====");
		
			}	 
			return jsonResult;
		}
		
		
		
		/**
		 * 插入回复信息
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/addReply")
		@ResponseBody
		public JsonResult addReply(HttpServletRequest request,HttpServletResponse response){
			 JsonResult json =new JsonResult();
			 
			 try {
				 //投诉表id
				 String complaintId = request.getParameter("complaintId");
				 //回复json集合
				 String replyList = request.getParameter("replyList");
				 //成本分析
				 String costAnalysis = request.getParameter("costAnalysis");
				 //采购原因分析
				 String purchaseContent = request.getParameter("purchaseContent");
				 //采购填预计完成日期
				 String predictCompleteTime = request.getParameter("predictCompleteTime");
				 //回复人类型（1：采购回复  2：质检回复 3：技术回复 4：整改回复）
				 String replyType = request.getParameter("replyType");
				 //质检原因分析
				 String inspectionContent = request.getParameter("inspectionContent");
				 //用户roleNo
				 String roleNo = request.getParameter("roleNo");
				 
			     //查询cookie中用户
				 String userName = WebCookie.getUserName(request);
				 if(StringUtils.isBlank(complaintId)){
					 json.setOk(false);
					 json.setMessage("投诉获取失败");
					 return json;
				 }
				 ProjectComplaint projectComplaint = new ProjectComplaint();
				 projectComplaint.setId(Integer.parseInt(complaintId));
				
			     //根据回复类型判断		 
				 Integer type = null;
				 if(StringUtils.isNotBlank(replyType)){
					 type = Integer.parseInt(replyType);
					 if(type == IssueReplyTypelEnum.PURCHASE_REPLY.getCode()){
						 projectComplaint.setCostAnalysis(costAnalysis);
						 projectComplaint.setPurchaseContent(purchaseContent);
						 projectComplaint.setPredictCompleteTime(DateUtil.StrToDate(predictCompleteTime));
						 projectComplaint.setPurchaseConfirmDate(new Date());
					 }else if(type == IssueReplyTypelEnum.INSPECTION_REPLY.getCode()){
						 projectComplaint.setInspectionContent(inspectionContent);
						 projectComplaint.setZhijianReplyTime(new Date());						 
					 }else if(type == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()){
						 projectComplaint.setTechnicianReplyTime(new Date());						 
					 }else if(type == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()){
							 projectComplaint.setCompleteTime(new Date());
							 if("9".equals(roleNo)){
								 projectComplaint.setRectificationZhijianReply(userName);
								 projectComplaint.setRectificationZhijianTime(new Date());
							 }else{
								 projectComplaint.setRectificationSellPurchaseReply(userName);
								 projectComplaint.setRectificationSellPurchaseTime(new Date());
							 }
							 projectComplaintService.updateByPrimaryKeySelective(projectComplaint);					 
					 }
				 }
				 if(StringUtils.isNotBlank(userName)){						 
					 //投诉问题列表
					 List<IssueReply> issueReplyList = null;
					 ObjectMapper objectMapper = new ObjectMapper();
					 JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, IssueReply.class);
					 issueReplyList = objectMapper.readValue(replyList,javaType);	
						 for (IssueReply issueReply : issueReplyList) {
							 //根据id查询整改是否已回复，当已存在，则进行更新  不存在，则插入
							IssueReply reply = issueReplyService.queryReply(issueReply.getIssueId(),type);
							if(reply!=null){
								 if(type == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()){
									reply.setQualification(issueReply.getQualification());
									reply.setReplyContent(issueReply.getReplyContent());
									reply.setReplyTime(new Date());										
								 }else if(type == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()){
								    reply.setFileName(issueReply.getFileName());
								    reply.setFilePath(issueReply.getFilePath());
								    reply.setReplyTime(new Date());
								 }else{
									 reply.setReplyContent(issueReply.getReplyContent());
									 reply.setReplyTime(new Date());
								 }
								 issueReplyService.updateByPrimaryKeySelective(reply);
							}else{
								issueReplyService.insertSelective(issueReply);
							}
						 }
						 projectComplaintService.updateByPrimaryKeySelective(projectComplaint);
				
				 }else{
					 json.setOk(false);
					 json.setMessage("请先登录");
					 return json;
				 }
	         	
			} catch (Exception e) {
				json.setOk(false);
				json.setMessage("保存失败");
				e.printStackTrace();
			}
			return json;
		}
		
		
		
		
		
		
		/**
		 * 技术上传报告下载
		 * @Title fileDownload 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws UnsupportedEncodingException
		 * @throws IOException
		 * @return ResponseEntity<byte[]>
		 */
	    @RequestMapping(value = "technicianFile")
	    public ResponseEntity<byte[]> technicianFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
	    	if(StringUtils.isNotBlank(request.getParameter("id"))){
	    		  Integer id = Integer.parseInt(request.getParameter("id"));
	    		  IssueReply issueReply = issueReplyService.selectByPrimaryKey(id);
	    		  ResponseEntity<byte[]> download = OperationFileUtil.download(issueReply.getFilePath(), issueReply.getFileName());
			      return download;
		    	}else{
		    		throw new RuntimeException("未获取到路径");
		    	}	       
	   }
	    
	    
	    
	    
		/**
		 * 上传投诉问题照片
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/uploadIssuePic")
		@ResponseBody
		public JsonResult uploadIssuePic(HttpServletRequest request,HttpServletResponse response) {
			 JsonResult jsonResult = new JsonResult();
		try {
			 String projectNo = request.getParameter("projectNo");
			 String path = UploadAndDownloadPathUtil.getComplaintPath()+ File.separator+ projectNo;
			 File file = new File(path);
			 if  (!file.exists()  && !file .isDirectory())       {         
					file .mkdir();     
			 }  	
			 //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
			 String picNames="";
			 Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path+File.separator);
			 if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
					 Set<String> keySet = multiFileUpload.keySet();
					   for (String key : keySet) {	
						    String pic = multiFileUpload.get(key);  
					        picNames += pic + ",";
					   }
					   if(picNames.endsWith(",")){
						  picNames = picNames.substring(0,picNames.length()-1);
					   }	
			   }
			  jsonResult.setData(picNames);
			  jsonResult.setOk(true);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
			}
			return jsonResult;
		}
		
		
		/** 
		  * 图片文件上传 (BASE64)
		  */
		 @ResponseBody
		 @RequestMapping("fileUploadPicture") 
		 public JsonResult fileUploadPicture(String imgdata, HttpServletRequest request) { 
			 
		    JsonResult jsonResult = new JsonResult();
		    BASE64Decoder decoder = new BASE64Decoder();  
		    FileOutputStream fos = null;
		  try { 		       
		   String fileName = request.getParameter("fileName"); 
			//获取相对路径
		   String path = UploadAndDownloadPathUtil.getComplaintPath();
		   File dirFile = new File(path);  
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}	
	       //新的图片名称  
	       String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));  
		   String imgPath=path+newFileName; 
		   // new一个文件对象用来保存图片，默认保存当前工程根目录 
		   File imageFile = new File(imgPath); 
		   // 创建输出流 
		   fos = new FileOutputStream(imageFile); 
		   // 获得一个图片文件流，我这里是从flex中传过来的 
		   byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//解码 
		   for (int i = 0; i < result.length; ++i) { 
			    if (result[i] < 0) {// 调整异常数据 
			    result[i] += 256; 
			    } 
		   } 
		   fos.write(result); 
		   fos.flush();

		   jsonResult.setData(newFileName);
		   jsonResult.setMessage("上传成功"); 
		   jsonResult.setOk(true);
		   
		  } catch (Exception e1) { 
			LOG.error("上传图片失败", e1); 
		    jsonResult.setMessage("上传失败"); 
			jsonResult.setOk(false);
		  }finally{ 
				try {
					fos.flush();
					fos.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				} 		 	    
		  } 
		  return jsonResult;
		 }
		
		
		/**
		 * 用于投诉上传进度条显示
		 * @Title initCreateInfo 
		 * @Description
		 * @param session
		 * @return
		 * @return Progress
		 */
		@RequestMapping(value = "/progressStatus", method = RequestMethod.POST )
		@ResponseBody
		public Progress initCreateInfo(HttpSession session) {
					
			Progress status = (Progress) session.getAttribute("status");
			if(status==null){
				return status;
			}
			return status;
		}
		
		
		
		
		/**
		 * 查询出货单列表
		 * @Title queryShippingList 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@RequestMapping("/queryShippingList")
		public String queryShippingList(HttpServletRequest request,HttpServletResponse response){
			 
			try {
				String pageStr = request.getParameter("pageStr"); 
				String inputKey = request.getParameter("inputKey");// 搜索词
				String pageSizeStr = request.getParameter("pageSize");
				String sampleOrProductStr = request.getParameter("sampleOrProduct");
				String isCompleteStr = request.getParameter("isComplete");
				Integer roleNo = null;                            // 判断是管理员，销售，采购
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
				ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
				shippingConfirmationQuery.setPageSize(pageSize);
				shippingConfirmationQuery.setPageNumber(pageSize * (page - 1));
				shippingConfirmationQuery.setRoleNo(roleNo);
				shippingConfirmationQuery.setUserName(userName);
				shippingConfirmationQuery.setInputKey(inputKey);
				//判断样品还是大货
				int sampleOrProduct = -1;
				//判断是否完成
				int isComplete = 0;
				if(StringUtils.isNotBlank(sampleOrProductStr)){
					sampleOrProduct = Integer.parseInt(sampleOrProductStr);
				}
				if(StringUtils.isNotBlank(isCompleteStr)){
					isComplete = Integer.parseInt(isCompleteStr);
				}
				shippingConfirmationQuery.setSampleOrProduct(sampleOrProduct);
				shippingConfirmationQuery.setIsComplete(isComplete);

				List<ShippingConfirmation> shippings = shippingConfirmationService.selectByProjectNo(shippingConfirmationQuery);
				for (ShippingConfirmation shippingConfirmation : shippings) {
					
					
					//查询投诉(该项目历史投诉)
					 String projectNo = shippingConfirmation.getProjectNo();
					 if(projectNo.contains("-")){
						 projectNo = projectNo.split("-")[0];
					 }
					 //根据投诉历史是否完成，判断是否能够出货
					 //查询该项目所有历史投诉
					 ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
					 projectComplaintQuery.setInputKey(projectNo);
					 projectComplaintQuery.setRoleNo(100);
					 projectComplaintQuery.setPageNumber(-1);
					 List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
					 Boolean isComplaintComplete = true;
					 for (ProjectComplaint projectComplaint2 : complaintList) {
						 Date completeTime = projectComplaint2.getCompleteTime();
						 if(completeTime == null){
							 isComplaintComplete = false;
						 }
					 }
					 //查询是否终检没问题
					 Boolean isSampleNoProblem = false;
					 Boolean isProductNoProblem = false;
					 
					 List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
					 for (QualityReport qualityReport : reports) {
						 if(qualityReport.getType() == SAMPLE){	 
							 //如果是样品出货，查询出货证明
							 if(shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() ==  SAMPLE && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
								 isSampleNoProblem = true;
//								 shippingConfirmation.setsa
							 }
						 }
						//如果是终期检验
						 if(qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() ==  PRODUCT && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
							 isProductNoProblem = true;
						 }
					 }

					 //查询产品360度视频
					 List<FactoryQualityInspectionVideo> videoList = factoryQualityInspectionVideoService.selectByProjectNoAndType(projectNo,PRODUCT_VIDEO);
					//判断是否允许签名
					 Boolean isSign = false;
					 if(shippingConfirmation.getFirstPerson() != null && shippingConfirmation.getSecondPerson() != null 
							 && shippingConfirmation.getThirdPerson() != null && shippingConfirmation.getFourthPerson() != null 
							 && isComplaintComplete == true 						
							 ){	
							 if(shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement())))){
								 isSign = true;
							 }
							 if((shippingConfirmation.getSampleOrProduct() == 0 && videoList!=null && videoList.size() > 0)){							
								 if((shippingConfirmation.getSampleFileName() != null) || isSampleNoProblem == true){
									 isSign = true;
								 }
							 }
					   }
					 					
					 //针对之前的出货单，不进行拦截（id 239）
					 if(shippingConfirmation.getId()<=239){
						 isSign = true;
					 }
					 shippingConfirmation.setIsSign(isSign);
				}
				int totalCount = shippingConfirmationService.count(shippingConfirmationQuery);
				request.setAttribute("shippings", shippings);
				request.setAttribute("inputKey", inputKey);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("page", page);
				request.setAttribute("userName", userName);
				request.setAttribute("count", totalCount);
				request.setAttribute("sampleOrProduct", sampleOrProduct);
				request.setAttribute("isComplete", isComplete);
				request.setAttribute("isCompleteStr", isCompleteStr);
				//计算尾页
				Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
				request.setAttribute("lastNum", lastNum);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}		 
			return "confirm_list";
		}
		
		
		
		/**
		 * 保存出运信息
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/addShippingConfirmation")
		@ResponseBody
		public JsonResult addShippingConfirmation(HttpServletRequest request,HttpServletResponse response){
			 JsonResult json =new JsonResult();			 
			 try {
				 String projectNo=request.getParameter("projectNo");
				 String userName = WebCookie.getUserName(request);
				 if(StringUtils.isBlank(userName)){
					 json.setOk(false);
					 json.setMessage("您还未登录");
					 return json;
	             }
				 
				 if(StringUtils.isNotBlank(projectNo)){
					 Project project = projectService.selectProjctDetails(projectNo);
					 if(project == null){
						 json.setOk(false);
						 json.setMessage("该项目还未录入");
						 return json;
					 }else{
						 ShippingConfirmation shippingConfirmation = new ShippingConfirmation();
						 shippingConfirmation.setProjectNo(projectNo);
						 shippingConfirmation.setCreatePerson(userName);
						 shippingConfirmationService.insertSelective(shippingConfirmation);
						 json.setOk(true);
						 json.setData(shippingConfirmation.getId());
						 return json;
					 }
				 }else{
					 json.setOk(false);
					 json.setMessage("项目号不能为空");
					 return json;
				 }
	         	
			} catch (Exception e) {
				json.setOk(false);
				json.setMessage("出货单添加错误");
				e.printStackTrace();
			}
			return json;
		}
		
		
		
		/**
		 * 跳转出运信息页面
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/toAdd/{type}")
		public String toAdd(HttpServletRequest request,@PathVariable Integer type){	 
			 String userName = WebCookie.getUserName(request);
			 if(StringUtils.isBlank(userName)){
				 return "redirect:/index.jsp";
             }	
			 //根据id查询
			 if(StringUtils.isNotBlank(request.getParameter("id"))){
				 ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
				 Project project = projectService.selectProjectByProjectNo(shippingConfirmation.getProjectNo());
				 request.setAttribute("shippingConfirmation", shippingConfirmation);
				 request.setAttribute("project", project);
		         //质检录入页面查询质检报告
				 if(type == 2){
					 List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
					 request.setAttribute("reports", reports); 
				 }
			     if(type == 3){
			    	 MeetingRecord meetingRecord = new MeetingRecord();
					 meetingRecord.setProjectNo(shippingConfirmation.getProjectNo());
					 meetingRecord.setMeetingName("质量分析会");
					 List<MeetingRecord> meetings = meetingRecordService.selectMeetings(meetingRecord);
					 request.setAttribute("meetings", meetings); 
			     }
				
			 }			 			
			 if(type == 1){
				 return "confirm_list_first"; 
			 }else if(type == 2){
				 return "confirm_list_second";
			 }else if(type == 3){
				 return "confirm_list_three";
			 }else if(type == 4){
				 return "confirm_list_four";
			 }else{
				 return "confirm_list_share";
			 }
		}
		
		
		/**
		 * 保存出运信息
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/addFirst")
		public JsonResult addFirst(HttpServletRequest request,HttpServletResponse response){	
			JsonResult json =new JsonResult();		
			try {
				String userName = WebCookie.getUserName(request);
				 if(StringUtils.isBlank(userName)){
					 json.setOk(false);
					 json.setMessage("您还未登录");
					 return json;
				 }
				//根据id查询
				if(StringUtils.isNotBlank(request.getParameter("id"))){
					
					String customerName = request.getParameter("customerName"); //客户名
					String productName = request.getParameter("productName");   //产品名										
					ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));						
					if(StringUtils.isNotBlank(customerName)){
						shippingConfirmation.setCustomerName(customerName);
					}
					if(StringUtils.isNotBlank(productName)){
						shippingConfirmation.setProductName(productName);
					}										
					if(StringUtils.isNotBlank(request.getParameter("sampleOrProduct"))){
						shippingConfirmation.setSampleOrProduct(Integer.parseInt(request.getParameter("sampleOrProduct")));
					}
					if(StringUtils.isNotBlank(request.getParameter("isPlastic"))){
						shippingConfirmation.setIsPlastic(Integer.parseInt(request.getParameter("isPlastic")));
					}
					if(StringUtils.isNotBlank(request.getParameter("isBossConfirm"))){
						shippingConfirmation.setIsBossConfirm(Integer.parseInt(request.getParameter("isBossConfirm")));
					}
					if(StringUtils.isNotBlank(request.getParameter("shippingWay"))){
						shippingConfirmation.setShippingWay(request.getParameter("shippingWay"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("checkQty"))){
						shippingConfirmation.setCheckQty(request.getParameter("checkQty"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("orders"))){
						shippingConfirmation.setOrders(request.getParameter("orders"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("boxNumber"))){
						shippingConfirmation.setBoxNumber(request.getParameter("boxNumber"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("openQty"))){
						shippingConfirmation.setOpenQty(request.getParameter("openQty"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("spendTime"))){
						shippingConfirmation.setSpendTime(request.getParameter("spendTime"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("checkConclusion"))){
						shippingConfirmation.setCheckConclusion(request.getParameter("checkConclusion"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("noExecuted"))){
						shippingConfirmation.setNoExecuted(request.getParameter("noExecuted"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("concessiveAccept"))){
						shippingConfirmation.setConcessiveAccept(request.getParameter("concessiveAccept"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("meetingConclusion"))){
						shippingConfirmation.setMeetingConclusion(request.getParameter("meetingConclusion"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("notPaid"))){
						shippingConfirmation.setNotPaid(request.getParameter("notPaid"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("scale"))){
						shippingConfirmation.setScale(request.getParameter("scale"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("customerConfirmWay"))){
						shippingConfirmation.setCustomerConfirmWay(request.getParameter("customerConfirmWay"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("isQualityReportEn"))){
						shippingConfirmation.setIsQualityReportEn(Integer.parseInt(request.getParameter("isQualityReportEn")));
					}	
					if(StringUtils.isNotBlank(request.getParameter("drawbackProduct"))){
						shippingConfirmation.setDrawbackProduct(request.getParameter("drawbackProduct"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("drawbackRate"))){
						shippingConfirmation.setDrawbackRate(request.getParameter("drawbackRate"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("shippingFee"))){
						shippingConfirmation.setShippingFee(request.getParameter("shippingFee"));
					}	
					if(StringUtils.isNotBlank(request.getParameter("salesWorry"))){
						shippingConfirmation.setSalesWorry(request.getParameter("salesWorry"));
					}	
					//根据当前步骤更新
					if(StringUtils.isNotBlank(request.getParameter("step"))){
						String step = request.getParameter("step");
						switch (step) {
							case "first":
								shippingConfirmation.setFirstPerson(userName);
								shippingConfirmation.setFirstTime(new Date());
								break;
							case "second":
								shippingConfirmation.setSecondPerson(userName);
								shippingConfirmation.setSecondTime(new Date());
								break;		
							case "third":
								shippingConfirmation.setThirdPerson(userName);
								shippingConfirmation.setThirdTime(new Date());
								break;		
							case "fourth":
								shippingConfirmation.setFourthPerson(userName);
								shippingConfirmation.setFourthTime(new Date());
								break;						
							default:
								break;
						}
					}		
					
					
					//更新签名数据
					if(StringUtils.isNotBlank(request.getParameter("type"))){
						Integer type = Integer.parseInt(request.getParameter("type"));
						switch (type) {
							case SALES:
								shippingConfirmation.setSalesConfirm(userName);
								shippingConfirmation.setSalesConfirmTime(new Date());
								break;
							case PURCHASE:
								shippingConfirmation.setPurchaseConfirm(userName);
								shippingConfirmation.setPurchaseConfirmTime(new Date());
								break;		
							case QUALITY_LEADER:
								shippingConfirmation.setQualityLeaderConfirm(userName);
								shippingConfirmation.setQualityLeaderConfirmTime(new Date());
								break;		
							case PURCHASE_LEADER:
								shippingConfirmation.setPurchaseLeaderConfirm(userName);
								shippingConfirmation.setPurchaseLeaderConfirmTime(new Date());
								break;						
							case BOSS:
								shippingConfirmation.setBossConfirm(userName);
								shippingConfirmation.setBossConfirmTime(new Date());
								break;						
							default:
								break;
						}
					}					
					
					
					
					shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation);
					json.setOk(true);
					json.setMessage("保存成功");
				}
			} catch (NumberFormatException e) {				
				e.printStackTrace();
				json.setOk(false);
				json.setMessage("出货单保存失败");
			} catch (ParseException e) {
				e.printStackTrace();
				json.setOk(false);
				json.setMessage("出货单保存失败");
			}			 
			return json;
		}
		
		
		/**
		 * 跳转出运信息页面
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/detail")
		public String detail(HttpServletRequest request){	 
			 String userName = WebCookie.getUserName(request);
//			 if(StringUtils.isBlank(userName)){
//				 return "redirect:/index.jsp";
//             }	
			 try {
				//根据id查询
				 if(StringUtils.isNotBlank(request.getParameter("id"))){
					 ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
					 request.setAttribute("shippingConfirmation", shippingConfirmation);	
					 				 
					    //判断当前出货次数
						Integer count = shippingConfirmationService.selectCountByProjectNoAndType(shippingConfirmation.getProjectNo(),PRODUCT);
						//获取项目金额
						Double amount = 0.00;
						String projectAmount = shippingConfirmation.getProjectAmount();
						if(projectAmount!=null){
							String regEx="[^0-9]";  
							Pattern p = Pattern.compile(regEx);  
							Matcher m = p.matcher(projectAmount);
							projectAmount = m.replaceAll("").trim();
							amount = Double.parseDouble(projectAmount);
						}
						 //判断是否需要老板签字
						 //1、非塑料类样品 出货   2、金额大于 1万美元的第一次 大货出货
						 if((shippingConfirmation.getIsPlastic() == 0 && shippingConfirmation.getSampleOrProduct() == SAMPLE) || (shippingConfirmation.getSampleOrProduct() == PRODUCT && count == 1 && amount > 1)){
							 request.setAttribute("bossConfirm", true);	
						 }
						 
						 //查询项目
						 Project project = projectService.selectProjectByProjectNo(shippingConfirmation.getProjectNo());
						 //查询投诉(该项目历史投诉)
						 String projectNo = shippingConfirmation.getProjectNo();
						 if(projectNo.contains("-")){
							 projectNo = projectNo.split("-")[0];
						 }
						 //根据投诉历史是否完成，判断是否能够出货
						 //查询该项目所有历史投诉
						 ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
						 projectComplaintQuery.setInputKey(projectNo);
						 projectComplaintQuery.setRoleNo(100);
						 projectComplaintQuery.setPageNumber(-1);
						 List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
						 Boolean isComplaintComplete = true;
						 for (ProjectComplaint projectComplaint2 : complaintList) {
							 Date completeTime = projectComplaint2.getCompleteTime();
							 if(completeTime == null){
								 isComplaintComplete = false;
							 }
						 }
						 
						 //查询项目相关任务
						 List<ProjectTask> tasks = projectTaskService.selectByProjectNo(shippingConfirmation.getProjectNo());
						 //查询质量、技术关键词
						 List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(shippingConfirmation.getProjectNo(),1);
						 QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(shippingConfirmation.getProjectNo());
						 //查询质检报告
						 Boolean isSampleCheck = false;
						 //查询是否终检没问题
						 Boolean isSampleNoProblem = false;
						 Boolean isProductNoProblem = false;
						 //终检有问题，是否上传图纸、视频
						 String productFileName = null;
						 String operateExplain = null;
						 
						 List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
						 for (QualityReport qualityReport : reports) {
							 if(qualityReport.getType() == SAMPLE){
								 isSampleCheck = true;		 
								 //如果是样品出货，查询出货证明
								 if(shippingConfirmation.getSampleOrProduct() ==  SAMPLE && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
									 isSampleNoProblem = true;
								 }
							 }
							//如果是终期检验
							 if(qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() ==  PRODUCT && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
								 isProductNoProblem = true;
							 }
							 //如果是终期检验
							 if(qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() ==  PRODUCT && qualityReport.getStatus() == 2){
								 operateExplain = qualityReport.getOperateExplain();
								 productFileName = qualityReport.getProductFileName();								
							 }
							 
							 
							 StringBuilder detailView = new StringBuilder("[");
								detailView.append(qualityReport.getProjectNo()).append("]");
								detailView.append(
										QualityTypeEnum.getEnum(qualityReport.getType()).getValue())
										.append(",");
								detailView.append(
										QualityStatusEnum.getEnum(qualityReport.getStatus())
												.getValue()).append(",[");
								detailView.append(qualityReport.getUser()).append("/")
										.append(DateFormat.date2String(qualityReport.getCreatetime())).append("]");
								qualityReport.setDetailView(detailView.toString());
						 }

						 //查询产品360度视频
						 List<FactoryQualityInspectionVideo> videoList = factoryQualityInspectionVideoService.selectByProjectNoAndType(shippingConfirmation.getProjectNo(),PRODUCT_VIDEO);
						 //查询项目所有工厂
						 List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(shippingConfirmation.getProjectNo());
						 
						 //判断是否允许签名
						 Boolean isSign = false;
						 if(shippingConfirmation.getFirstPerson() != null && shippingConfirmation.getSecondPerson() != null 
								 && shippingConfirmation.getThirdPerson() != null && shippingConfirmation.getFourthPerson() != null 
								 && isComplaintComplete == true 
							/*	 &&(					
									 (shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement()))))
								     ||
								     ((shippingConfirmation.getSampleOrProduct() == 0 && videoList!=null && videoList.size() > 0) || shippingConfirmation.getSampleOrProduct() == 1)  
								   )	*/			
								 ){	
							 if(shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement())) || StringUtils.isNotBlank(productFileName))){
								 isSign = true;
							 }
							 if((shippingConfirmation.getSampleOrProduct() == 0 && videoList!=null && videoList.size() > 0)){							
								 if((shippingConfirmation.getSampleFileName() != null) || isSampleNoProblem == true){
									 isSign = true;
								 }
							 }
						   }
						 					
						 //针对之前的出货单，不进行拦截（id 239）
						 if(shippingConfirmation.getId()<=239){
							 isSign = true;
						 }
						 
						 //查询留言
						 List<Comment> comments = projectCommentService.selectByShippingId(shippingConfirmation.getId());
						 
						 
						 request.setAttribute("project", project);	
						 request.setAttribute("userName", userName!=null?userName.toLowerCase():"");	
						 request.setAttribute("isComplaintComplete", isComplaintComplete);	
						 request.setAttribute("complaintList", complaintList);	
						 request.setAttribute("tasks", tasks);	
						 request.setAttribute("analysisIssueList", analysisIssueList);	
						 request.setAttribute("qualityAnalysis", qualityAnalysis);	
						 request.setAttribute("isSampleCheck", isSampleCheck);	
						 request.setAttribute("qualityReports", reports);	
						 request.setAttribute("videoList", videoList);	
						 request.setAttribute("factoryList", factoryList);	
						 request.setAttribute("isSampleNoProblem", isSampleNoProblem);	
						 request.setAttribute("isProductNoProblem", isProductNoProblem);	
						 request.setAttribute("productFileName", productFileName);	
						 request.setAttribute("operateExplain", operateExplain);	
						 request.setAttribute("isSign", isSign);	
						 request.setAttribute("comments", comments);	
				 }
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}			 			
				 return "confirm_list_share";
		}
		
		
		
	
		
		
		/**
		 * 删除投诉表
		 * @Title delete 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@ResponseBody
		@RequestMapping("/deleteShipping")
		public JsonResult deleteShipping(HttpServletRequest request,HttpServletResponse response){
			
			JsonResult jsonResult = new JsonResult();
			try {
				String id = request.getParameter("id");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
				}				
				if(StringUtils.isNotBlank(id)){
					ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(id));
					if(userName.equalsIgnoreCase(shippingConfirmation.getCreatePerson()) || userName.equalsIgnoreCase("ninazhao")){
						shippingConfirmationService.deleteByPrimaryKey(Integer.parseInt(id));
						jsonResult.setOk(true);
						jsonResult.setMessage("已删除");
					}else{
						jsonResult.setOk(false);
						jsonResult.setMessage("你没有权限删除");
					}
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage("删除失败");
				LOG.error("====电子出货单删除失败==deleteShipping===",e);
		
			}	 
			return jsonResult;
		}
		
		
		
		
		
		
		
		
		
		
		       /**
				 * 样品检验上传
				 * @Title upload 
				 * @Description
				 * @param request
				 * @param response
				 * @return
				 * @return JsonResult
				 */
				@RequestMapping("/sampleUpload")
				@ResponseBody
				public JsonResult sampleUpload(HttpServletRequest request,HttpServletResponse response){  
						JsonResult jsonResult = new JsonResult();
						 try {
							 String ids = request.getParameter("id");
					 		 String projectNo = request.getParameter("projectNo");
					 		 String drawingName = request.getParameter("fileName");
					 		 String path1 = UploadAndDownloadPathUtil.getProjectImg()
					 				 + File.separator + projectNo + File.separator ; 
					 		 String path = UploadAndDownloadPathUtil.getProjectImg()
									+ File.separator + projectNo + File.separator + "sample"+ File.separator ; 
					 	
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
					 		
					 		//更新样品上传
					 		ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(ids));
					 		if(shippingConfirmation!=null){
					 			shippingConfirmation.setSampleFileOriginalName(drawingName);
					 			shippingConfirmation.setSampleFileName(fileName);
					 			shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation);
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
						 	} catch (ParseException e) {
								e.printStackTrace();
						 		jsonResult.setOk(false);
						 		jsonResult.setMessage("上传失败");
						 		return jsonResult;
							} 			
				 }
			    
		
		
				
				/**
				 * 出货证明上传
				 * @Title upload 
				 * @Description
				 * @param request
				 * @param response
				 * @return
				 * @return JsonResult
				 */
				@RequestMapping("/shipmentAgreementUpload")
				@ResponseBody
				public JsonResult shipmentAgreementUpload(HttpServletRequest request,HttpServletResponse response){  
						JsonResult jsonResult = new JsonResult();
						 try {
							 String ids = request.getParameter("id");
					 		 String projectNo = request.getParameter("projectNo");
					 		 String drawingName = request.getParameter("fileName");
					 		 String path1 = UploadAndDownloadPathUtil.getProjectImg()
					 				 + File.separator + projectNo + File.separator ; 
					 		 String path = UploadAndDownloadPathUtil.getProjectImg()
									+ File.separator + projectNo + File.separator + "shipment"+ File.separator ; 
					 	
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
					 		
					 		//更新样品上传
					 		ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(ids));
					 		if(shippingConfirmation!=null){
					 			shippingConfirmation.setShipmentAgreement(fileName);
					 			shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation);
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
						 	} catch (ParseException e) {
								e.printStackTrace();
						 		jsonResult.setOk(false);
						 		jsonResult.setMessage("上传失败");
						 		return jsonResult;
							} 			
				 }
				
		
				
		   		
}
