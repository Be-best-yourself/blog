package com.blog.dao.user;

import com.blog.dao.IBaseDao;
import com.blog.entity.user.User;

public interface IUserDao extends IBaseDao<User>{

	User selectUserForUserName(String userName);

	User selectUserForPhoneNum(String phoneNum);
}