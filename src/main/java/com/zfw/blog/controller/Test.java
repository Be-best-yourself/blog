package com.zfw.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfw.blog.entity.Student;
import com.zfw.blog.service.IStudentService;

@Controller
public class Test extends BaseController{
	@Autowired
	private IStudentService iStudentService;
	@RequestMapping("test1")
	public void test1(){
		Student student=new Student();
		student.setName("a");
		student.setAge(1);
		iStudentService.addStudent(student);
		logger.info("aaa");
	}
	@RequestMapping("test2")
	public ModelAndView test2(){
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("student", iStudentService.findStudentById(1));
		return modelAndView;
	}
	@RequestMapping("test3")
	public ModelAndView test3(){
		return new ModelAndView().addObject(getUserAgent());
	}
	@RequestMapping("test4")
	public ModelAndView test4(){
		return new ModelAndView().addObject(iStudentService.findById(1, "test_student"));
	}
	

}
