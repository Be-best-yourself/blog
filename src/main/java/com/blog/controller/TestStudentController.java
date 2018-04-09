package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.user.IUserDao;
import com.blog.entity.student.Student;
import com.blog.entity.user.User;
import com.blog.service.IStudentService;
import com.blog.service.user.IUserService;

@Controller
public class TestStudentController extends BaseController{
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IUserService userService;
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
	@RequestMapping("test5")
	public ModelAndView test5(){
		Student student=new Student();
		student.setName("1");
		List<Student> selectAlls = studentService.getAlls(student);
		return new ModelAndView().addObject("students", selectAlls);
	}
	@RequestMapping("test6")
	public ModelAndView testUser(){
		User t=new User();
		t.setUserName("zhang");
		List<User> users = userService.getAlls(t);
		return new ModelAndView().addObject("users",users);
	}
}
