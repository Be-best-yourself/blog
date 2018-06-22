package com.blog.dao.blog;

import com.blog.dao.IBaseDao;
import com.blog.entity.blog.Blog;

public interface IBlogDao extends IBaseDao<Blog>{

	void updateBlogStautsByClassifyId(int blogStatus, int classifyId);

	Blog selectByIdAndUserId(int id, int userId);
}