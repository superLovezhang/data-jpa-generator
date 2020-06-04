package ${projectUrl}.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "${tableName}")
public class ${javaTableName} implements Serializable {
    <#list columnList as column>

    /**
    * ${column.comment}
    */
    <#if column.primary == true>
    @Id
    </#if>
    private ${column.javaTypeName} ${column.columnName};
    </#list>

    <#list columnList as column>
    public ${column.javaTypeName} get${column.columnName?cap_first}() {
        return ${column.columnName};
    }

    public void set${column.columnName?cap_first}(${column.javaTypeName} ${column.columnName}) {
        this.${column.columnName} = ${column.columnName};
    }
    </#list>

}