package org.example.mapper;

import org.example.pojo.Department;

public interface DepartmentMapper {
    // 依据id查询部门
    Department findDepartmentById(Integer id);
}
