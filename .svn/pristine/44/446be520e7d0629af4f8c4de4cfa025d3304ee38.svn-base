package com.cn.hnust.daoErp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProductionPlan;


public interface ProductionPlanErpMapper {
  
	/**
	 * 查询erp的生产计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	List<ProductionPlan> selectByProjectNo(@Param("projectNo")String projectNo);
	
	
	
	/**
	 * 查询erp的生产计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	ProductionPlan selectDemandByProjectNo(@Param("projectNo")String projectNo);
    
}