package com.blog.status;

public enum Status {
	//enabled disabled 
	
	CLASSIFY_ENABLED(0,"可用","enabled"),
	CLASSIFY_RECYCLE(1,"回收","recycle"),
	
	BLOG_ENABLED(0,"可用","enabled"),
	BLOG_RECYCLE(1,"回收站","recycle"),
	BLOG_DRAFTS(2,"草稿箱","drafts");
	
	public int CODE;
	public String ZH_CODE;
	public String EN_CODE;

	private Status(int CODE, String ZH_CODE,String EN_CODE) {
		this.CODE = CODE;
		this.ZH_CODE = ZH_CODE;
		this.EN_CODE = EN_CODE;
	}

}
