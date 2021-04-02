package com.ghh.sample.controller;

import com.ghh.sample.mapper.UserMapper;
import com.ghh.sample.model.entity.User;
import com.ghh.sample.model.vo.ResponseData;
import com.ghh.sample.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Api(tags = "Application Level api")
public class ApplicationController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    @ApiOperation("test")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/index");
        return mv;
    }

    @PostMapping("/user")
    @ResponseBody
    @ApiOperation("测试创建用户")
    public ResponseData testCreate() {
        User u = new User();
        u.setName("guhaihua");
        userService.createUser(u);
        return ResponseData.ok(u);
    }

    @GetMapping("/user/list")
    @ResponseBody
    public ResponseData listUsers(HttpSession session) {
        PageHelper.startPage(1, 10);
        return ResponseData.ok(userService.listUsers());
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseData testAAA() {
        Date d = new Date();
        return ResponseData.ok(d);
    }

    @GetMapping("/download")
    @ApiOperation("下载")
    public ResponseEntity<InputStreamSource> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "test.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(userService.genExcel()));
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/testInsertList")
    @ResponseBody
    public ResponseData testInsertList() {
        List<User> ul = new ArrayList<>();
        User u1 = new User();
        u1.setId(UUID.randomUUID().toString());
        u1.setName("name1");
        ul.add(u1);

        User u2 = new User();
        u2.setId(UUID.randomUUID().toString());
        u2.setName("name1");
        ul.add(u2);

        try {
            int i = userMapper.insertList(ul);
            return ResponseData.ok(i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(1, e.getMessage());
        }
    }
}
