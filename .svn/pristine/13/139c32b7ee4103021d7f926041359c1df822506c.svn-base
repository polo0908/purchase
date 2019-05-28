package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.util.IdGen;

@Controller
@RequestMapping("/inspectionReport")
public class ProjectInspectionReportController {
   
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	
	@RequestMapping(value = "/synchInspectionReport", method = RequestMethod.POST)
	public void synchProjectDrawing(HttpServletRequest request,@RequestParam Map<String,String> map)
			throws Exception {
		 String jsonStr = map.get("inspectionReport");
		 JSONArray json = JSONArray.fromObject(jsonStr); // 首先把字符串转成 JSONArray  对象
		 List<ProjectInspectionReport> inspectionReportList=new ArrayList<ProjectInspectionReport>();
		 if(json.size()>0){
			  for(int i=0;i<json.size();i++){
			    JSONObject obj = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    String projectNo = (String) obj.get("projectNo");	
			    String reportName=(String) obj.get("reportName");
			    ProjectInspectionReport inspectionReport=new ProjectInspectionReport();
			    inspectionReport.setId(IdGen.uuid());
			    inspectionReport.setProjectNo(projectNo);
			    inspectionReport.setReportName(reportName);
			    inspectionReport.setCreateDate(new Date());
			    inspectionReportList.add(inspectionReport);
			  }
		 }
		 //1.查询原有的数据
		 //2.将同步的数据与原有数据进行对比
		 List<ProjectInspectionReport> projectList=projectInspectionReportService.selectAllInspectionReport();
		 List<ProjectInspectionReport> insertProjectList=new ArrayList<ProjectInspectionReport>(); //需要插入的数据集合
         for(int i=0;i<inspectionReportList.size();i++){
        	 ProjectInspectionReport  inspectionReport= inspectionReportList.get(i);
        	 ProjectInspectionReport project = null;
			boolean bool = false;
			for (int j = 0; j < projectList.size(); j++) {
				project = projectList.get(j);
				//项目号匹配成功不做任何操作
				if(inspectionReport.getProjectNo().equals(project.getProjectNo()) && inspectionReport.getReportName().equals(project.getReportName())){
					bool = true;
					break;
				}
			}
			//项目号匹配不成功,将不匹配的项目号添加到项目表里面
			if(!bool){
				ProjectInspectionReport insertProject = new ProjectInspectionReport();
				insertProject.setId(IdGen.uuid());
				insertProject.setProjectNo(inspectionReport.getProjectNo());
				insertProject.setReportName(inspectionReport.getReportName());
				insertProject.setCreateDate(new Date());
				projectInspectionReportService.addProjectInspectionReport(insertProject);
			}
		  }
	}
}
