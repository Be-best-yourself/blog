package com.blog.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.blog.BlogText;
import com.blog.entity.blog.Classify;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IBlogTextService;
import com.blog.service.blog.IClassifyService;

@Controller
public class UEditorController extends BaseController {
	@Autowired
	private IClassifyService iClassifyService;
	
	@Autowired
	private IBlogService iBlogService;
	
	@Autowired
	private IBlogTextService iBlogTextService;
	
	
	@RequestMapping("/editor/toUEditor")
	public ModelAndView toUEditor(@RequestParam(value="id",required=false)Integer blogId) {
		ModelAndView mv = new ModelAndView("ueditor/ueditor");
		Subject currenUser = SecurityUtils.getSubject();
		User user = (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		
		
		Classify queryClassify=new Classify();
		queryClassify.setClassifyUserId(user.getId());
		queryClassify.setClassifyStatus(0);
		List<Classify> classifys = iClassifyService.getAlls(queryClassify);
		mv.addObject("classifys", classifys);
		if (blogId!=null) {
			Blog blog = iBlogService.selectByIdAndUserId(blogId,user.getId());
			BlogText blogText = iBlogTextService.getById(blog.getBlogTextId());
			mv.addObject("blog", blog);
			mv.addObject("blogText", new String(blogText.getBlogText()));
		}
		return mv;
	}

}
