package com.blog.utils;

import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.blog.entity.sys.SMS;
import com.blog.service.sys.ISMSService;
import com.blog.status.Status;

public class SMSUtils {
	static final String product = "Dysmsapi";
	static final String domain = "dysmsapi.aliyuncs.com";
	/**
	 * 发送短信并保存到数据库
	 * @param iSMSService
	 * @param templateCode 模板code 此处是从阿里控制台得到的
	 * @param phoneNum	手机号
	 * @param smsData	数据
	 * @param accessKeyId 
	 * @param secret
	 * @return
	 */
	public static boolean sendSMSAndSave(ISMSService iSMSService, String templateCode, String phoneNum,
			Map<String, Object> smsData, String accessKeyId, String secret) {
		SendSmsResponse sendSMS = sendSMS(templateCode,phoneNum, smsData, accessKeyId, secret);
		SMS createSMS = new SMS();
		createSMS.setSmsPhone(phoneNum);
		createSMS.setSmsCreateTime(new Date());
		createSMS.setSmsStatus(sendSMS.getCode());
		createSMS.setSmsType(Status.SMS_REGISTER.CODE);
		createSMS.setSmsBody(FreemarkUtils.processFreemarker("sms", templateCode+".ftl", smsData));
		iSMSService.add(createSMS);
		return true;
	}
	/**
	 * 发送短信
	 * @param templateCode 模板code 此处是从阿里控制台得到的
	 * @param phoneNum	手机号
	 * @param smsData	数据
	 * @param accessKeyId 
	 * @param secret
	 * @return
	 */
	public static SendSmsResponse sendSMS(String templateCode, String phoneNum, Map<String, Object> smsData,
			String accessKeyId, String secret) {
		String templateParam = null;
		if (smsData != null) {
			templateParam = JSON.toJSONString(smsData);
		} else {
			return null;
		}
		SendSmsResponse sendSmsResponse = null;
		try {
			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phoneNum);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("小村姑");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam(templateParam);
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sendSmsResponse;

	}
}
