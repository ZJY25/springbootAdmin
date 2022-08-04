package com.zjy.springbootadmin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.springbootadmin.entity.User;
import com.zjy.springbootadmin.mapper.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserMapper mapper;


//    //用自己写的mapper增改数据
//    public Integer saveUser(User user) {
//        if(user.getId() == null) {         //当用户不存在，新增用户
//            System.out.println("新增");
//            return mapper.insertUser(user);
//        }else {
//            System.out.println("更新");
//            return mapper.update(user);
//        }
//    }


    //使用MP实现增改数据
    public Boolean saveUserWithMP(User user) {
//        //手动判断save or update
//        if(user.getId() == null) {
//            System.out.println("insert");
//            return save(user);                      //MP提供方法，表示插入数据
//        } else {
//            System.out.println("update");           //MP提供方法，表示更新数据
//            return updateById(user);
//        }


        //一行代码解决
        System.out.println("一行代码解决");
        return saveOrUpdate(user);
    }

    //处理分页查询参数
    public Map<String, Object> selectByPage(Integer pageNum, Integer pageSize, String username) {
        //根据pageNumber计算sql语句中的参数。
        //查询第一页(0~pageSize-1)，pageNumber = 1 开始记录下标为 0，查询结果条数为pageSize 不变
        //查询第二页(pageSize~2*pageSize)，pageNumber = 2 开始记录下标为 （2-1）*pageSize
        pageNum = (pageNum - 1) * pageSize;
        Integer count = mapper.selectTotal(username);   //查询总条数
        Map<String, Object> res = new HashMap<>();
        List<User> users = mapper.selectByPage(pageNum, pageSize, username);
        res.put("data", users);
        res.put("total",count);
        return res;
    }
}
