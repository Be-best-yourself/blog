package com.blog.controller;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.user.User;
import com.blog.service.user.IUserService;

@Controller
public class UserController extends BaseController {
	@Autowired
	private IUserService iUserService;

	// 转到用户注册
	@RequestMapping("/toRegister")
	public ModelAndView toRegister() {
		return new ModelAndView("register");
	}

	// 用户注册
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView registerUser(User user) {
		SimpleHash userMD5Password = new SimpleHash("MD5", ByteSource.Util.bytes(user.getUserPassword()));
		SimpleHash userSaltPassword = new SimpleHash("MD5", user.getUserName(),
				ByteSource.Util.bytes(user.getUserPassword()), 1024);
		ModelAndView mv = new ModelAndView();
		user.setUserPassword(userMD5Password.toString());
		user.setUserPasswordSalt(userSaltPassword.toString());
		int userId = iUserService.addAndGetId(user);
		if (userId != 0) {
			mv.addObject("result", "sucesse");
		}
		return mv;
	}
}
