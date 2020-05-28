package com.zfw.jsonp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

public class TestJsonP {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String html="http://localhost:8080/blog/article/40";
		String endpoint="http://oss-cn-beijing.aliyuncs.com";
		String accessKeyId="LTAI0GiBUUs19j";
		String accessKeySecret="npk3NLqbo87KfQhQo1OId9jDJlIe";
		Date expiration=new Date(new Date().getTime()+30000);
		String bucketName="blog-uploadfile-test";
		replaceHtml(html, endpoint, accessKeyId, accessKeySecret, expiration, bucketName);
	}

	public static String replaceHtml(String html, String endpoint, String accessKeyId, String accessKeySecret, Date expiration,
			String bucketName) throws MalformedURLException, IOException {
		Document document = Jsoup.parse(new URL(html),3000);
		Elements imgTags = document.getElementsByTag("img");
		for (Element imgTag : imgTags) {
			String srcString = imgTag.attr("src");
			String ossUrl = endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1];
			if (srcString.contains(ossUrl)) {
				System.out.println(srcString);
				srcString = srcString.replaceAll(ossUrl + "/", "");
				String ossKey = srcString.substring(0, srcString.indexOf("?"));
				String url = getUrl(ossKey, endpoint, accessKeyId, accessKeySecret, expiration, bucketName);
				System.err.println(url);
				imgTag.attr("src", url);
			}
		}
		return document.toString();
	}

	private static String getUrl(String key, String endpoint, String accessKeyId, String accessKeySecret,
			Date expiration, String bucketName) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 设置URL过期时间为1小时
		// Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
		generatePresignedUrlRequest.setExpiration(expiration);
		URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}
}
