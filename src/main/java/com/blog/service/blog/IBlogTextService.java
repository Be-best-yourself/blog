package com.blog.service.blog;

import com.blog.entity.blog.BlogText;

public interface IBlogTextService{
	int deleteById(Integer id);

    int add(BlogText t);

    int addAndGetId(BlogText t);

    BlogText getById(Integer id);

    int update(BlogText t);

    int updateByPrimaryKeyWithBLOBs(BlogText t);
    
    int updateOrAdd(BlogText t);
}
