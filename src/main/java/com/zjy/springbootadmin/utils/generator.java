package com.zjy.springbootadmin.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;


/**
 * MP代码生成器
 * by halfling
 * @since 2022-8-12
 */
public class generator {
    public static void main(String[] args) {
        generate();
    }
    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/springbootadmin?serverTimezone=GMT%2b8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("zjy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\projects\\SpringBoot\\springbootAdmin\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zjy.springbootadmin") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "D:\\projects\\SpringBoot\\springbootAdmin\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();     //lombok
                    builder.mapperBuilder().enableMapperAnnotation().build();   //mapper
                    builder.controllerBuilder().enableHyphenStyle()             //驼峰
                            .enableRestStyle();                                 //RestController
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                })
                .execute();
    }
}
