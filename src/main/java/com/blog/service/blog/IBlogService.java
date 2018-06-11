package com.blog.service.blog;

import com.blog.entity.blog.Blog;
import com.blog.service.IBaseService;

public interface IBlogService extends IBaseService<Blog>{

	void updateBlogStautsByClassifyId(int blogStatus, int classifyId);
}
