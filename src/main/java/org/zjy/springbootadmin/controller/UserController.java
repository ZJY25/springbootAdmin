package org.zjy.springbootadmin.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import org.zjy.springbootadmin.service.IUserService;
import org.zjy.springbootadmin.entity.User;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjy
 * @since 2022-08-15
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    //新增或更新
    @PostMapping
    public Boolean saveWithMP(@RequestBody User user){return userService.saveOrUpdate(user);}

    //通过id删除记录
    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable Integer id){return userService.removeById(id);}

    //查询所有数据
    @GetMapping("")
    public List<User> selectAll() {return userService.list();}

    //查询一条数据
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {return userService.getById(id);}

    //分页查询
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNum, pageSize));
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {return userService.removeBatchByIds(ids);}
}

