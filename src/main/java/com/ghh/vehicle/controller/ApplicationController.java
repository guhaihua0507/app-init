package com.ghh.sample.controller;

import com.ghh.sample.model.entity.User;
import com.ghh.sample.model.vo.ResponseData;
import com.ghh.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/index");
        return mv;
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseData testCreate() {
        User u = new User();
        u.setName("guhaihua");
        userService.createUser(u);
        return ResponseData.ok(u);
    }
}
