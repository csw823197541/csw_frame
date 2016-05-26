package com.sedi.controller;

import com.sedi.entity.Student;
import com.sedi.constant.ExceptionCode;
import com.sedi.exception.InvalidOperatorException;
import com.sedi.service.StudentService;
import com.sedi.model.ResultModel;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    public Student create(@RequestParam("userName") String userName,
                          @RequestParam(value = "password", defaultValue = "888888", required = false) String password,
                          @RequestParam(value = "email", required = false) String email,
                          @RequestBody(required = false) Student student) {
        Validate.notNull(userName, "The userName must not be null");
        logger.debug(String.format("create student by userName [%s], password [%s]", userName, password));

        //验证用户名是否存在（或有效性验证）
        if(!studentService.isValidUserName(userName)) {
            throw new InvalidOperatorException("用户名已存在，无效");
        }

        Student saveStu = new Student(userName, password, email);

        saveStu = studentService.create(saveStu);

        return saveStu;
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
