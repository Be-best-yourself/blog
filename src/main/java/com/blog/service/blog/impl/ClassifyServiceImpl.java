package com.blog.service.blog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.blog.IClassifyDao;
import com.blog.entity.blog.Classify;
import com.blog.service.blog.IClassifyService;
import com.blog.service.impl.BaseServiceImpl;
@Service
public class ClassifyServiceImpl extends BaseServiceImpl<Classify> implements IClassifyService{
	@Autowired
	private IClassifyDao iClassifyDao;
	@Override
	public List<Classify> getByLikeClassifyPath(String likeClassifyPath, int userId) {
		return iClassifyDao.selectByLikeClassifyPath(likeClassifyPath,userId);
	}
}
