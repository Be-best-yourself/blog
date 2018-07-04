package com.blog.utils;

import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

public class OSSUtils {
	/**
	 * 替换页面中oss外链失效时间，仅对img标签
	 * @param html
	 * @param endpoint
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param expiration
	 * @param bucketName
	 * @return
	 */
	public static String replaceHtml(String html, String endpoint, String accessKeyId, String accessKeySecret,
			Date expiration, String bucketName){
		Document document = Jsoup.parse(html);
		Elements imgTags = document.getElementsByTag("img");
		for (Element imgTag : imgTags) {
			String srcString = imgTag.attr("src");
			String ossUrl = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1];
			if (srcString.contains(ossUrl)) {
				srcString = srcString.replaceAll(ossUrl + "/", "");
				String ossKey = srcString.substring(0, srcString.indexOf("?"));
				String url = getUrl(ossKey, endpoint, accessKeyId, accessKeySecret, expiration, bucketName);
				imgTag.attr("src", url);
			}
		}
		return document.toString();
	}
	/**
	 * 获取oss外链
	 * @param key 文件路径
	 * @param endpoint
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param expiration 失效时间 long
	 * @param bucketName
	 * @return
	 */
	public static String getUrl(String key, String endpoint, String accessKeyId, String accessKeySecret,
			Date expiration, String bucketName) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
		generatePresignedUrlRequest.setExpiration(expiration);
		URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}
	/**
	 * 获取oss 带参数的外链
	 * @param queryParam 查询参数
	 * @param key 文件路径
	 * @param endpoint
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param expiration 失效时间 long
	 * @param bucketName
	 * @return
	 */
	public static String getUrl(String key, String endpoint, String accessKeyId, String accessKeySecret,
			Date expiration, String bucketName,Map<String, String> queryParam) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
		generatePresignedUrlRequest.setExpiration(expiration);
		generatePresignedUrlRequest.setQueryParameter(queryParam);
		URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}
}
