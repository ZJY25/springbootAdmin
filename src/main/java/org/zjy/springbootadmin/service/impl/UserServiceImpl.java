package org.zjy.springbootadmin.service.impl;

import org.zjy.springbootadmin.entity.User;
import org.zjy.springbootadmin.mapper.UserMapper;
import org.zjy.springbootadmin.service.IUserService;
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
