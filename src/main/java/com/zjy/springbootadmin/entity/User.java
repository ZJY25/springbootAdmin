package com.zjy.springbootadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "sys_user")                    //给MP著名该数据在数据库中的表名称
public class User {

     @TableId(type = IdType.AUTO)                 //操作相关属性，id自增、表明数据库中相应属性名称
     private Integer id;
     private String username;
     @JsonIgnore              //忽略password不展示
     private String password;
     private String nickname;
     private String email;
     private String phone;
     private String address;
}
