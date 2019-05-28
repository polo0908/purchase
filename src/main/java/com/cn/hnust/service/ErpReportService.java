package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityReport;

public interface ErpReportService {
	/**
	 * 查询erp上传的直接报告
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	List<QualityReport> selectErpReportByProjectNo(@Param("projectNo")String projectNo);
}
