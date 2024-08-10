package org.example.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.example.pojo.Teacher;

import java.util.List;

public interface StudentMapper {
    // 依据id查询学生方式1
    Student getStudentByID1(Integer id);
    // 依据id查询学生方式2
    Student getStudentByID2(Integer id);
    // 依据老师id查询学生
    List<Student> findStudents(Integer tid);

    // 依据id查询学生
    @Select("select * from student where s_id=#{id}")
    @Results({
            @Result(id = true,column = "s_id",property = "sId"),
            @Result(column = "s_name",property = "sName"),
            @Result(column = "s_id",property = "teacherList",
                    many = @Many(select = "com.cn.mapper.TeacherMapper.findTeachersOnAnnotation")),
    })
    Student getStudentByIDOnAnnotation(Integer id);

    // 依据老师id查询与之对应的多个学生
    @Select("select * from student where s_id in " +
            "(select s_id from student_teacher_relation where t_id = #{id})")
    List<Student> findStudentsOnAnnotation(Integer tid);
}
