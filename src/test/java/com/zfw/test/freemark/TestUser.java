package com.zfw.test.freemark;

import java.io.Serializable;

public class TestUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String passWord;
	private String realName;
	private Integer age;
	private String addr;
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getRealName() {
		return realName;
	}
	public Integer getAge() {
		return age;
	}
	public String getAddr() {
		return addr;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
