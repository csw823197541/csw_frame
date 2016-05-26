package com.sedi.controller;

import com.sedi.entity.Student;
import com.sedi.model.ExceptionCode;
import com.sedi.service.StudentService;
import com.sedi.model.ResultModel;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    public Student create(@RequestBody Student student) {

        System.out.println(student.getUserName());

        student = studentService.create(student);

        return student;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> listStudents() {
        System.out.println("查找所有学生");

        return studentService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student findStudent(@PathVariable Integer id) {
        Validate.notNull(id, "can't find student, id is null");
        Student student = studentService.findOne(id);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Validate.notNull(id, "can't find student, id is null");
        Validate.notNull(student, "can't update student , student is null");

        student.setId(id);

        student = studentService.updateStudentInfo(student);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultModel deleteStudent(@PathVariable Integer id) {
        Validate.notNull(id, "can't find student, id is null");

        ResultModel resultModel = null;

        Student student = studentService.deleteStudent(id);

        if(student.isDelete()) {
            resultModel = new ResultModel(ExceptionCode.DELETE_SUCCESS);
        } else {
            resultModel = new ResultModel(ExceptionCode.DELETE_FAIL);
        }
        return resultModel;
    }
}
