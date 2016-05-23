package com.sedi.service;

import com.sedi.entity.Student;

import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
public interface StudentService {

    Student create(Student student);
    List<Student> getAll();
}
