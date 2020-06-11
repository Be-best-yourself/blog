package com.zfw.ali.copy;

import java.io.File;
import java.util.Random;

import com.aliyun.oss.OSSClient;

public class TestAliOSS {

	// Endpoint以杭州为例，其它Region请按实际情况填写。
	static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
	// https://ram.console.aliyun.com 创建RAM账号。
	public static void main(String[] args) throws InterruptedException {
		Random r33=new Random();
		for (int i = 0; i < 6; i++) {
			System.out.println(r33.nextInt(32)+1);
		}
		Thread.sleep(1000);
		System.err.println(r33.nextInt(15)+1);
	}
}
