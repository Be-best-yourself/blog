package com.zfw.blog.service;

import java.io.Serializable;

public interface IBaseService<T,ID extends Serializable,TN> {
	T findById(ID id,TN tableName);
}
