package com.blog.dao.user;

import com.blog.dao.IBaseDao;
import com.blog.entity.user.User;

public interface IUserDao extends IBaseDao<User>{

	User getByUserName(String username);

	User selectUserForUserName(String userName);
}