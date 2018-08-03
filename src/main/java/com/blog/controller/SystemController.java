package com.blog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.service.sys.ISMSService;
import com.blog.status.Status;
import com.blog.utils.OSSUtils;
import com.blog.utils.SMSUtils;

@Controller
public class SystemController extends BaseController {
	public static final String PHONENUM = "phoneNum";
	public static final String UUIDKEY = "token";
	public static final String VALIDATECODE = "validateCode";

	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(50);

	@Value("${ali.oss.endpoint}")
	private String endpoint;
	@Value("${ali.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${ali.oss.accessKeySecret}")
	private String secretAccessKey;
	@Value("${ali.oss.bucketName}")
	private String bucketName;

	@Autowired
	private ISMSService iSMSService;

	/**
	 * 
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param r
	 *            模糊半径 区间[1,50] r 越大图片越模糊
	 * @param s
	 *            正态分布的标准差 区间[1,50]s 越大图片越模糊
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/drag/{width}/{height}", method = RequestMethod.GET)
	public ModelAndView getDragPicture(@PathVariable(value = "width") int width,
			@PathVariable(value = "height") int height,
			@RequestParam(value = "r", defaultValue = "0", required = false) int r,
			@RequestParam(value = "s", defaultValue = "0", required = false) int s, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Random rand = new Random();
		int randNum = rand.nextInt(1000) + 1;
		String key = "system/drag-picture/" + randNum + ".jpg";

		Map<String, String> queryParam = new HashMap<>();
		// 图片裁剪 固定宽高 取中心
		queryParam.put("x-oss-process", "image/crop,x_0,y_0,w_" + width + ",h_" + height + ",g_center");
		if (s > 0 && r > 0) {
			queryParam.put("x-oss-process",
					"image/crop,x_0,y_0,w_" + width + ",h_" + height + ",g_center/blur,r_" + r + ",s_" + s);
		}
		String url = OSSUtils.getUrl(key, endpoint, accessKeyId, secretAccessKey,
				new Date(new Date().getTime() + 500000), bucketName, queryParam);

		ModelAndView mv = new ModelAndView();
		mv.addObject("result", url);
		return mv;
	}

	/**
	 * 由服务器生成token，在图片拖动验证之前（点击发送验证码之前）生成， 只对本次点击有效，证明图片拖动验证是由按钮点击触发的，防止恶意发短信
	 * 由页面过来的tokenid,手机号验证时，tokenid为手机号
	 * 
	 * @param tokenid
	 * @return
	 */
	@RequestMapping(value = "getToken", method = RequestMethod.POST)
	public ModelAndView getToken(String tokenid) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + "-" + tokenid;
		Session tokenSession = SecurityUtils.getSubject().getSession();
		tokenSession.setAttribute(UUIDKEY + tokenSession.getId(), uuid);
		// 20秒,销毁此uuid
		scheduledExecutorService.schedule(new RemoveSessionRunable(tokenSession, UUIDKEY + tokenSession.getId()), 20,
				TimeUnit.SECONDS);
		ModelAndView mv = new ModelAndView();
		mv.addObject("token", uuid);
		return mv;
	}

	// 发送验证码
	@RequestMapping(value = "sendSMS", method = RequestMethod.POST)
	public ModelAndView sendSMSvalidateCode(String phoneNum, String token) {
		ModelAndView mv = new ModelAndView();
		Session session = SecurityUtils.getSubject().getSession();
		if (session.getAttribute(UUIDKEY + session.getId()) == null
				|| !session.getAttribute(UUIDKEY + session.getId()).equals(token + "-" + phoneNum)) {
			// 证明这20秒时间内没有拉对图片
			mv.addObject("result", false);
		} else {
			Random rand = new Random();
			int validateCode = rand.nextInt(1000000) + 100000;
			session.setAttribute(VALIDATECODE + session.getId(), validateCode);
			session.setAttribute(PHONENUM + session.getId(), phoneNum);
			// 短信缓存10分钟
			scheduledExecutorService.schedule(new RemoveSessionRunable(session, UUIDKEY + session.getId()), 600,
					TimeUnit.SECONDS);
			Map<String, Object> smsData = new HashMap<>();
			smsData.put("code", validateCode);
			logger.info("发短信了："+phoneNum+"====="+validateCode);
			SMSUtils.sendSMSAndSave(iSMSService, Status.SMS_REGISTER.CODE, Status.SMS_REGISTER.EN_CODE, phoneNum, smsData);
			mv.addObject("result", validateCode);
		}
		return mv;
	}

	/**
	 * 比较验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "compareValidateCode", method = RequestMethod.POST)
	public ModelAndView compareValidateCode(String validateCode) {
		Session session = SecurityUtils.getSubject().getSession();
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute(VALIDATECODE + session.getId()) == null) {
			mv.addObject("result", false);
		} else if (!validateCode.equals(session.getAttribute(VALIDATECODE + session.getId()).toString())) {
			mv.addObject("result", false);
		} else {
			mv.addObject("result", true);
		}
		return mv;
	}

	
	// 发送忘记密码验证码
	@RequestMapping(value = "sendForgetPasswordsms", method = RequestMethod.POST)
	public ModelAndView sendForgetPasswordCode(String phoneNum, String token) {
		ModelAndView mv = new ModelAndView();
		Session session = SecurityUtils.getSubject().getSession();
		if (session.getAttribute(UUIDKEY + session.getId()) == null
				|| !session.getAttribute(UUIDKEY + session.getId()).equals(token + "-" + phoneNum)) {
			// 证明这20秒时间内没有拉对图片
			mv.addObject("result", false);
		} else {
			Random rand = new Random();
			int validateCode = rand.nextInt(1000000) + 100000;
			session.setAttribute(VALIDATECODE + session.getId(), validateCode);
			session.setAttribute(PHONENUM + session.getId(), phoneNum);
			// 短信缓存2分钟
			scheduledExecutorService.schedule(new RemoveSessionRunable(session, UUIDKEY + session.getId()), 120,
					TimeUnit.SECONDS);
			Map<String, Object> smsData = new HashMap<>();
			smsData.put("code", validateCode);
			logger.info("发短信了："+phoneNum+"====="+validateCode);
			SMSUtils.sendSMSAndSave(iSMSService, Status.SMS_FORGETPASSWORD.CODE, Status.SMS_FORGETPASSWORD.EN_CODE, phoneNum, smsData);
			mv.addObject("result", validateCode);
		}
		return mv;
	}

	public class RemoveSessionRunable implements Runnable {
		Session session = null;
		String key = null;

		public RemoveSessionRunable(Session session, String key) {
			this.session = session;
			this.key = key;
		}

		@Override
		public void run() {
			logger.info("session删除：key=" + key + "+++++value=" + session.getAttribute(key));
			session.removeAttribute(key);
		}

	}
}
