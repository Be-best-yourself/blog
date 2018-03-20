package com.zfw.blog.service;

import java.util.List;

public interface IBaseService<T>{
	int deleteById(Integer id);

    int add(T t);

    int addAndGetId(T t);

    T getById(Integer id);

    int updateAndGetId(T t);

    int update(T t);
    
    List<T> getAlls(T t);
}
