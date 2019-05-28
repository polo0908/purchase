package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.InvoiceInfo;

public interface InvoiceInfoService {
  
	
	/**
	 * 查询erp最早发票时间（用于判断是否新客户）
	 * @Title selectFirstDate 
	 * @Description
	 * @return
	 * @return List<InvoiceInfo>
	 */
	List<InvoiceInfo> selectFirstDate(List<String> item);
/**
 * 
 * @Title:InvoiceInfoService
 * @Description:查询利润
   @author wangyang
 * @param userName
 * @param start
 * @return double
 * @throws
 */
	String getAllMoney(String userName, String start);
/**
 * 
 * @Title:InvoiceInfoService
 * @Description:查询返单率数据
   @author wangyang
 * @param userName
 * @param start
 * @return InvoiceInfo
 * @throws
 */
InvoiceInfo getAll(String userName, String start);
}
