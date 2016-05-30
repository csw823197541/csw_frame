package com.sedi.utils;

import com.sedi.entity.Student;
import com.sedi.exception.StudentNotFoundException;

/**
 * Created by csw on 2016/5/30 9:12.
 * explain：
 */
public class ValidateUtil {

    public static void notNullStudentValidate(Student student) {

        if(null == student) {
            throw new StudentNotFoundException("用户名不存在");
        }
    }
}
