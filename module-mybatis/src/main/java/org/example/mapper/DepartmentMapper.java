package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.Department;

public interface DepartmentMapper {
    // 依据id查询部门
    Department findDepartmentById(Integer id);

    @Select("select * from department where d_id = #{id}")
    Department findDepartmentByIdOnAnnotation(Integer id);
}
