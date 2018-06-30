package com.zfw.ali.copy;

import java.io.File;

import com.aliyun.oss.OSSClient;

public class TestAliOSS {

	// Endpoint以杭州为例，其它Region请按实际情况填写。
	static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
	// https://ram.console.aliyun.com 创建RAM账号。
	static String accessKeyId = "LTAI0GiBUUs19jRn";
	static String accessKeySecret = "npk3NLqbo87KfQhQoM1OIdg9jDJlIe";

	public static void main(String[] args) {

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		ossClient.putObject("blog-uploadfile-test", "imge/file.jpg",
				new File("C:\\Users\\zhang\\Desktop\\图片\\big.jpg"));
		// 关闭Client。
		ossClient.shutdown();
	}
}
