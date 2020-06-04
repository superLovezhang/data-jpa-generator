package ${projectUrl}.controller;
<#assign lowerJavaName = javaTableName?uncap_first>
import ${projectUrl}.service.${javaTableName}Service;

import ${projectUrl}.pojo.${javaTableName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${lowerJavaName}")
@CrossOrigin
public class ${javaTableName}Controller {

    @Autowired
    private ${javaTableName}Service ${lowerJavaName}Service;

    @PostMapping
    public Result save(@RequestBody ${javaTableName} ${lowerJavaName}) {
        ${lowerJavaName}Service.add(${lowerJavaName});
        return Result.SUCCESS();
    }

    @PutMapping
    public Result update(@RequestBody ${javaTableName} ${lowerJavaName}) {
        ${lowerJavaName}Service.update(${lowerJavaName});
        return Result.SUCCESS();
    }

    <#if primaryKey != "">
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        ${lowerJavaName}Service.delete(id);
        return Result.SUCCESS();
    }

    @GetMapping(value = "/{id}")
    public Result find(@PathVariable String id) {
        ${javaTableName} ${lowerJavaName} = ${lowerJavaName}Service.findById(id);
        return new Result(ResultCode.SUCCESS, ${lowerJavaName});
    }
    </#if>

    @GetMapping
    public Result findAll() {
    List<${javaTableName}> list = ${lowerJavaName}Service.findList();
        return new Result(ResultCode.SUCCESS, list);
    }

}
