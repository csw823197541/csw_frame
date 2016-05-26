package com.sedi.controller;

import com.sedi.component.BaseComponent;
import com.sedi.constant.ErrorCode;
import com.sedi.exception.ErrorModel;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by csw on 2016/5/26 18:13.
 * <p/>
 * explain：该类为所有API Controller的父类，用以统一处理异常
 */
public abstract class BaseController extends BaseComponent {

    private ObjectMapper jsonMapper = new ObjectMapper();

    private StringWriter errors;

    /**
     * 处理RestTemple Caller异常，直接返回错误的信息
     * @param ex
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @ExceptionHandler(HttpStatusCodeException.class)
    public ErrorModel handleException(HttpStatusCodeException ex,
                                      HttpServletResponse httpServletResponse) throws IOException {

        errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        logger.error(String.format("Http client error happened, response body [%s]", errors));

        httpServletResponse.setCharacterEncoding("UFT-8");
        httpServletResponse.setStatus(ex.getStatusCode().value());

        ErrorModel errorModel = jsonMapper.readValue(ex.getResponseBodyAsString(), ErrorModel.class);

        return errorModel;
    }

    /**
     * can't read json exception
     * @param ex
     * @param httpServletResponse
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorModel handleException(HttpMessageNotReadableException ex,
                                      HttpServletResponse httpServletResponse) {

        errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        logger.debug(String.format("Http message not readable exception [%s]", errors));

        httpServletResponse.setCharacterEncoding("UFT-8");
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        ErrorModel errorModel = new ErrorModel();
        errorModel.setErrorCode(ErrorCode.UNKNOWNERROR);
        errorModel.setExceptionResult(ex.getMessage() + "not a readable exception");

        return errorModel;
    }

    @ExceptionHandler(Exception.class)
    public ErrorModel handleException(Exception ex,
                                      HttpServletResponse httpServletResponse) {

        errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        logger.error(String.format("happened something unknown exception [%s]", errors));

        httpServletResponse.setCharacterEncoding("UFT-8");
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        ErrorModel errorModel = new ErrorModel();
        errorModel.setExceptionResult(ex.getMessage());
        errorModel.setErrorCode(ErrorCode.UNKNOWNERROR);

        return errorModel;
    }
}
