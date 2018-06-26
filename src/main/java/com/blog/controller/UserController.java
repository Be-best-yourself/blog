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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("redirect:/user/toLogin");
	}

	@RequestMapping("/isSessionTimeOut")
	public ModelAndView isSessionTimeOut() {
		ModelAndView mv = new ModelAndView();
		Subject currenUser = SecurityUtils.getSubject();
		mv.addObject("result", currenUser.getPrincipal() instanceof User);
		return mv;
	}

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
				ByteSource.Util.bytes(user.getUserName()), 2);
		ModelAndView mv = new ModelAndView();
		user.setUserPassword(userMD5Password.toString());
		user.setUserPasswordSalt(userSaltPassword.toString());
		int userId = iUserService.addAndGetId(user);
		if (userId != 0) {
			mv.addObject("result", "sucesse");
		}
		return mv;
	}

	// 转到用户登录
	@RequestMapping("/user/toLogin")
	public ModelAndView toLogin() {
		return new ModelAndView("login");
	}

	// 用户登录
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(String userName, String userPassword, Boolean rememberMe) {
		ModelAndView mv = new ModelAndView();
		Subject currenUser = SecurityUtils.getSubject();
		if (!currenUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
			token.setRememberMe(rememberMe);
			try {
				currenUser.login(token);
				mv.addObject("result", "true");
			} catch (UnknownAccountException uae) { // 用户名未知...
				logger.info("用户名不存在");
				mv.addObject("result", "用户名不存在");
			} catch (IncorrectCredentialsException ice) {// 凭据不正确，例如密码不正确 ...
				logger.info("密码不对");
				mv.addObject("result", "密码不对");
			} catch (LockedAccountException lae) {
				logger.info("用户名被锁定");
				mv.addObject("result", "用户名被锁定");
			} catch (ExcessiveAttemptsException eae) {
				logger.info("尝试次数过多");
				mv.addObject("result", "尝试次数过多");
			} catch (AuthenticationException ae) {
				logger.info("其他未指定异常  ");
				mv.addObject("result", "登录异常");
			}
		}
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		return new ModelAndView("index");
	}

	@RequestMapping("/toLogin")
	public ModelAndView toLogin(HttpServletRequest request) {
		return new ModelAndView("login");
	}
}
