package com.superflower.generator.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static Map<String, String> typeConvert;

    static {
        typeConvert = new HashMap<>();
        typeConvert.put("VARCHAR", "String");
        typeConvert.put("BIGINT", "Long");
        typeConvert.put("INT", "Integer");
        typeConvert.put("DATE", "java.util.Date");
        typeConvert.put("DATETIME", "java.util.Date");
        typeConvert.put("TIME", "java.util.Date");
        typeConvert.put("DOUBLE", "Double");
        typeConvert.put("TEXT", "String");
        typeConvert.put("VARCHAR2", "String");
        typeConvert.put("NVARCHAR2", "String");
        typeConvert.put("NUMBER", "Long");
        typeConvert.put("CHAR", "String");
        typeConvert.put("MEDIUMTEXT", "String");
        typeConvert.put("TIMESTAMP", "String");
        typeConvert.put("DECIMAL", "String");
        typeConvert.put("TINYINT", "Integer");
        typeConvert.put("BIT", "Integer");
        typeConvert.put("LONGTEXT", "String");
    }

    public static String toJavaType(String sqlType) {
        return typeConvert.get(sqlType);
    }

    public static String toJavaClassName(String tableName) {
        // co_company -> Company
        String className = null;
        String[] s = tableName.split("_");
        className = s.length == 0 ? firstCharUpper(tableName) : convertCamelCase(s);
        return className;
    }

    public static String toJavaPropertiesName(String properties) {
        String[] s = properties.split("_");
        String javaProperties = "";
        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                javaProperties += s[i];
                continue;
            }
            javaProperties += firstCharUpper(s[i]);
        }
        return javaProperties;
    }

    private static String convertCamelCase(String[] s) {
        String cameCaseName = "";
        for (int i = 0; i < s.length; i++) {
            if (i != 0) {
                cameCaseName += firstCharUpper(s[i]);
            }
        }
        return cameCaseName;
    }

    public static String firstCharUpper(String str) {
        String firstChar = str.substring(0, 1).toUpperCase();
        return firstChar + str.substring(1);
    }

}
