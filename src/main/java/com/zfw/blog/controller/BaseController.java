package com.zfw.blog.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.zfw.blog.entity.SessionUser;

/**
 * 基本Controller,放入logger
 */
public class BaseController {
	
	protected static final String SESSIONUSER = "SESSIONUSER";
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession httpSession = null;

	@ModelAttribute
	private void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	
	/**
	 * 将request中的参数封装成map集合
	 * @param httpServletRequest
	 * @return
	 */
	protected Map<String, String> getParam() {
		Map<String, String> paramMap = new HashMap<String, String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			paramMap.put(parameterName, request.getParameter(parameterName));
		}
		return paramMap;
	}

	/**
	 * 设置Session
	 * @param AttributeName
	 * @param AttributeObj
	 */
	protected void setSessionAttribute(String AttributeName, Object AttributeObj) {
		httpSession.setAttribute(AttributeName, AttributeObj);
	}

	/**
	 * 通过attributeName获取Session中的对象
	 * @param AttributeName
	 * @return
	 */
	protected Object getSessionByAttributeName(String AttributeName) {
		return httpSession.getAttribute(AttributeName) == null ? null : httpSession.getAttribute(AttributeName);
	}

	/**
	 * 获取SessionUser，加上ModelAttribute将SessionUser自动放入modelmap中
	 * @return sessionUser
	 */
	@ModelAttribute("sessionUser")
	protected SessionUser getSessionUser() {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute(SESSIONUSER);
		return sessionUser != null ? sessionUser : null;
	}

	/**
	 * 得到用户设备
	 * @return
	 */
	protected String getUserAgent(){
		return request.getHeader("User-Agent");
	}
}
