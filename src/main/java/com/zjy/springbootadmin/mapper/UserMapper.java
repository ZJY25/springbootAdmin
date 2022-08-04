package com.zjy.springbootadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjy.springbootadmin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Component                //配置就放在配置类，@MapperScan("mapper文件夹路径")
//@Mapper                   //不用放在执行类
public interface UserMapper extends BaseMapper<User> {

    //查询记录总数
    @Select("select count(*) from sys_user where username like concat('%', #{username}, '%')")
    Integer selectTotal(@Param("username") String username);

    //查询所有用户
    @Select("select * from sys_user")
    List<User> selectAll();

//    List<User> selectByParameter();   //根据参数查询用户


    //带limit的查询语句
    @Select("select * from sys_user where username like concat('%', #{username}, '%') limit #{pageNum}, #{pageSize}")
    List<User> selectByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);


    //增加一条记录
    @Insert("insert into sys_user (username, password, nickname, email, phone, address) " +
            "values (#{username}, #{password}, #{nickname},  #{email}, #{phone}, #{address})")
    Integer insertUser(User user);





    //根据id删除记录
    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);               //利用id参数删除用户(真删除



//    @Update("update sys_user set nickname = #{nickname}, address = #{address} where id = #{id}")    //①直接注释运行sql语句
    Integer update(User user);
}
