package com.zfw.blog.dao;

import java.io.Serializable;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
public interface IBaseDao<T,ID extends Serializable,TN> {
	@Select(value={"select * from test_student where id = #{id}"})
	T findById(ID id,TN tableName);
}