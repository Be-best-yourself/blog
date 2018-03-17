package com.zfw.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zfw.blog.dao.IBaseDao;
import com.zfw.blog.service.IBaseService;

/**
 * BaseServiceImpl 简单的增删改查
 * @author zhang
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements IBaseService<T> {
	@Autowired
	private IBaseDao<T> iBaseDao;
	@Override
	public int deleteById(Integer id) {
		return iBaseDao.deleteByPrimaryKey(id);
	}

	@Override
	public int add(T t) {
		return iBaseDao.insert(t);
	}

	@Override
	public int addAndGetId(T t) {
		return iBaseDao.insertSelective(t);
	}

	@Override
	public T getById(Integer id) {
		return iBaseDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateAndGetId(T t) {
		return iBaseDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int update(T t) {
		return iBaseDao.updateByPrimaryKey(t);
	}


}
