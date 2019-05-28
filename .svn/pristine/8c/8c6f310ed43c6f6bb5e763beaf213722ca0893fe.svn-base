package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User selectUser(String userName, String password) {
		return userDao.selectUser(userName, password);
	}

	@Override
	public User selectUserByName(String userName) {
		return userDao.selectUserByName(userName);
	}

	@Override
	public User findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}

	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public List<User> selectUserByType(int roleNo) {
		return userDao.selectUserByType(roleNo);
	}

	@Override
	public int insertSelective(User record) {
		return userDao.insertSelective(record);
	}

	@Override
	public List<User> queryAllUser(String userName) {
		return userDao.queryAllUser(userName);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectById(Integer id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> queryByJob(String job) {
		return userDao.queryByJob(job);
	}

	public List<User> queryByRoleNo(User user) {
		
		return userDao.queryByRoleNo(user);
	}

	@Override
	public User selectByDingTalkId(String dingTalkId) {
		return userDao.selectByDingTalkId(dingTalkId);
	}

	
	

}
