package com.blog.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.user.User;

@Controller
@RequestMapping("editor")
public class UEditorController extends BaseController{

	@Autowired
	SqlSessionFactory SqlSessionFactory;
	@RequestMapping("/toUEditor")
	public ModelAndView toUEditor(){
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		ModelAndView mv = new ModelAndView("ueditor/ueditor");
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		return mv;
	}
}
