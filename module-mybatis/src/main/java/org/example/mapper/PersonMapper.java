package org.example.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Person;

public interface PersonMapper {
    // 依据id查询公民方式1
    Person findPersonById1(Integer id);
    // 依据id查询公民方式2
    Person findPersonById2(Integer id);
    // 依据id查询公民方式2
    Person findPersonById3(Integer id);


    @Select("select * from person where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "mark",property = "card",
                    one = @One(select = "com.cn.mapper.CardMapper.findCardByIdOnAnnotation"))
    })
    Person findPersonByIdOnAnnotation(Integer id);
}
