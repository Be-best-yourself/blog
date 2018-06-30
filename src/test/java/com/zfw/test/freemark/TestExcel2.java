package com.zfw.test.freemark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class TestExcel2 {

	public static void main(String[] args) throws Exception {
		List<TestUser> userList=new ArrayList<TestUser>();
		TestUser user = new TestUser();
		user.setUserName("zhang");
		user.setPassWord("aaa");
		user.setRealName("san");
		user.setAge(1);
		user.setAddr("aaaaaaa");
		userList.add(user);
		Map<String,Object> dataModel=new HashMap<String, Object>();
		dataModel.put("userList", userList);
		FileOutputStream out = new FileOutputStream("shengcheng.csv");
		OutputStreamWriter writer=new OutputStreamWriter(out, "utf-8");
		//TODO 有问题，路径不对
		Template template=getTemplate("aa","excel3.ftl");
		template.process(dataModel, writer);
		writer.flush();
		writer.close();
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
			e.printStackTrace();
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
	public static Template getTemplate(String templateDir,String templateName){
		String resource = TestExcel2.class.getClassLoader().getResource("templates").getFile();
		try {
			Configuration cfg = getConfiguration(resource+"/"+templateDir);
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
