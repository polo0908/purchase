package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.EmailUser;

public interface EmailUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailUser record);

    int insertSelective(EmailUser record);

    EmailUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmailUser record);

    int updateByPrimaryKey(EmailUser record);
    
    /**
     * 查询所有的销售、跟单、采购
     * @Title queryAll 
     * @Description
     * @return
     * @return List<EmailUser>
     */
    List<EmailUser> queryAll();
}