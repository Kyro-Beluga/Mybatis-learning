package com.beluga.mybatis.Mapper;

import com.beluga.mybatis.bean.Employee;

public interface EmployeeMapper {

    Employee selectEmployee(Integer id);

    void addEmp(Employee employee);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer id);
}
