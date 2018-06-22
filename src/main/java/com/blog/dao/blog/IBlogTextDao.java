package com.blog.dao.blog;

import com.blog.entity.blog.BlogText;

public interface IBlogTextDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogText record);

    int insertSelective(BlogText record);

    BlogText selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogText record);

    int updateByPrimaryKeyWithBLOBs(BlogText record);
}