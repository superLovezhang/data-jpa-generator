package com.superflower.generator;

import com.superflower.generator.entity.Table;
import com.superflower.generator.util.CodeGenerator;
import com.superflower.generator.util.DataBaseUtils;
import com.superflower.generator.util.FileUtils;
import com.superflower.generator.util.SqlParams;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class GeneratorMain {

    public static void main(String[] args) throws Exception {
        // 初始化参数
        // 设置文件生成项目的src/main/java的目录地址
        SqlParams.setRootUrl("D:\\java\\ihrm_parent\\code_generator\\src\\main\\java");
        // 设置文件生成的项目坐标 com.xxx.xxx
        SqlParams.setProjectUrl("com.a.b");
        // 设置数据库账户密码 默认root 123456
        //SqlParams.setAccount("username", "password");
        // 设置要生成代码的表 传一个字符串数组 不设置默认为连接数据库的全表
//        SqlParams.setDatabase(new String[] {"proc_user_group"});
        // 数据库链接地址
        SqlParams.setSqlUrl("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8");

        CodeGenerator.execute();
    }

}
