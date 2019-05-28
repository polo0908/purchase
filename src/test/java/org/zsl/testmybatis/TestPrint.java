package org.zsl.testmybatis;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.hnust.dao.ShippingConfirmationMapper;
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
public class TestPrint {
	
	@Resource
	private IQualityReportService qualityReportService;
	
	@Resource
	private IQualityPicExplainService qualityPicExplainService;
	
	@Resource
	private ShippingConfirmationService shippingConfirmationService;
	@Resource
	private ShippingConfirmationMapper shippingConfirmationMapper;
	@Resource
	private IProjectInspectionReportService projectInspectionReportService;
	

	 @Test
	public void test1() throws ParseException {

		 ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
		 shippingConfirmationQuery.setRoleNo(100);
		 shippingConfirmationQuery.setSampleOrProduct(1);
		 shippingConfirmationQuery.setIsComplete(0);
		 shippingConfirmationQuery.setPageSize(100);
		 shippingConfirmationQuery.setPageNumber(0);
		 List<ShippingConfirmation> list = shippingConfirmationService.selectByProjectNo(shippingConfirmationQuery);
		 System.out.println(list);
		 for (ShippingConfirmation shippingConfirmation : list) {
			 int count = shippingConfirmationMapper.selectCountByProjectNoAndType(shippingConfirmation.getProjectNo(),1);
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
				if(count == 1 && amount > 1){
					 shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation);
				}			
		 }
	}

		@Test
		public void test3() throws Exception {
			List<ProjectInspectionReport> reports = projectInspectionReportService.selectAllInspectionReport();
			for (ProjectInspectionReport projectInspectionReport : reports) {
				try {
					ProjectInspectionReport insertProject = new ProjectInspectionReport();
					insertProject.setId(IdGen.uuid());
 					insertProject.setCreateDate(projectInspectionReport.getCreateDate());
					insertProject.setProjectNo(projectInspectionReport.getProjectNo());
					insertProject.setReportName(projectInspectionReport.getReportName());
					projectInspectionReportService.addProjectInspectionReport(insertProject);
					Assert.assertTrue(true);
					System.out.println("导入完成");
				} catch (Exception e) {					
					e.printStackTrace();
					continue;
				}
			}
		}
		
	

}
