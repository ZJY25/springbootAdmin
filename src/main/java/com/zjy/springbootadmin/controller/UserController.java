package com.zjy.springbootadmin.controller;

import com.zjy.springbootadmin.entity.User;
import com.zjy.springbootadmin.mapper.UserMapper;
import com.zjy.springbootadmin.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    //查询所有数据
    @GetMapping("")
    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    //分页查询
    //select * from table limit {开始记录的下标},{查询结果条数}
    @GetMapping("/page")
    public Map<String, Object> selectByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
        //在service层中处理查询参数
        return userService.selectByPage(pageNum, pageSize, username);
    }





    //增加或修改
    //mybatis方式实现
//    @PostMapping
//    public Integer save(@RequestBody User user){return userService.saveUser(user);}
    //mybatis-plus方式实现
    @PostMapping
    public Boolean saveWithMP(@RequestBody User user){return userService.saveUserWithMP(user);}



    //通过id删除记录
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

}
