package com.zfw.ali;

import java.net.URL;
import java.util.Date;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

public class TestAliOSS {

	// Endpoint以杭州为例，其它Region请按实际情况填写。
	static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
	// https://ram.console.aliyun.com 创建RAM账号。
	static String accessKeyId = "LTAI0GiBUUs19jRn";
	static String accessKeySecret = "npk3NLqbo87KfQhQoM1OIdg9jDJlIe";

	public static void main(String[] args) {

		String url = getUrl("image/120180619/20180619091559157000001.gif");
		System.out.println(url);
	}
	private static String getUrl(String key){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =new GeneratePresignedUrlRequest("blog-uploadfile-test", key);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
