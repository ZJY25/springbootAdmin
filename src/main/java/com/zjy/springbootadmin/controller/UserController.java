package com.zjy.springbootadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjy.springbootadmin.entity.User;
import com.zjy.springbootadmin.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "UserController接口")
public class UserController {
    @Resource
    private UserService userService;

    //查询所有数据
    @GetMapping("")
    public List<User> selectAll() {
//        //自己写的查询所有数据
//        return userMapper.selectAll();

        //MP的service方法
        return userService.list();
    }


    //分页查询

//    //自己实现的分页查询
//    //select * from table limit {开始记录的下标},{查询结果条数}
//    @GetMapping("/page")
//    public Map<String, Object> selectByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username){
//        //在service层中处理查询参数
//        return userService.selectByPage(pageNum, pageSize, username);
//    }


    //mybatis的分页查询
    @GetMapping("/page")
    public IPage<User> selectByPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam String username,           //required = false 可以使得无参数时null
                                    @RequestParam String email,
                                    @RequestParam String address){           //defaultValue = ""可以使得无参数时为""
        return userService.selectMyPage(pageNum, pageSize, username, email, address);
    }





    //增加或修改

    //mybatis方式实现
//    @PostMapping
//    public Integer save(@RequestBody User user){return userService.saveUser(user);}


    //mybatis-plus方式实现
    @PostMapping
    public Boolean saveWithMP(@RequestBody User user){return userService.saveUserWithMP(user);}



    //通过id删除记录
    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable Integer id){
//        // 自己写的deleteById
//        return userMapper.deleteById(id);

        //MP的removeById
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }

}
