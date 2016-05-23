package com.sedi.controller;

import com.sedi.entity.Student;
import com.sedi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView create(Student student) {

        System.out.println(student.getUserName());
        studentService.create(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", "添加成功！");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> listStudents(Student student) {
        System.out.println("查找所有学生");

        return studentService.getAll();
    }
}
