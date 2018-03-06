package com.zfw.test.freemark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestExcel {

	public static void main(String[] args) throws Exception {
		List<TestUser> userList=new ArrayList<TestUser>();
		long a=System.currentTimeMillis();
		TestUser user = new TestUser();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100000; j++) {
				user.setUserName("zhang" + j);
				user.setPassWord(j + "");
				user.setRealName("san" + j);
				user.setAddr("aaaaaaa");
				user.setAge(j);
				userList.add(user);
			}
			Map<String,Object> dataModel=new HashMap<String, Object>();
			dataModel.put("userList", userList);
			templateParseUtil("templates","excel2.ftl",dataModel);
			userList.clear();
			System.out.println("已完成："+(double)i+"%");
		}
		mergeFiles("templates/excel.csv",files);
		long b=System.currentTimeMillis();
		System.out.println("执行时间："+(b-a)/1000+"秒");
	}
	static int i=0;
	static List<String> files=new ArrayList<String>();
	public static String templateParseUtil(String templateDir,String templateName,Map<String,Object> data) throws IOException, TemplateException{
		i++;
		String temFileName = "tmp"+i+".csv";
		files.add(temFileName);
		FileOutputStream out = new FileOutputStream(temFileName);
		OutputStreamWriter writer=new OutputStreamWriter(out, "utf-8");
		Template template=getTemplate(templateDir, templateName);
		template.process(data, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}
	
	public static void mergeFiles(String outFile, List<String> files) {
		System.out.println("合并文件中……");
		int BUFSIZE = 1024 * 8;
		FileChannel outChannel = null;
		FileOutputStream fos=null;
		FileInputStream fis=null;
		try {
			fos = new FileOutputStream(outFile);
			outChannel = fos.getChannel();
			for (String f : files) {
				fis = new FileInputStream(f);
				FileChannel fc = fis.getChannel();
				ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
				while (fc.read(bb) != -1) {
					bb.flip();
					outChannel.write(bb);
					bb.clear();
				}
				fc.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (outChannel != null) {
					outChannel.close();
					for (String f : files) {
						File file=new File(f);
						file.delete();
					}
				}
				System.err.println("文件合并成功……");
			} catch (IOException ignore) {
			}
		}
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
		try {
			Configuration cfg = getConfiguration(templateDir);
			Template template = cfg.getTemplate(templateName,"utf-8");
			return template;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取模板文件时出了问题");
			return null;
		}
	}

}
