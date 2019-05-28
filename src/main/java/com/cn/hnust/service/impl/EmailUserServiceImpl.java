package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.EmailUserMapper;
import com.cn.hnust.pojo.EmailUser;
import com.cn.hnust.service.IEmailUserService;

@Service
public class EmailUserServiceImpl implements IEmailUserService {

	@Autowired
	private EmailUserMapper emailUserMapper;
	
	@Override
	public List<EmailUser> queryAll() {
		return emailUserMapper.queryAll();
	}

}
