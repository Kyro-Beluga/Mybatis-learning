package com.beluga.mybatis.Mapper;

import com.beluga.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {

    @MapKey("id")
    Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);

    Map<String,Object> getEmpByIdReturnMap(Integer id);

    Employee selectEmpByMap(Map<String,Object> map);

    Employee selectEmpByIDAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee selectEmployee(Integer id);

    void addEmp(Employee employee);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer id);
}
