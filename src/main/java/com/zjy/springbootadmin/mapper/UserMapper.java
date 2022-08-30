package com.zjy.springbootadmin.mapper;

import com.zjy.springbootadmin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjy
 * @since 2022-08-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
