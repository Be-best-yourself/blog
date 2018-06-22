package com.zfw.utils;

public class StringIndexOf {
	public static void main(String[] args) {
		String a="aa.jpg.bak";
		int indexOf = a.indexOf(".");
		String substring = a.substring(a.indexOf("."), a.length());
		System.out.println(substring);
	}
}
