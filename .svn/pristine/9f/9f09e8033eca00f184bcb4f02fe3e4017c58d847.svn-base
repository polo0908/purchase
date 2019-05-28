package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.User;

public interface ItemCaseERPService {
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
     * @param start 
     * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerOff(String userName, String start);
	/**
	 * @param start 
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:新客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getNewCustomerBigProjectOffer(String userName, String start);
	/**
	 * @param start 
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerBigProjectOffer(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取违规次数
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return int
	 * @throws
	 */
	String getProjectViolation(String userName, String start);
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
	int proofingSuccess(String userName, String start);
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
	int proofingFailure(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return List<ProjectERP>
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取销售项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return List<ProjectERP>
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo1(String userName, String start);
}
