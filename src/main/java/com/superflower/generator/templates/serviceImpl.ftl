package ${projectUrl}.service.impl;
<#assign lowerJavaName = javaTableName?uncap_first>
import ${projectUrl}.dao.${javaTableName}Dao;
import ${projectUrl}.service.${javaTableName}Service;
import ${projectUrl}.pojo.${javaTableName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ${javaTableName}ServiceImpl implements ${javaTableName}Service {

    @Autowired
    private ${javaTableName}Dao ${lowerJavaName}Dao;

    @Autowired
    private IdWorker idWorker;

    public void add(${javaTableName} ${lowerJavaName}) {
        String id = idWorker.nextId() + "";
        ${lowerJavaName}.setId(id);
        ${lowerJavaName}Dao.save(${lowerJavaName});
    }

    public void update(${javaTableName} ${lowerJavaName}) {
        ${lowerJavaName}Dao.save(${lowerJavaName});
    }

    <#if primaryKey != "">
    public void delete(String id) {
        ${lowerJavaName}Dao.deleteById(id);
    }

    public ${javaTableName} findById(String id) {
        return ${lowerJavaName}Dao.findById(id).get();
    }
    </#if>

    public List<${javaTableName}> findList() {
        return ${lowerJavaName}Dao.findAll();
    }

}