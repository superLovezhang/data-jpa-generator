package com.superflower.generator.util;

import com.superflower.generator.entity.Table;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    public static void execute() throws Exception {
        // 获取生成代码的列表
        List<Table> list = DataBaseUtils.obtainTableProperties();
        String rootUrl = SqlParams.rootUrl;
        String projectUrl = SqlParams.projectUrl;
        Configuration configuration = new Configuration();
        configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\java\\ihrm_parent\\code_generator\\src\\main\\java\\com\\superflower\\generator\\templates")));

        Template entityTemplate = configuration.getTemplate("entity.ftl");
        Template daoTemplate = configuration.getTemplate("dao.ftl");
        Template serviceTemplate = configuration.getTemplate("service.ftl");
        Template serviceImplTemplate = configuration.getTemplate("serviceImpl.ftl");
        Template controllerTemplate = configuration.getTemplate("controller.ftl");
        HashMap<String, Template> templates = new HashMap<>();
        templates.put("entityTemplate", entityTemplate);
        templates.put("daoTemplate", daoTemplate);
        templates.put("serviceTemplate", serviceTemplate);
        templates.put("serviceImplTemplate", serviceImplTemplate);
        templates.put("controllerTemplate", controllerTemplate);

        String preUrl = SqlParams.rootUrl + "/" + SqlParams.projectUrl.replaceAll("\\.", "/");
        FileUtils.mkdirs(null);
        for (Table table1 : list) {
            HashMap<String, Object> table = new HashMap<>();
            table.put("tableName", table1.getTableName());
            table.put("javaTableName", table1.getJavaTableName());
            table.put("primaryKey", table1.getPrimaryKey());
            table.put("columnList", table1.getColumnList());
            table.put("projectUrl", projectUrl);

            generatorTemplate(table, table1, preUrl, templates);
        }
    }

    public static void generatorTemplate(Map table, Table table1, String preUrl, HashMap<String, Template> templates) throws Exception {
        // 生成实体类
        FileUtils.mkdirs("/pojo");
        templates.get("entityTemplate").process(table, new FileWriter(new File(filePreUrl(preUrl, "/pojo", StringUtils.firstCharUpper(table1.getJavaTableName()) + ".java"))));
        // 生成Dao
        FileUtils.mkdirs("/dao");
        templates.get("daoTemplate").process(table, new FileWriter(new File(filePreUrl(preUrl, "/dao", StringUtils.firstCharUpper(table1.getJavaTableName()) + "Dao.java"))));
        // 生成Service impl
        FileUtils.mkdirs("/service");
        FileUtils.mkdirs("/service/impl");
        templates.get("serviceTemplate").process(table, new FileWriter(new File(filePreUrl(preUrl, "/service", StringUtils.firstCharUpper(table1.getJavaTableName()) + "Service.java"))));
        templates.get("serviceImplTemplate").process(table, new FileWriter(new File(filePreUrl(preUrl, "/service/impl", StringUtils.firstCharUpper(table1.getJavaTableName()) + "ServiceImpl.java"))));
        // 生成Controller
        FileUtils.mkdirs("/controller");
        templates.get("controllerTemplate").process(table, new FileWriter(new File(filePreUrl(preUrl, "/controller", StringUtils.firstCharUpper(table1.getJavaTableName()) + "Controller.java"))));
    }

    public static String filePreUrl(String preUrl, String ceng, String className) {
        return preUrl + ceng + "/" + className;
    }

}
