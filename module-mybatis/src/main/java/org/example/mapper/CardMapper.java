package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.Card;

public interface CardMapper {
    // 依据id查询身份证
    Card findCardById(Integer id);

    @Select("select * from card where id = #{id}")
    Card findCardByIdOnAnnotation(Integer id);
}
