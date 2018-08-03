package com.blog.status;

public enum Status {
	//enabled disabled 
	
	CLASSIFY_ENABLED(0,"可用","enabled"),
	CLASSIFY_RECYCLE(1,"回收","recycle"),
	
	//blog状态
	BLOG_ENABLED(0,"可用","enabled"),
	BLOG_RECYCLE(1,"回收站","recycle"),
	BLOG_DRAFTS(2,"草稿箱","drafts"),
	
	//文件类型
	FILE_IMAGE(0,"图片","image"),
	FILE_VIDEO(1,"视频","video"),
	FILE_FILE(2,"文件","file"),
	
	//短信类型
	SMS_REGISTER(0,"注册","SMS_140721847"),
	SMS_FORGETPASSWORD(1,"忘记密码","SMS_140726899");
	
	
	//错误代码
	
	public int CODE;
	public String ZH_CODE;
	public String EN_CODE;

	private Status(int CODE, String ZH_CODE,String EN_CODE) {
		this.CODE = CODE;
		this.ZH_CODE = ZH_CODE;
		this.EN_CODE = EN_CODE;
	}

}
