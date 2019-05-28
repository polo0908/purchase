package com.cn.hnust.daoQuotation;

import org.apache.ibatis.annotations.Param;





public interface QuotationNewQuotesMapper {
    int deleteByPrimaryKey(Integer id);
    /**
     * @param allProjectNo 
     * 
     * @Title:IQuotationNewQuotesService
     * @Description:新的大项目
       @author wangyang
     * @return int
     * @throws
     */
    	int getNew(@Param("projectId")String allProjectNo);
    /**
     * 
     * @Title:IQuotationNewQuotesService
     * @Description:老的大项目
       @author wangyang
     * @return int
     * @throws
     */
	int getOld(@Param("projectId")String allProjectNo);

   


}