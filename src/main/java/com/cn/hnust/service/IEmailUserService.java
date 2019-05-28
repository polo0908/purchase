package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.EmailUser;

public interface IEmailUserService {
    /**
     * 查询所有的销售、跟单、采购
     * @Title queryAll 
     * @Description
     * @return
     * @return List<EmailUser>
     */
    List<EmailUser> queryAll();
}
