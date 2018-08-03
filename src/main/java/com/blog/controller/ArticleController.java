package com.blog.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.blog.BlogText;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IBlogTextService;
import com.blog.status.Status;
import com.blog.utils.OSSUtils;

@Controller
public class ArticleController extends BaseController {

	@Value("${ali.oss.endpoint}")
	private String endpoint;
	@Value("${ali.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${ali.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${ali.oss.bucketName}")
	private String bucketName;

	@Autowired
	private IBlogService iBlogService;
	@Autowired
	private IBlogTextService iBlogTextService;
	
	
	@RequestMapping("article/{id}")
	public ModelAndView showArticle(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("article/article");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		mv.addObject("result", currenUser.getPrincipal() instanceof User);
		if (currenUser.getPrincipal() instanceof User) {
			mv.addObject("userName", user.getUserName());
			mv.addObject("userLogo", user.getUserLogo());
		}
		Blog blog = iBlogService.getById(id);
		if (blog.getBlogStatus() == Status.BLOG_ENABLED.CODE) {
			BlogText blogText = iBlogTextService.getById(blog.getBlogTextId());
			String blogBody = OSSUtils.replaceHtml(new String(blogText.getBlogText()), endpoint, accessKeyId,
					accessKeySecret, new Date(new Date().getTime() + 5 * 1000), bucketName);

			mv.addObject("blogName", blog.getBlogName());
			mv.addObject("blogText", blogBody);
		}
		return mv;
	}

}
