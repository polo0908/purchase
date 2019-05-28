package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ProjectInspectionReport;

public interface ProjectInspectionReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectInspectionReport record);

    int insertSelective(ProjectInspectionReport record);

    ProjectInspectionReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectInspectionReport record);

    int updateByPrimaryKey(ProjectInspectionReport record);

	List<ProjectInspectionReport> selectAllInspectionReport();

	void batchAddInspectionReport(List<ProjectInspectionReport> projectInspectionReportList);

	List<ProjectInspectionReport> selectInspectionReportByProjectNo(String projectNo);

	List<ProjectInspectionReport> selectInspectionPlanByProjectNo(String projectNo);
}