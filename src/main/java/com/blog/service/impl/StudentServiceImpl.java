package com.blog.service.impl;

import org.springframework.stereotype.Service;

import com.blog.entity.student.Student;
import com.blog.service.IStudentService;

@Service(IStudentService.SERVICE_NAME)
public class StudentServiceImpl extends BaseServiceImpl<Student> implements IStudentService {
}