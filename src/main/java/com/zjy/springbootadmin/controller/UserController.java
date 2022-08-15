package com.zjy.springbootadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.zjy.springbootadmin.service.IUserService;
import com.zjy.springbootadmin.entity.User;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjy
 * @since 2022-08-15
 */
@RestController
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
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String address) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotBlank(username), "username", username);
        queryWrapper.like(Strings.isNotBlank(email), "email", email);
        queryWrapper.like(Strings.isNotBlank(address), "address", address);
        queryWrapper.orderByDesc("id");
        return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {return userService.removeBatchByIds(ids);}
}

