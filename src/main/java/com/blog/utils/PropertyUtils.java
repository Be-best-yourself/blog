package com.blog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载properties配置文件工具
 * @author zhang
 *
 */
public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

/*	public static void main(String[] args) {
		Properties pro = loadingProperty("ali.properties");
		String property = pro.getProperty("ali.sms.accessKeyId");
		System.out.println(property);
	}
*/
	public static Properties loadingProperty(String propertyName) {
		Properties props = new Properties();
		InputStream in=null;
		try {
			in = PropertyUtils.class.getClassLoader().getResourceAsStream(propertyName);
			props.load(in);
		} catch (IOException e) {
			logger.info(propertyName+"文件打开异常");
			return null;
		}finally{
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("流关闭异常");
				}
			}
		}
		return props;
	}
}
