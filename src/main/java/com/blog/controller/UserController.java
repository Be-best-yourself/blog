package com.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.user.User;
import com.blog.service.user.IUserService;

@Controller
public class UserController extends BaseController {
	@Autowired
	private IUserService iUserService;

	// 转到用户注册
	@RequestMapping("/user/toRegister")
	public ModelAndView toRegister() {
		return new ModelAndView("register");
	}

	// 用户注册
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ModelAndView registerUser(User user) {
		SimpleHash userMD5Password = new SimpleHash("MD5", ByteSource.Util.bytes(user.getUserPassword()));
		SimpleHash userSaltPassword = new SimpleHash("MD5", user.getUserPassword(),
				ByteSource.Util.bytes(user.getUserName()), 1024);
		ModelAndView mv = new ModelAndView();
		user.setUserPassword(userMD5Password.toString());
		user.setUserPasswordSalt(userSaltPassword.toString());
		int userId = iUserService.addAndGetId(user);
		if (userId != 0) {
			mv.addObject("result", "sucesse");
		}
		return mv;
	}
	
	//转到用户登录
	@RequestMapping("/user/toLogin")
	public ModelAndView toLogin(){
		return new ModelAndView("login");
	}
	//用户登录
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public ModelAndView login(User user){
		ModelAndView mv = new ModelAndView("index");
		Subject currenUser= SecurityUtils.getSubject();
		if (!currenUser.isAuthenticated()) {
			UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
			try {
				currenUser.login(token);
			}  catch ( UnknownAccountException uae ) { //用户名未知...  
				logger.info("用户名不存在");
				mv.addObject("user", user);
				mv.setViewName("login");
			} catch ( IncorrectCredentialsException ice ) {//凭据不正确，例如密码不正确 ...  
				logger.info("密码不对");
				mv.addObject("user", user);
				mv.setViewName("login");
			} catch ( LockedAccountException lae ) {
				logger.info("用户名被锁定");
				mv.addObject("user", user);
				mv.setViewName("login");//用户被锁定，例如管理员把某个用户禁用...  
			} catch ( ExcessiveAttemptsException eae ) {
				logger.info("次数多余系统指定次数");
				mv.addObject("user", user);
				mv.setViewName("login");//尝试认证次数多余系统指定次数 ...  
			} catch ( AuthenticationException ae ) {  
				ae.printStackTrace();
				logger.info("其他未指定异常  ");
				mv.addObject("user", user);
				mv.setViewName("login");
			//其他未指定异常  
			}  
		}
		return mv;
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		return new ModelAndView("index");
	}
}
