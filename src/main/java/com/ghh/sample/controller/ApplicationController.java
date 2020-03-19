package com.ghh.sample.controller;

import com.ghh.sample.model.entity.User;
import com.ghh.sample.model.vo.ResponseData;
import com.ghh.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    @GetMapping("/test")
    @ResponseBody
    public ResponseData testAAA() {
        return ResponseData.ok();
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamSource> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "test.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(userService.genExcel()));
    }
}
