package com.zfw.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfw.blog.entity.student.Student;
import com.zfw.blog.service.IStudentService;

@Controller
public class TestStudentController extends BaseController{
	@Autowired
	private IStudentService studentService;
	@RequestMapping("test1")
	public void test1(){
		Student student=new Student();
		student.setName("a");
		student.setAge(1);
		studentService.add(student);
		logger.info("aaa");
	}
	@RequestMapping("test2")
	public ModelAndView test2(){
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("student", studentService.getById(1));
		return modelAndView;
	}
	@RequestMapping("test3")
	public ModelAndView test3(HttpServletRequest request){
		return new ModelAndView().addObject(getUserAgent(request));
	}
	/*@RequestMapping("test4")
	public ModelAndView test4(){
	}
	*/

}
