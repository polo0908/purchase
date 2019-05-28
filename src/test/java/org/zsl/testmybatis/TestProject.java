package org.zsl.testmybatis;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ShippingConfirmationMapper;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IQualityPicExplainService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.ShippingConfirmationService;
import com.cn.hnust.util.IdGen;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestProject {
	
	@Resource
	private ProjectMapper projectMapper;

	 @Test
	public void test1() throws ParseException {
		 List<Project> list = projectMapper.selectByProjectList();
		 System.out.println("总数："+list.size());
		 list.forEach(p->{
			 String projectNo = p.getProjectNo();
			 projectMapper.selectByProjectDim(projectNo);
			  if(projectNo.contains("-")){
				  String originalProjectNo = projectNo.split("-")[0];
				  List<Project> projectList = projectMapper.selectByProjectDim(originalProjectNo);				  
				  if(projectList!=null){
					  projectList.forEach(p1->{
						  if(StringUtils.isNotBlank(p1.getProductImg())){
							  projectMapper.updateByNoPic(originalProjectNo,p1.getProductImg());
							  System.out.println("更新结束");
						  }
					  });
				  }
			  }	  
		 });
	}

	
		
	

}
