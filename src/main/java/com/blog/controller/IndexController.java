package com.blog.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IClassifyService;
import com.blog.service.user.IUserService;
import com.blog.status.Status;
@Controller
public class IndexController extends BaseController {
	@Autowired
	private IBlogService iBlogService;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IClassifyService iClassifyService;
	@RequestMapping(value="index", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ModelAndView mv = new ModelAndView("index/index");
		Subject currenUser = SecurityUtils.getSubject();
		if (currenUser.getPrincipal() instanceof User) {
			User user = (User) currenUser.getPrincipal();
			mv.addObject("userName", user.getUserName());
			mv.addObject("userLogo", user.getUserLogo());
		}else{
			mv.addObject("userName", null);
		}
		
		Blog queryBlog=new Blog();
		queryBlog.setBlogStatus(Status.BLOG_ENABLED.CODE);
		List<Blog> blogs = iBlogService.getAlls(queryBlog);
		mv.addObject("blogs",blogs);
		mv.addObject("iUserService",iUserService);
		mv.addObject("iClassifyService",iClassifyService);
		return mv;
	}
}
