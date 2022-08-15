package com.zjy.springbootadmin.service.impl;

import com.zjy.springbootadmin.entity.User;
import com.zjy.springbootadmin.mapper.UserMapper;
import com.zjy.springbootadmin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjy
 * @since 2022-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
