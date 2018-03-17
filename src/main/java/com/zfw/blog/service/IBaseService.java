package com.zfw.blog.service;

public interface IBaseService<T>{
	int deleteById(Integer id);

    int add(T t);

    int addAndGetId(T t);

    T getById(Integer id);

    int updateAndGetId(T t);

    int update(T t);
}
