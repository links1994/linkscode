package org.example.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    // 依据id查询老师方式1
    Teacher getTeacherByID1(Integer id);
    // 依据id查询老师方式2
    Teacher getTeacherByID2(Integer id);
    // 依据学生id查询老师
    List<Teacher> findTeachers(Integer sid);


    // 依据id查询老师
    @Select("select * from teacher where t_id=#{id}")
    @Results({
            @Result(id = true,column = "t_id",property = "tId"),
            @Result(column = "t_name",property = "tName"),
            @Result(column = "t_id",property = "studentList",
                    many = @Many(select = "com.cn.mapper.StudentMapper.findStudentsOnAnnotation")),
    })
    Teacher getTeacherByIDOnAnnotation(Integer id);

    // 依据学生id查询与之对应的多个老师
    @Select("select * from teacher where t_id in " +
            "(select t_id from student_teacher_relation where s_id = #{id})")
    List<Teacher> findTeachersOnAnnotation(Integer sid);
}
