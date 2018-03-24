package com.zfw.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zfw.blog.entity.user.User;
import com.zfw.blog.service.user.IUserService;

@Controller
public class UserController extends BaseController {
	@Autowired
	private IUserService iUserService;

	// 用户注册
	@RequestMapping(value = "/admin/user", method = RequestMethod.POST)
	public ModelAndView toRegister(User user) {
		ModelAndView mv = new ModelAndView();
		int userId = iUserService.addAndGetId(user);
		if (userId != 0) {
			mv.addObject("result", "sucesse");
		}
		return mv;
	}
}
