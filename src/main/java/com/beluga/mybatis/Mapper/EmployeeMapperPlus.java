package com.beluga.mybatis.Mapper;

import com.beluga.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

    Employee getEmpById(int i);

    Employee getEmpAndDept(Integer id);
}
