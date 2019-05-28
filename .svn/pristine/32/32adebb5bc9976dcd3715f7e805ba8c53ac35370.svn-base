package com.cn.hnust.daoErp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectERP;

public interface ItemCaseERPMapper {
	
	/**
	 * 根据项目号查询
	 * @Title selectByCaseNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return ProjectERP
	 */
    ProjectERP selectByCaseNo(String projectNo);
    /**
     * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerOff(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
	/**
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:新客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getNewCustomerBigProjectOffer(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
	/**
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerBigProjectOffer(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
    /**
     * 
     * @Title:ItemCaseERPMapper
     * @Description:获取违规次数
       @author wangyang
     * @param userName
     * @param start
     * @return int
     * @throws
     */
	String getProjectViolation(@Param("CustomerManager")String userName,@Param("projectNo") String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:打样成功
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	int proofingSuccess(@Param("CustomerManager")String userName,@Param("projectNo") String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:打样失败
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	int proofingFailure(@Param("CustomerManager")String userName,@Param("projectNo") String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo(@Param("CustomerManager")String userName, @Param("projectNo")String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo1(@Param("CustomerManager")String userName,@Param("projectNo") String start);
}
