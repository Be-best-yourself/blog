package com.zfw.blog.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.zfw.blog.dao.IBaseDao;
import com.zfw.blog.service.IBaseService;

public class BaseServiceImpl<T,ID extends Serializable,TN> implements IBaseService<T,ID,TN> {

	@Autowired
	private IBaseDao<T,ID,TN> iBaseDao;
	public T findById(ID id, TN tableName) {
		return iBaseDao.findById(id, tableName);
	}

}
