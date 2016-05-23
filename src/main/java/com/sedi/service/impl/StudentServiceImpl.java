package com.sedi.service.impl;

import com.sedi.entity.Student;
import com.sedi.repository.StudentRepository;
import com.sedi.service.StudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
@Component("studentService")
@Scope("prototype")
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentRepository studentRepository;

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }
}
