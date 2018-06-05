package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("editor")
public class UEditorController extends BaseController{

	@RequestMapping("/toUEditor")
	public ModelAndView toUEditor(){
		return new ModelAndView("ueditor/index");
	}
}
