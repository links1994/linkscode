package org.example.mapper;

import org.example.pojo.Employee;

public interface EmployeeMapper {
    Employee findEmployeeById0(Integer id);
    // 依据id查询员工方式1
    Employee findEmployeeById1(Integer id);
    // 依据id查询员工方式2
    Employee findEmployeeById2(Integer id);
}
