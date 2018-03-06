package com.zfw.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfw.blog.dao.IStudentDao;
import com.zfw.blog.entity.Student;
import com.zfw.blog.service.IStudentService;
@Service(IStudentService.SERVICE_NAME)
public class StudentServiceImpl extends BaseServiceImpl<Student, Integer, String> implements IStudentService {
	@Autowired
	private IStudentDao iStudentDao;
	public void addStudent(Student student) {
		iStudentDao.insert(student);
	}

	public Student findStudentById(int id) {
		return iStudentDao.selectStudentById(id);
	}

}
