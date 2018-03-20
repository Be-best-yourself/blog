package com.zfw.blog.dao;

import java.util.List;

/**
 * 封装baseDao，基于当前mybaties代码自动生成的配置工具
 * 
 * @author zhang
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	
	int deleteByPrimaryKey(Integer id);

	int insert(T t);

	int insertSelective(T t);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T t);

	int updateByPrimaryKey(T t);

	List<T> selectAlls(T t);
}
