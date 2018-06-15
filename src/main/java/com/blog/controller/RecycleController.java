package com.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.user.User;

@Controller
@RequestMapping("recycle")
public class RecycleController extends BaseController{
	@RequestMapping("toRecycle")
	public ModelAndView toRecyle(){
		ModelAndView mv = new ModelAndView("recycle/recycle");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		return mv;
	}
}
