package com.blog.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkUtils {
/*
	public static void main(String[] args) throws Exception {
		Map<String, Object> dataModel=new HashMap<>();
		dataModel.put("code", "fdsfsd");
		String processFreemarker = processFreemarker("sms","SMS_138062764.ftl",dataModel);
		System.out.println(processFreemarker);
	}*/
	
	public static String processFreemarker(String templateDir,String templateName,Map<String,Object> dataModel){
		StringWriter out=new StringWriter();
		Template template=getTemplate(templateDir, templateName);
		try {
			template.process(dataModel, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}
	
	/**
	 * 模板目录
	 * @param templateDir
	 * @return
	 * @throws IOException
	 */
	private static Configuration getConfiguration(String templateDir){
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setDefaultEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
			return cfg;
		} catch (IOException e) {
			System.err.println("模板配置目录有问题");
			return null;
		}
	}
	/**
	 * 模板文件
	 * @param templateDir
	 * @param templateName
	 * @return
	 * @throws Exception
	 */
	private static Template getTemplate(String templateDir,String templateName){
		String path = FreemarkUtils.class.getClassLoader().getResource("templates").getFile();
		try {
			Configuration cfg = getConfiguration(path+"/"+templateDir+"/");
			if (cfg==null) {
				return null;
			}
			Template template = cfg.getTemplate(templateName,"utf-8");
			return template;
		} catch (Exception e) {
			System.err.println("获取模板文件时出了问题");
			return null;
		}
	}
}
