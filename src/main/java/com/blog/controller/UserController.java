package com.blog.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
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
		ModelAndView mv = new ModelAndView();
		Subject currenUser = SecurityUtils.getSubject();
		if (currenUser.getPrincipal() instanceof User) {
			// TODO 跳转到网站首页URL
			mv.setViewName("redirect:/editor/toUEditor");
		} else {
			mv.setViewName("redirect:/user/toLogin");
		}
		return mv;
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

	// 查询用户名是否存在
	@RequestMapping("/getUserByUserName")
	public ModelAndView getUserByUserName(String userName) {
		ModelAndView mv = new ModelAndView();
		User user = iUserService.getUserForUserName(userName);
		if (user != null) {
			mv.addObject("result", true);
		} else {
			mv.addObject("result", false);
		}
		return mv;
	}

	// 查询手机号是否存在
	@RequestMapping(value = "/getUserByPhoneNum", method = RequestMethod.GET)
	public ModelAndView getUserByPhoneNum(String phoneNum) {
		ModelAndView mv = new ModelAndView();
		User user = iUserService.getUserByPhoneNum(phoneNum);
		if (user != null) {
			mv.addObject("result", true);
		} else {
			mv.addObject("result", false);
		}
		return mv;
	}

	// 用户注册
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ModelAndView registerUser(String userName, String password, String validateCode, String phoneNum) {
		ModelAndView mv = new ModelAndView();
		Session session = SecurityUtils.getSubject().getSession();
		try {
			if (session.getAttribute(SystemController.PHONENUM+session.getId()).equals(phoneNum)
					&& session.getAttribute(SystemController.VALIDATECODE+session.getId()).equals(validateCode)) {
				mv.addObject("result", false);
				return mv;
			}
			SimpleHash userSaltPassword = new SimpleHash("MD5", password, ByteSource.Util.bytes(userName), 2);
			User user = new User();
			user.setUserName(userName);
			user.setUserPassword(password);
			user.setUserPhone(phoneNum);
			user.setUserPasswordSalt(userSaltPassword.toString());
			iUserService.addAndGetId(user);
			mv.addObject("result", true);
		} catch (UnknownAlgorithmException e) {
			mv.addObject("result", false);
		} catch (CodecException e) {
			mv.addObject("result", false);
		}
		return mv;
	}
	
	// 重置密码
	@RequestMapping(value = "/user/resetpwd", method = RequestMethod.POST)
	public ModelAndView resetPassword(String password, String validateCode, String phoneNum) {
		ModelAndView mv = new ModelAndView();
		Session session = SecurityUtils.getSubject().getSession();
		try {
			if (session.getAttribute(SystemController.PHONENUM+session.getId()).equals(phoneNum)
					&& session.getAttribute(SystemController.VALIDATECODE+session.getId()).equals(validateCode)) {
				mv.addObject("result", false);
				return mv;
			}
			User updateUser = iUserService.getUserByPhoneNum(phoneNum);
			updateUser.setUserPassword(password);
			SimpleHash userSaltPassword = new SimpleHash("MD5", password, ByteSource.Util.bytes(updateUser.getUserName()), 2);
			updateUser.setUserPasswordSalt(userSaltPassword.toString());
			updateUser.setUserModifyTime(new Date());
			iUserService.update(updateUser);
			mv.addObject("result", true);
		} catch (InvalidSessionException e) {
			e.printStackTrace();
			mv.addObject("result", false);
		} catch (UnknownAlgorithmException e) {
			e.printStackTrace();
			mv.addObject("result", false);
		} catch (CodecException e) {
			e.printStackTrace();
			mv.addObject("result", false);
		}
		return mv;
	}
	
	

	// 转到用户登录页面
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
	
	@RequestMapping("forgetpassword")
	public ModelAndView forgetPassword(){
		return new ModelAndView("forgetpassword");
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
