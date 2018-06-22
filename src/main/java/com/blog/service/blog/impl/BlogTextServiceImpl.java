package com.blog.service.blog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.blog.IBlogTextDao;
import com.blog.entity.blog.BlogText;
import com.blog.service.blog.IBlogTextService;
@Service
public class BlogTextServiceImpl implements IBlogTextService{
	@Autowired
	private IBlogTextDao iBlogTextDao;
	@Override
	public int deleteById(Integer id) {
		return iBlogTextDao.deleteByPrimaryKey(id);
	}

	@Override
	public int add(BlogText t) {
		return iBlogTextDao.insert(t);
	}

	@Override
	public int addAndGetId(BlogText t) {
		return iBlogTextDao.insertSelective(t);
	}

	@Override
	public BlogText getById(Integer id) {
		return iBlogTextDao.selectByPrimaryKey(id);
	}

	@Override
	public int update(BlogText t) {
		return iBlogTextDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(BlogText t) {
		return iBlogTextDao.updateByPrimaryKeyWithBLOBs(t);
	}

	@Override
	public int updateOrAdd(BlogText t) {
		int key;
		if (t.getId()!=null) {
			key=iBlogTextDao.updateByPrimaryKeySelective(t);
		}else {
			key=iBlogTextDao.insert(t);
		}
		return key;
	}
}
