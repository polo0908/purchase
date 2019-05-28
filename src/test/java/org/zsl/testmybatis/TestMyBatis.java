package org.zsl.testmybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.dao.MeetingRecordMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.RoleBindList;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IRoleBindListService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	 private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;
	@Autowired
	private IProjectService projectService;

	@Resource
	private IRoleBindListService roleService;
	@Resource
	private IProjectInspectionReportService projectInspectionReportService;
	
	@Resource ProjectTaskMapper ps;
	
	@Resource MeetingRecordMapper ms;
	
	@Resource
	private RpcHelper rpcHelper;

	 @Before
	 public void before() {
	 ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	 userService = (IUserService) ac.getBean("userService");
	 }

	// @Test
	public void test1() {
		String userName = "";
		String password = "";
		User user = userService.selectUser(userName, password);
		logger.info(JSON.toJSONString(user));
	}

	/**
	 *测试批量添加项目信息
	 *  @Test
	 * @Title batchAddProject 
	 * @Description TODO
	 * @return void
	 */
	@Test
	public void batchAddProject() {
		List<Project> projectList = new ArrayList<Project>();
		Project insertProject = new Project();
		insertProject.setId(IdGen.uuid());
		insertProject.setProjectNo("shs19650");
		insertProject.setProjectName("##铸封外壳");
		insertProject.setDeliveryDate(DateUtil.StrToDate("2018-08-13"));
		insertProject.setDeliveryStatus(0);
		insertProject.setWarningStatus(0);
		insertProject.setImportance(0);
		insertProject.setFinish(0);
		insertProject.setStage(0);
		insertProject.setPurchaseId(184);
		insertProject.setEmailUserId(165);
		insertProject.setActualStartDate(DateUtil.StrToDate("2018-07-06"));
		insertProject.setCreateDate(new Date());
		projectList.add(insertProject);
		projectService.batchAddProject(projectList);
	}

	// @Test
	public void test2() {

		RoleBindList list = new RoleBindList();

		list.setCreateId(123);

		RoleBindDetail d = new RoleBindDetail();
		RoleBindDetail f = new RoleBindDetail();
		d.setRoleName("123");
		f.setRoleName("123");
		List<RoleBindDetail> ls = list.getRoleBindDetails();
		ls.add(d);
		ls.add(f);
		roleService.insertAll(list);

	}

	@Test
	public void test3() throws Exception {
		ProjectInspectionReport insertProject = new ProjectInspectionReport();
		insertProject.setCreateDate(new Date());
		insertProject.setProjectNo("SHS10222-4");
		insertProject.setReportName("(SHS10222-4)(SHS10222-3)SHS10222-3-检验计划-01(1).xlsx");
		projectInspectionReportService.addProjectInspectionReport(insertProject);

	}
	
	@Test
	public void test4() {
		List<ProjectTask> ls = ps.selectAll();
		for(int i=0;i<ls.size();i++){
			//rpcHelper.sendRequest("", ls.get(i));
		}
		
		List<MeetingRecord> lm =  ms.selectAll();
		for(int j=0;j<lm.size();j++){
			//rpcHelper.sendRequest("http://112.64.174.34:33169/ERP-NBEmail/helpServlet?action=publicComment2&className=ExternalinterfaceServlet", lm.get(j));
		}

	}
	
	

}
