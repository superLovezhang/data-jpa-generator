package ${projectUrl}.service;
<#assign lowerJavaName = javaTableName?uncap_first>
import ${projectUrl}.pojo.${javaTableName};

import java.util.List;

public interface ${javaTableName}Service {

    public void add(${javaTableName} ${lowerJavaName});

    public void update(${javaTableName} ${lowerJavaName});

    <#if primaryKey != "">
    public void delete(String id);

    public ${javaTableName} findById(String id);
    </#if>

    public List<${javaTableName}> findList();

}
