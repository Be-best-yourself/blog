package com.blog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.IBaseDao;
import com.blog.service.IBaseService;

/**
 * BaseServiceImpl 简单的增删改查
 * @author zhang
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements IBaseService<T> {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
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

	@Override
	public List<T> getAlls(T t) {
		return iBaseDao.selectAlls(t);
	}


}
