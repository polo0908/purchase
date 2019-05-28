package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;


public class RpcQuotationStatistic {

	private final static Logger log = Logger.getLogger(RpcQuotationStatistic.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public synchronized String sendRequest(String url, String startDate,String endDate) {
		String str = "";
		try {			
			if (StringUtils.isBlank(url)) {
//		     url = "http://192.168.1.91:8081/New-Quotation/quote/getData";
				url = reader.getProperty("new_quotation_path")+"/quote/getData";
			}
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("startDate", startDate);	
			parameters.put("endDate", endDate);	
			
			str = HttpClient.sendPost(url, parameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
		}
		return str;
	}

}