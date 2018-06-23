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
import com.blog.entity.blog.Classify;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IClassifyService;
import com.blog.status.Status;

//草稿箱
@Controller
public class DraftsContoller extends BaseController {
	@Autowired
	private IBlogService iBlogService;
	@Autowired
	private IClassifyService iClassifyService;

	@RequestMapping("drafts/toDrafts")
	public ModelAndView toClassify() {
		ModelAndView mv = new ModelAndView("drafts/drafts");
		Subject currenUser = SecurityUtils.getSubject();
		User user = (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		Classify queryClassify = new Classify();
		queryClassify.setClassifyUserId(user.getId());
		queryClassify.setClassifyStatus(Status.CLASSIFY_ENABLED.CODE);
		mv.addObject("classifys", iClassifyService.getAlls(queryClassify));
		return mv;
	}

	@RequestMapping(value="drafts/getChildDrafts",method=RequestMethod.POST)
	public ModelAndView getChildClassifyAndBlogById(){
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		ModelAndView mv = new ModelAndView("drafts/childDrafts");
		Blog queryBlog=new Blog();
		queryBlog.setBlogUserId(user.getId());
		queryBlog.setBlogStatus(Status.BLOG_DRAFTS.CODE);
		List<Blog> blogs = iBlogService.getAlls(queryBlog);
		mv.addObject("blogs", blogs);
		return mv;
		}

	@RequestMapping(value="drafts/release",method=RequestMethod.POST)
	public ModelAndView releaseDrafts(int id,String blogName,String blogKeyword,int blogClassifyId){
		ModelAndView mv = new ModelAndView();
		try {
			Blog blog = iBlogService.getById(id);
			blog.setBlogStatus(Status.BLOG_ENABLED.CODE);
			blog.setBlogClassifyId(blogClassifyId);
			blog.setBlogName(blogName);
			blog.setBlogKeyword(blogKeyword);
			iBlogService.update(blog);
			mv.addObject("result", true);
		} catch (Exception e) {
			mv.addObject("result", false);
		}
		return mv;
	}
}
 