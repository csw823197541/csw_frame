package com.sedi.controller;

import com.sedi.entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * Created by csw on 2016/3/16.
 */
@RestController
@RequestMapping("/api")
public class ViewController {

    @RequestMapping(value = "/goUserInfo", method = RequestMethod.GET)
    public ModelAndView goUserInfo() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/userInfo");
        return modelAndView;
    }

}
