package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.ItemCaseERPMapper;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ItemCaseERPService;
@Service
public class ItemCaseERPServiceImpl implements ItemCaseERPService {

	@Autowired
	private ItemCaseERPMapper itemCaseERPMapper;
	
	@Override
	public ProjectERP selectByCaseNo(String projectNo) {
		return itemCaseERPMapper.selectByCaseNo(projectNo);
	}

	@Override
	public int getOldCustomerOff(String userName, String start) {
		
		return itemCaseERPMapper.getOldCustomerOff(userName,start);
	}

	@Override
	public int getNewCustomerBigProjectOffer(String userName, String start) {
		
		return itemCaseERPMapper.getNewCustomerBigProjectOffer(userName,start);
	}

	@Override
	public int getOldCustomerBigProjectOffer(String userName, String start) {
		
		return itemCaseERPMapper.getOldCustomerBigProjectOffer(userName,start);
	}

	@Override
	public String getProjectViolation(String userName, String start) {
		
		return itemCaseERPMapper.getProjectViolation(userName,start);
	}

	@Override
	public int proofingSuccess(String userName, String start) {
		
		return itemCaseERPMapper.proofingSuccess(userName,start);
	}

	@Override
	public int proofingFailure(String userName, String start) {
		
		return itemCaseERPMapper.proofingFailure(userName,start);
	}

	@Override
	public List<ProjectERP> getAllCaseNo(String userName, String start) {
		
		return itemCaseERPMapper.getAllCaseNo(userName,start);
	}

	@Override
	public List<ProjectERP> getAllCaseNo1(String userName, String start) {
		
		return itemCaseERPMapper.getAllCaseNo1(userName,start);
	}

}
