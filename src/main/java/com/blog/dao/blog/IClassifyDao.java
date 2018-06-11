package com.blog.dao.blog;

import java.util.List;

import com.blog.dao.IBaseDao;
import com.blog.entity.blog.Classify;

public interface IClassifyDao extends IBaseDao<Classify>{

	List<Classify> selectByLikeClassifyPath(String likeClassifyPath, int userId);
}