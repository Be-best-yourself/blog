package com.blog.service.blog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.blog.IBlogDao;
import com.blog.entity.blog.Blog;
import com.blog.service.blog.IBlogService;
import com.blog.service.impl.BaseServiceImpl;

@Service
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements IBlogService {
	@Autowired
	private IBlogDao iBlogDao;

	@Override
	public void updateBlogStautsByClassifyId(int blogStatus, int classifyId) {
		iBlogDao.updateBlogStautsByClassifyId(blogStatus,classifyId);
	}
}
