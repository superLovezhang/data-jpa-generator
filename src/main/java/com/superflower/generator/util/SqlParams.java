package com.superflower.generator.util;

public class SqlParams {

    public static String url;

    public static String[] database;

    public static String username = "root";

    public static String password = "123456";

    public static String rootUrl;

    public static String projectUrl;

    public static void setSqlUrl(String url) {
        if (url == null || "".equals(url)) {
            throw new RuntimeException("请填写数据库地址");
        }
        SqlParams.url = url;
    }

    public static void setDatabase(String[] database) {
        if (database == null || database.length == 0) {
            throw new RuntimeException("请填写要生成的表名");
        }
        SqlParams.database = database;
    }

    public static void setAccount(String username, String password) {
        if ((username == null || "".equals(username)) && (password == null || "".equals(password))) {
            throw new RuntimeException("请填写账号密码");
        }
        SqlParams.username = username;
        SqlParams.password = password;
    }

    public static void setRootUrl(String rootUrl) {
        if (rootUrl == null || "".equals(rootUrl)) {
            throw new RuntimeException("请填写生成文件的根路径");
        }
        SqlParams.rootUrl = rootUrl;
    }

    public static void setProjectUrl(String projectUrl) {
        if (projectUrl == null || "".equals(projectUrl)) {
            throw new RuntimeException("请填写项目路径");
        }
        SqlParams.projectUrl = projectUrl;
    }

}
