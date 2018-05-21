package com.zfw.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5 {

	public static void main(String[] args) {
		String md5Hex = DigestUtils.md5Hex("123456");
		System.out.println(md5Hex);
		SimpleHash simpleHash = new SimpleHash("MD5",ByteSource.Util.bytes("123456"));
		System.out.println(simpleHash);
		
	}
}
