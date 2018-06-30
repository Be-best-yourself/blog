package com.blog.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.user.IUserDao;
import com.blog.entity.user.User;
import com.blog.service.impl.BaseServiceImpl;
import com.blog.service.user.IUserService;
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Autowired
	private IUserDao iUserDao;
	@Override
	public User getUserForUserName(String userName) {
		return iUserDao.selectUserForUserName(userName);
	}
	@Override
	public User getUserByPhoneNum(String phoneNum) {
		return iUserDao.selectUserForPhoneNum(phoneNum);
	}
}
