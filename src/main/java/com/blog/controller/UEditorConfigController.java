package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.blog.entity.blog.UploadFile;
import com.blog.entity.user.User;
import com.blog.service.blog.IUploadFileService;
import com.blog.utils.Base64Util;
import com.blog.utils.ThreadPoolExecutorUtil;

@Controller
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UEditorConfigController extends BaseController {

	@Value("${ali.oss.endpoint}")
	private String endpoint;
	@Value("${ali.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${ali.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${ali.oss.bucketName}")
	private String bucketName;

	@Autowired
	private IUploadFileService iUploadFileService;

	// multipart文件上传
	@RequestMapping(value = "/file")
	public ModelAndView uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, HttpSession session,
			HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		ModelAndView mv = new ModelAndView();
		if (uploadFile.isEmpty()) {
			mv.addObject("state", "上传文件不能为空");
			logger.info("上传文件不能为空");
		} else {
			String originalFilename = uploadFile.getOriginalFilename();
			String uploadName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
					+ String.format("%06d", user.getId())
					+ originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
			String path = new SimpleDateFormat("yyyyMMdd").format(new Date());
			int fileType = 0;
			if (uploadFile.getContentType().contains("image")) {
				path = "image/" + user.getId() + "/" + path;
			} else if (uploadFile.getContentType().contains("video")) {
				uploadName = originalFilename;
				path = "video/" + user.getId() + "/" + path;
				fileType = 1;
			} else {
				uploadName = originalFilename;
				path = "file/" + user.getId() + "/" + path;
				fileType = 2;
			}

			// 上传到OSS上
			String key = path + "/" + uploadName;
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			ThreadPoolExecutorUtil
					.doUpLoad(new uploadOSS(ossClient, bucketName, key, null, uploadFile.getInputStream()));
			Thread.sleep(1000);
			// 保存到数据库
			UploadFile createUploadFile = new UploadFile();
			createUploadFile.setUploadCreateTime(new Date());
			createUploadFile.setUploadOriginalName(originalFilename);
			createUploadFile.setUploadSize(uploadFile.getSize());
			createUploadFile.setUploadTitleName(uploadName);
			createUploadFile.setUploadType(uploadFile.getContentType());
			createUploadFile.setUploadUserId(user.getId());
			createUploadFile.setFileType(fileType);
			createUploadFile.setUploadUrl(key);
			iUploadFileService.add(createUploadFile);

			mv.addObject("state", "SUCCESS");
			mv.addObject("original", originalFilename);
			mv.addObject("size", uploadFile.getSize());
			mv.addObject("title", uploadName);
			mv.addObject("type", uploadFile.getContentType());
			mv.addObject("url", getUrl(key));
		}
		return mv;
	}

	private String getUrl(String key) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 由于当前返回的外链是用户预览页面，为了页面缓存，这里设置过期时间长点，一个月
		Date expiration = new Date(new Date().getTime() + 30 * 24 * 3600 * 1000);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
		generatePresignedUrlRequest.setExpiration(expiration);
		URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}

	// 涂鸦上传,百度上传上来的是base64
	@RequestMapping("scrawl")
	private ModelAndView uploadScrawl(@RequestParam("uploadFile") String base64Str) throws InterruptedException {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		ModelAndView mv = new ModelAndView();
		String path = "img/" + user.getId() + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		String uploadName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
				+ String.format("%06d", user.getId()) + ".jpg";
		File base64ToImgFile = Base64Util.base64ToImgFile(base64Str, uploadName);
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		// 上传到OSS
		String key = path + "/" + uploadName;
		ThreadPoolExecutorUtil.doUpLoad(new uploadOSS(ossClient, bucketName, key, base64ToImgFile, null));
		Thread.sleep(1000);
		// 保存到数据库
		UploadFile createUploadFile = new UploadFile();
		createUploadFile.setUploadCreateTime(new Date());
		createUploadFile.setUploadOriginalName("base64");
		createUploadFile.setUploadSize(base64ToImgFile.length());
		createUploadFile.setUploadTitleName(uploadName);
		createUploadFile.setUploadType("base64");
		createUploadFile.setUploadUserId(user.getId());
		createUploadFile.setFileType(0);
		createUploadFile.setUploadUrl(key);
		iUploadFileService.add(createUploadFile);

		mv.addObject("state", "SUCCESS");
		mv.addObject("original", "base64");
		mv.addObject("size", base64ToImgFile.length());
		mv.addObject("title", uploadName);
		mv.addObject("type", "base64");
		mv.addObject("url", getUrl(key));
		return mv;
	}

	public class uploadOSS implements Runnable {
		private OSSClient ossClient = null;
		private String bucketName = null;
		private String key = null;
		private File file = null;
		private InputStream in = null;

		public uploadOSS(OSSClient ossClient, String bucketName, String key, File file, InputStream in) {
			this.ossClient = ossClient;
			this.bucketName = bucketName;
			this.key = key;
			this.file = file;
			this.in = in;
		}

		@Override
		public void run() {
			try {
				if (file != null) {
					ossClient.putObject(bucketName, key, file);
				}
				if (in != null) {
					ossClient.putObject(bucketName, key, in);
				}
			} catch (OSSException e) {
				throw new OSSException();
			} catch (ClientException e) {
				throw new ClientException();
			} finally {
				ossClient.shutdown();
			}
		}
	}

	// Ueditor图片列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listImage(int fileType, int start, int size) {
		UploadFile queryUploadFile = new UploadFile();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		queryUploadFile.setUploadUserId(user.getId());
		queryUploadFile.setFileType(fileType);
		List<UploadFile> uploadFiles = iUploadFileService.getAlls(queryUploadFile);
		List<BaiduUediter> list = new ArrayList<>();
		for (UploadFile uploadFile : uploadFiles) {
			String uploadUrl = uploadFile.getUploadUrl();
			BaiduUediter uediterStatue = new BaiduUediter();
			uediterStatue.setState("SUCCESS");
			uediterStatue.setUrl(getUrl(uploadUrl));
			list.add(uediterStatue);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("start", 0);
		mv.addObject("total", uploadFiles.size());
		mv.addObject("state", "SUCCESS");
		mv.addObject("list", list);
		return mv;
	}

	// Ueditor远程抓取
	@RequestMapping(value = "/catchimage", method = RequestMethod.POST)
	public ModelAndView catchimage(HttpServletRequest request) {
		String source = getParam(request).get("source[]");
		String url = source.substring(source.indexOf("com/"), source.indexOf("?")).substring(4);
		logger.info(url);
		/*
		 * {"state": "SUCCESS", list: [{"state": "SUCCESS","size":
		 * "281359","source":
		 * "http://blog-uploadfile-test.oss-cn-beijing.aliyuncs.com/image/1/20180619/20180619095810163000001.gif?Expires=1529569315&OSSAccessKeyId=LTAI0GiBUUs19jRn&Signature=oOObTEjjTZ1FupVxORZFXgdlL2Q%3D"
		 * ,"title": "1529565741608042285.gif","url":
		 * "/ueditor/jsp/upload/image/20180621/1529565741608042285.gif"} ]}
		 */
		List<BaiduUediter> list = new ArrayList<>();
		BaiduUediter uediterStatue = new BaiduUediter();
		uediterStatue.setState("SUCCESS");
		uediterStatue.setSource(source);
		uediterStatue.setUrl(getUrl(url));
		list.add(uediterStatue);
		ModelAndView mv = new ModelAndView();
		mv.addObject("state", "SUCCESS");
		mv.addObject("list", list);
		return mv;
	}

	public class BaiduUediter implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String state;
		private String url;
		private int size;
		private String source;

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

}
