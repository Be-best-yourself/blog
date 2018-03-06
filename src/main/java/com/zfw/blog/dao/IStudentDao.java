package com.zfw.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zfw.blog.entity.Student;

public interface IStudentDao extends IBaseDao<Student, Integer, String>{
	@Select("select * from test_student where id = #{id}")
	Student selectStudentById(int id);
	@Insert("insert into test_student(name,age) values(#{name},#{age})")
	void insert(Student student);

}
