package com.sedi.service.impl;

import com.sedi.entity.Student;
import com.sedi.repository.StudentRepository;
import com.sedi.service.StudentService;
import org.apache.commons.lang3.Validate;
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

    public Student findOne(Integer id) {

        return studentRepository.findOne(id);
    }

    public Student updateStudentInfo(Student updateStu) {
        Validate.notNull(updateStu, "can't update student info");
        Validate.notNull(updateStu.getId(), "can't update student info, id is null");

        Student saveStu = this.findOne(updateStu.getId());
        saveStu = updateStu.changeInfo(saveStu);

        saveStu = studentRepository.save(saveStu);

        return saveStu;
    }

    public Student deleteStudent(Integer id) {
        Validate.notNull(id, "can't find student, id is null");

        Student student = this.findOne(id);

        Validate.notNull(student, "can't find student, student is none");
        student.setIsDelete(true);
        student = studentRepository.save(student);

        return student;
    }
}
