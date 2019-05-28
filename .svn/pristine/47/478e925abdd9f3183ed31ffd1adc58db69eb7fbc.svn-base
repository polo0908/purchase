package com.cn.hnust.service;

import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QualityReportQuery;

public interface IQualityReportService {
	
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(QualityReport record);

	    int insertSelective(QualityReport record)throws ParseException;

	    QualityReport selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(QualityReport record);

	    int updateByPrimaryKey(QualityReport record);
	    
	    QualityReport selectByPrimaryKey(int id);
	    
	    List<QualityReport> selectByProjectNo(@Param("projectNo")String projectNo);

		QualityReport selectNewestReportByProjectNo(@Param("projectNo")String projectNo);

		
		
		/**
		 * 查询质检报告列表
		 * @Title selectAllReport 
		 * @Description
		 * @param qualityReportQuery
		 * @return
		 * @return List<QualityReport>
		 */
		List<QualityReport> selectAllReport(QualityReportQuery qualityReportQuery);
		
		
		
		/**
		 * 质检报告数量
		 * @Title totalReports 
		 * @Description
		 * @param qualityReportQuery
		 * @return
		 * @return int
		 */
		int totalReports(QualityReportQuery qualityReportQuery);
		
		
		/**
		 * 根据项目号和质检报告类型查询
		 * @Title selectByProjectNo 
		 * @Description
		 * @param projectNo
		 * @param type
		 * @return
		 * @return List<QualityReport>
		 */
		List<QualityReport> selectByProjectNoAndType(String projectNo,Integer type);
}
