package com.zfw.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value="test")
	public void test1(){
		logger.info("aaaaaaa");
	}

}
