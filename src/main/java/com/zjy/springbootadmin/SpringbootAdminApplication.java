package com.zjy.springbootadmin;

import com.zjy.springbootadmin.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class SpringbootAdminApplication {

    @Resource
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminApplication.class, args);
    }

    @GetMapping("/app_test")
    public String appTest() {
        return "app_test";
    }
}
