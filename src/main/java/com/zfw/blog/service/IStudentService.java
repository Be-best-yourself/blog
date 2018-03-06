package com.zfw.blog.service;

import com.zfw.blog.entity.Student;

public interface IStudentService extends IBaseService<Student, Integer, String> {
	public static final String SERVICE_NAME="studentService";
	public void addStudent(Student student);
	public Student findStudentById(int id);
}
