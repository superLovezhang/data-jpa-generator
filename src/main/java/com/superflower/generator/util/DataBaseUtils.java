package com.superflower.generator.util;


import com.superflower.generator.entity.Column;
import com.superflower.generator.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (SqlParams.url == null || "".equals(SqlParams.url)) {
            throw new RuntimeException("请填写数据库地址");
        }
        return DriverManager.getConnection(SqlParams.url, SqlParams.username, SqlParams.password);

    }

    public static List<Table> obtainTableProperties() {
        ArrayList<Table> list = new ArrayList<>();
        try {
            Connection connection = getConnection();
            // 获取该连接的元数据
            DatabaseMetaData metaData = connection.getMetaData();
            // 如果表名列表被赋值了且为空字符串 报错
            if (SqlParams.database != null && SqlParams.database.length == 0) {
                throw new RuntimeException("请填写要生成的表名");
            }

            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            ArrayList<String> tableList = new ArrayList<>();
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                // 如果表名列表为null 默认为该数据库所有表
                if (SqlParams.database != null) {
                    if (!contains(SqlParams.database, tableName)) {
                        continue;
                    }
                }
                String primary = "";
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, "co_company");
                while (primaryKeys.next()) {
                    String name = primaryKeys.getString("COLUMN_NAME");
                    primary += name + ",";
                }
                Table table = new Table();
                table.setTableName(tableName);
                table.setJavaTableName(StringUtils.toJavaClassName(tableName));
                table.setPrimaryKey(primary);
                list.add(table);
                tableList.add(tableName);
            }
            String[] array = tableList.toArray(new String[tableList.size()]);
            SqlParams.database = array;

            // 获取所有要生成代码的表名列表
            for (Table table : list) {
                ArrayList<Column> columnList = new ArrayList<>();
                String tableName = table.getTableName();
                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    Column column = new Column();
                    // 列名
                    String columnsName = columns.getString("COLUMN_NAME");
                    column.setColumnName(StringUtils.toJavaPropertiesName(columnsName));
                    // 类型
                    String typeName = columns.getString("TYPE_NAME");
                    column.setJavaTypeName(StringUtils.toJavaType(typeName));
                    // 备注
                    String remarks = columns.getString("REMARKS");
                    column.setComment(remarks);
                    // 是否主键
                    column.setPrimary(table.getPrimaryKey().contains(columnsName));
                    columnList.add(column);
                }
                table.setColumnList(columnList);
            }
            connection.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("获取表结构失败");
        }
    }

    public static boolean contains(String[] arr, String item) {
        for (String s : arr) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

}
