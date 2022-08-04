package com.zjy.springbootadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zjy.springbootadmin.mapper.UserMapper;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @Resource
    private UserMapper userMapper;


    @RequestMapping("/say")
    public String say() {
        return "test";
    }

}
