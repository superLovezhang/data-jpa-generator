import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CodeTest {

    @Test
    public void test() throws Exception {
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(new FileTemplateLoader(new File("D:\\java\\ihrm_parent\\code_generator\\src\\main\\java\\com\\superflower\\generator\\templates")));
        Template template = cfg.getTemplate("freemark.ftl");
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", 1);
        template.process(map, new FileWriter(new File("D:\\test.txt")));
    }

    /**
     * 从connection中获取所有数据库名
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8", "root", "123456");
        // 获取数据库元数据
        DatabaseMetaData metaData = connection.getMetaData();
        // 获取所有数据库名
        ResultSet catalogs = metaData.getCatalogs();
        while (catalogs.next()) {
            System.out.println(catalogs.getString(1));
        }
    }

    /**
     * 从connection中获取所有表名
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8", "root", "123456");
        // 获取数据库元数据
        DatabaseMetaData metaData = connection.getMetaData();
        // 获取所有表名
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println(tables.getString("TABLE_NAME"));
        }
        connection.close();
    }

    /**
     * 从connection中获取表中所有字段
     */
    @Test
    public void test4() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8", "root", "123456");
        // 获取数据库元数据
//        DatabaseMetaData metaData = connection.getMetaData();
//        // 获取所有字段
//        ResultSet tables = metaData.getColumns(null, null, "co_company", null);
//        while (tables.next()) {
//            System.out.println(tables.getString("COLUMN_NAME"));
//        }
//        connection.close();
    }

    /**
     * 从ResultSetMetaData中获取表的字段和类型
     */
    @Test
    public void test5() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///ihrm?useUnicode=true&characterEncoding=utf8", "root", "123456");
        PreparedStatement statement = connection.prepareStatement("select * from co_company");
        ResultSet resultSet = statement.executeQuery();
        // 获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 获取字段数
        int count = metaData.getColumnCount();

        for (int i = 1; i <= count; i++) {
            String columnName = metaData.getColumnName(i);
            String typeName = metaData.getColumnTypeName(i);
            String className = metaData.getColumnClassName(i);
            System.out.println(columnName + "--" + typeName + "--" + className);
        }

        resultSet.close();
        connection.close();
    }

}
