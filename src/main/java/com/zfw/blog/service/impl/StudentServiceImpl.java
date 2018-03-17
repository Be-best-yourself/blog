package com.zfw.blog.service.impl;

import org.springframework.stereotype.Service;

import com.zfw.blog.entity.student.Student;
import com.zfw.blog.service.IStudentService;

@Service(IStudentService.SERVICE_NAME)
public class StudentServiceImpl extends BaseServiceImpl<Student> implements IStudentService {


}