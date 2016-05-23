package com.sedi.controller;

import com.sedi.entity.Staff;
import com.sedi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bilibili on 2015/10/23.
 */

@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Staff find(@PathVariable Integer id) {

        return staffService.find(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView save(String name, String password)
    {
        System.out.println("接收到的用户信息："+name+"--"+password);

        ModelAndView mov=new ModelAndView();
        mov.setViewName("/userInfo");
//        mov.addObject("msg", "保存成功了");

        return mov;
    }
}
