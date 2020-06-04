package ${projectUrl}.dao;


import ${projectUrl}.pojo.${javaTableName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ${javaTableName}Dao extends JpaRepository<${javaTableName}, String>, JpaSpecificationExecutor<${javaTableName}> {

}
