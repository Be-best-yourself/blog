package com.blog.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.blog.BlogText;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IBlogTextService;
import com.blog.status.Status;

@Controller
public class BlogController extends BaseController {
	@Autowired
	private IBlogService iBlogService;
	@Autowired
	private IBlogTextService iBlogTextService;

	@RequestMapping(value = "blog", method = RequestMethod.POST)
	public ModelAndView saveBlog(@RequestParam(required=false) Integer blogId, String blogKeyword, String blogDescription,
			String blogText, String blogTitle, int blogClassifyId, int blogStatus, String blogName) {
		ModelAndView mv = new ModelAndView();
		Subject currenUser = SecurityUtils.getSubject();
		User user = (User) currenUser.getPrincipal();
		try {
			BlogText createBlogText = new BlogText();
			Blog updateOrAddBlog = new Blog();
			//如果blogId不为null 执行更新
			if (blogId!=null) {
				updateOrAddBlog=iBlogService.getById(blogId);
				createBlogText=iBlogTextService.getById(updateOrAddBlog.getBlogTextId());
			}
			
			createBlogText.setBlogText(blogText.getBytes());
			iBlogTextService.updateOrAdd(createBlogText);

			updateOrAddBlog.setBlogStatus(blogStatus);
			updateOrAddBlog.setBlogClassifyId(blogClassifyId);
			updateOrAddBlog.setBlogDescription(blogDescription);
			updateOrAddBlog.setBlogKeyword(blogKeyword);
			updateOrAddBlog.setBlogName(blogName);
			updateOrAddBlog.setBlogTitle(blogTitle);
			updateOrAddBlog.setBlogUserId(user.getId());
			updateOrAddBlog.setBlogCreateTime(new Date());
			updateOrAddBlog.setBlogTextId(createBlogText.getId());
			iBlogService.updateOrAdd(updateOrAddBlog);
			mv.addObject("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("result", false);
		}

		return mv;
	}
	
	//移到回收站
	@RequestMapping(value="/blog/recycle/{id}",method=RequestMethod.POST)
	public ModelAndView recycleBlog(@PathVariable("id") int id){
		User user= (User) SecurityUtils.getSubject().getPrincipal();
		ModelAndView mv = new ModelAndView();
		try {
			Blog blog = iBlogService.selectByIdAndUserId(id, user.getId());
			blog.setBlogStatus(Status.BLOG_RECYCLE.CODE);
			blog.setBlogModifyTime(new Date());
			iBlogService.update(blog);
			mv.addObject("blog", blog);
		} catch (Exception e) {
			mv.addObject("result", false);
		}
		return mv;
	}
	@RequestMapping(value="/blog/{id}",method=RequestMethod.GET)
	public ModelAndView getBlog(@PathVariable int id){
		Blog blog = iBlogService.getById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("blog",blog);
		return mv;
	}
	
	
	@RequestMapping(value="/blog/{id}",method=RequestMethod.POST)
	public ModelAndView updateBlog(@PathVariable int id,int blogClassifyId,String blogKeyword,int blogStatus, String blogName){
		ModelAndView mv = new ModelAndView();
		Blog blog = iBlogService.getById(id);
		blog.setBlogStatus(blogStatus);
		blog.setBlogModifyTime(new Date());
		blog.setBlogKeyword(blogKeyword);
		blog.setBlogName(blogName);
		blog.setBlogClassifyId(blogClassifyId);
		iBlogService.update(blog);
		mv.addObject("blog",blog);
		return mv;
	}
	@RequestMapping(value="/blog/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteBlog(@PathVariable int id){
		ModelAndView mv = new ModelAndView();
		Blog blog = iBlogService.getById(id);
		iBlogService.deleteById(id);
		iBlogTextService.deleteById(blog.getBlogTextId());
		return mv;
	}

}
