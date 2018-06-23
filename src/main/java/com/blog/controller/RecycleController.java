package com.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.blog.Classify;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IClassifyService;
import com.blog.status.Status;

//回收站
@Controller
public class RecycleController extends BaseController{
	@Autowired
	private IBlogService iBlogService;
	@Autowired
	private IClassifyService iClassifyService;
	
	@RequestMapping("recycle/toRecycle")
	public ModelAndView toRecyle(){
		ModelAndView mv = new ModelAndView("recycle/recycle");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		Classify queryClassify = new Classify();
		queryClassify.setClassifyUserId(user.getId());
		queryClassify.setClassifyStatus(Status.CLASSIFY_ENABLED.CODE);
		mv.addObject("classifys", iClassifyService.getAlls(queryClassify));
		return mv;
	}
	@RequestMapping(value="recycle/getChildRecycle",method=RequestMethod.POST)
	public ModelAndView getChildRecycle(){
		ModelAndView mv = new ModelAndView("recycle/childRecycle");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		Blog queryBlog=new Blog();
		queryBlog.setBlogUserId(user.getId());
		queryBlog.setBlogStatus(Status.BLOG_RECYCLE.CODE);
		mv.addObject("blogs", iBlogService.getAlls(queryBlog));
		return mv;
	}
}
