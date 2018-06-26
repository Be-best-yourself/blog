package com.blog.service.user;

import com.blog.entity.user.User;
import com.blog.service.IBaseService;

public interface IUserService extends IBaseService<User>{

	User getUserForUserName(String userName);
}
