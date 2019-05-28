package com.cn.hnust.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.daoQuotation.QuotationNewQuotesMapper;
import com.cn.hnust.service.IQuotationNewQuotesService;
@Service
public class QuotationNewQuotesServiceImpl  implements IQuotationNewQuotesService{
	@Resource
	private QuotationNewQuotesMapper quotationNewQuotesMapper;

	@Override
	public int getNew(String allProjectNo) {
		
		return quotationNewQuotesMapper.getNew(allProjectNo);
	}

	@Override
	public int getOld(String allProjectNo) {
		
		return quotationNewQuotesMapper.getOld(allProjectNo);
	}
}
