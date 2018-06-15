package com.blog.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.blog.Blog;
import com.blog.entity.blog.Classify;
import com.blog.entity.user.User;
import com.blog.service.blog.IBlogService;
import com.blog.service.blog.IClassifyService;

@Controller
@RequestMapping("editor")
public class UEditorController extends BaseController{

	@Autowired
	private IClassifyService iClassifyService;
	
	@Autowired
	private IBlogService iBlogService;
	
	@RequestMapping("/toUEditor")
	public ModelAndView toUEditor(){
		ModelAndView mv = new ModelAndView("ueditor/ueditor");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		return mv;
	}
	
	@RequestMapping("/toClassify")
	public ModelAndView toClassify(){
		ModelAndView mv = new ModelAndView("classify/classify");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		mv.addObject("userName", user.getUserName());
		mv.addObject("userLogo", user.getUserLogo());
		Classify queryClassify=new Classify();
		queryClassify.setClassifyUserId(user.getId());
		queryClassify.setClassifyParentId(0);
		queryClassify.setClassifyStatus(0);
		List<Classify> classifys = iClassifyService.getAlls(queryClassify);
		mv.addObject("classifys", classifys);
		Blog queryBlog=new Blog();
		queryBlog.setBlogClassifyId(0);
		queryBlog.setBlogStatus(0);
		queryBlog.setBlogUserId(user.getId());
		List<Blog> blogs = iBlogService.getAlls(queryBlog);
		mv.addObject("blogs", blogs);
		return mv;
	}
	/*查询当前用户同一个父ID的目录*/
	@RequestMapping(value="classify/{parentId}",method=RequestMethod.GET)
	public ModelAndView getClassifysByParentId(@PathVariable("parentId") int id){
		ModelAndView mv = new ModelAndView("classify/childClassify");
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		
		Classify queryClassify=new Classify();
		queryClassify.setClassifyUserId(user.getId());
		queryClassify.setClassifyParentId(id);
		queryClassify.setClassifyStatus(0);
		List<Classify> classifys = iClassifyService.getAlls(queryClassify);
		mv.addObject("classifys", classifys);
		return mv;
	}
	/*查询当前用户ID查询父ID的目录*/
	@RequestMapping(value="classify/getParent",method=RequestMethod.GET)
	public ModelAndView getClassifyParentIdById(int id){
		ModelAndView mv = new ModelAndView();
		Classify classify = iClassifyService.getById(id);
		mv.addObject("classify", classify);
		return mv;
	}
	
	/*创建新分类*/
	@RequestMapping(value="classify",method=RequestMethod.POST)
	public ModelAndView createClassify(String classifyName,int classifyParentId){
		ModelAndView mv = new ModelAndView();
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		
		Classify parentClassify = iClassifyService.getById(classifyParentId);
		Classify classify=new Classify();
		classify.setClassifyUserId(user.getId());
		classify.setClassifyName(classifyName);
		classify.setClassifyBlogNum(0);
		classify.setClassifyCreateTime(new Date());
		classify.setClassifyStatus(0);
		if (parentClassify==null) {
			classify.setClassifyLevel(1);
			classify.setClassifyParentId(0);
			classify.setClassifyPath("/"+classifyName);
		}else {
			classify.setClassifyParentId(parentClassify.getId());
			classify.setClassifyLevel(parentClassify.getClassifyLevel()+1);
			classify.setClassifyPath(parentClassify.getClassifyPath()+"/"+classifyName);
		}
		iClassifyService.add(classify);
		mv.addObject("classify", classify);
		return mv;
	}
	/*重命名分类*/
	@RequestMapping(value="classify/{id}",method=RequestMethod.POST)
	public ModelAndView renameClassifyName(@PathVariable("id") int id,String classifyName){
		ModelAndView mv = new ModelAndView();
		
		Classify updateClassify=iClassifyService.getById(id);
		String oldPath = updateClassify.getClassifyPath();
		String replaceoldPath = oldPath.replaceAll(updateClassify.getClassifyName(), classifyName);
		updateClassify.setClassifyName(classifyName);
		updateClassify.setClassifyPath(replaceoldPath);
		iClassifyService.update(updateClassify);
		
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		
		List<Classify> classifys = iClassifyService.getByLikeClassifyPath(oldPath, user.getId());
		for (Classify classify : classifys) {
			String path = classify.getClassifyPath();
			classify.setClassifyPath(path.replaceAll(oldPath, replaceoldPath));
			iClassifyService.update(classify);
		}
		mv.addObject("classify", updateClassify);
		
		return mv;
	}
	
	/*移到回收站*/
	@RequestMapping(value="classify/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteClassifyById(@PathVariable int id){
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		Classify updateClassify=iClassifyService.getById(id);
		updateClassify.setClassifyStatus(1);
		updateClassify.setClassifyPath("");
		updateClassify.setClassifyModifyTime(new Date());
		iClassifyService.update(updateClassify);
		iBlogService.updateBlogStautsByClassifyId(1,id);
		updateClassifyAndBLogByClassifyParentId(id,user.getId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("classify", updateClassify);
		return mv;
	}
	private void updateClassifyAndBLogByClassifyParentId(int id,int userId){
		Classify queryClassify=new Classify();
		queryClassify.setClassifyParentId(id);
		queryClassify.setClassifyUserId(userId);
		List<Classify> classifys = iClassifyService.getAlls(queryClassify);
		for (Classify classify : classifys) {
			classify.setClassifyModifyTime(new Date());
			classify.setClassifyPath("");
			classify.setClassifyStatus(1);
			iClassifyService.update(classify);
			iBlogService.updateBlogStautsByClassifyId(1, classify.getId());
			updateClassifyAndBLogByClassifyParentId(classify.getId(),userId);
		}
	}
	
	
	/*查询当前目录下所有子目录以前文章*/
	@RequestMapping(value="getChildClassifyAndBlogById",method=RequestMethod.POST)
	public ModelAndView getChildClassifyAndBlogById(int id){
		Subject currenUser= SecurityUtils.getSubject();
		User user= (User) currenUser.getPrincipal();
		
		ModelAndView mv = new ModelAndView("classify/folderAndBlog");
		Classify queryClassify=new Classify();
		queryClassify.setClassifyParentId(id);
		queryClassify.setClassifyStatus(0);
		queryClassify.setClassifyUserId(user.getId());
		List<Classify> classifys = iClassifyService.getAlls(queryClassify);
		mv.addObject("classifys", classifys);
		Blog queryBlog=new Blog();
		queryBlog.setBlogClassifyId(id);
		queryBlog.setBlogStatus(0);
		queryBlog.setBlogUserId(user.getId());
		List<Blog> blogs = iBlogService.getAlls(queryBlog);
		mv.addObject("blogs", blogs);
		return mv;
	}
	
}
