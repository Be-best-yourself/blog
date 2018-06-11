package com.blog.service.blog;

import java.util.List;

import com.blog.entity.blog.Classify;
import com.blog.service.IBaseService;
public interface IClassifyService extends IBaseService<Classify>{
	List<Classify> getByLikeClassifyPath(String likeClassifyPath,int userId);
}
