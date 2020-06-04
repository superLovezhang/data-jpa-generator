### 这是什么？(what is this)

+ 这是一个基于JPA的java的代码生成工具，能够帮助你一键生成一切通用的controller,service,pojo,dao层代码。

### 怎么使用？(how to use)

+ 在项目的com.superflower.generator目录下，有一个GeneratorMain文件，简单配置一下，点击运行即可。

  ```java
  	    // 初始化参数
          // 设置文件生成项目的src/main/java的目录地址
          SqlParams.setRootUrl("D:\\java\\ihrm_parent\\code_generator\\src\\main\\java");
          // 设置文件生成的项目坐标 com.xxx.xxx
          SqlParams.setProjectUrl("com.a.b");
          // 设置数据库账户密码 默认root 123456
          //SqlParams.setAccount("username", "password");
          // 设置要生成代码的表 传一个字符串数组 不设置默认为连接数据库的全表
          //SqlParams.setDatabase(new String[] {"proc_user_group"});
          // 数据库链接地址
          SqlParams.setSqlUrl("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8");
          CodeGenerator.execute();
  ```

  