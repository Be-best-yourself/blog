package com.blog.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.blog.entity.SessionUser;

/**
 * 基本Controller,放入logger
 */
public class BaseController {
	
	protected static final String SESSIONUSER = "SESSIONUSER";
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 将request中的参数封装成map集合
	 * @param httpServletRequest
	 * @return
	 */
	protected Map<String, String> getParam(HttpServletRequest request) {
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
	protected void setSessionAttribute(HttpServletRequest request,String AttributeName, Object AttributeObj) {
		getSession(request).setAttribute(AttributeName, AttributeObj);
	}

	private HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	
	/**
	 * 通过attributeName获取Session中的对象
	 * @param AttributeName
	 * @return
	 */
	protected Object getSessionByAttributeName(HttpServletRequest request,String AttributeName) {
		HttpSession httpSession=getSession(request);
		return httpSession.getAttribute(AttributeName) == null ? null : httpSession.getAttribute(AttributeName);
	}

	/**
	 * 获取SessionUser，加上ModelAttribute将SessionUser自动放入modelMap中
	 * @param request
	 * @return sessionUser
	 */
	@ModelAttribute("sessionUser")
	protected SessionUser getSessionUser(HttpServletRequest request) {
		SessionUser sessionUser = (SessionUser) getSession(request).getAttribute(SESSIONUSER);
		return sessionUser != null ? sessionUser : null;
	}

	/**
	 * 得到用户设备
	 * @param request
	 * @return
	 */
	protected String getUserAgent(HttpServletRequest request){
		return request.getHeader("User-Agent");
	}
}
