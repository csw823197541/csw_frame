package com.sedi.exception;

import com.sedi.constant.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Created by csw on 2016/5/30 9:24.
 * explain：
 */
public class StudentNotFoundException extends PlatformException {

    public StudentNotFoundException(String description) {

        super(HttpStatus.NOT_FOUND, description, ErrorCode.USERNOTFOUND);
    }
}
