//package org.zsl.testmybatis;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.junit.Assert;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.junit.Test;
//import com.cn.hnust.pojo.Project;
//import com.cn.hnust.service.IProjectService;
//import com.cn.hnust.util.DateUtil;
//import com.cn.hnust.util.IdGen;
//
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
///**
//* 
//* @author 
//*
//*/
//public class Testcase {
//	
//	@Autowired
//	private IProjectService projectService;
//	
//	/**
//	 *测试批量添加项目信息
//	 *  @Test
//	 * @Title batchAddProject 
//	 * @Description
//	 * @return void
//	 */
//	@Test
//	public void batchAddProject() {
//		List<Project> projectList = new ArrayList<Project>();
//		Project insertProject = new Project();
//		insertProject.setId(IdGen.uuid());
//		insertProject.setProjectNo("shs19650");
//		insertProject.setProjectName("##铸封外壳");
//		insertProject.setDeliveryDate(DateUtil.StrToDate("2018-08-13"));
//		insertProject.setDeliveryStatus(0);
//		insertProject.setWarningStatus(0);
//		insertProject.setImportance(0);
//		insertProject.setFinish(0);
//		insertProject.setStage(0);
//		insertProject.setPurchaseId(184);
//		insertProject.setEmailUserId(165);
//		insertProject.setActualStartDate(DateUtil.StrToDate("2018-07-06"));
//		insertProject.setCreateDate(new Date());
//		projectList.add(insertProject);
//		projectService.batchAddProject(projectList);
//	}
//}
