package com.zfw.blog.service;

public interface TestService<T> {
	public static final String SERVICE_NAME=Thread.currentThread().getStackTrace()[1].getClassName();
	public T getTById(int id);
}
