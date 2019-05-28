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
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.RoleBindList;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IRoleBindListService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.QuoteWeeklyReportService;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestProjectUploadList {
	private static Logger logger = Logger.getLogger(TestProjectUploadList.class);
	 private ApplicationContext ac = null;
	private IUserService userService = null;
	private IProjectService projectService;
	private QuoteWeeklyReportService quoteWeeklyReportService = null;

	 @Before
	 public void before() {
	 ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	 quoteWeeklyReportService = (QuoteWeeklyReportService) ac.getBean("quoteWeeklyReportService");
	 projectService = (IProjectService) ac.getBean("projectService");
	 }

	@Test
	public void test1() {
		List<Project> projectList = projectService.selectAllProject();
		System.out.println(projectList);
	}

	

}
