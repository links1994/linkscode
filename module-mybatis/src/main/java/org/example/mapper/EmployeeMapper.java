package org.example.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Employee;

public interface EmployeeMapper {
    Employee findEmployeeById0(Integer id);
    // 依据id查询员工方式1
    Employee findEmployeeById1(Integer id);
    // 依据id查询员工方式2
    Employee findEmployeeById2(Integer id);


    // 依据id查询员工
    @Select("select * from employee where e_id = #{id}")
    @Results({
            @Result(id = true,column = "e_id",property = "eId"),
            @Result(column = "e_name",property = "eName"),
            @Result(column = "d_id",property = "department",
                    one = @One(select = "com.cn.mapper.DepartmentMapper.findDepartmentByIdOnAnnotation"))
    })
    Employee findEmployeeByIdOnAnnotation(Integer id);
}
