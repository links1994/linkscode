package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

import java.util.List;

public interface UserSkillMapper {
    // 插入用户并获取自增长的ID(方式一)
    int insertUserGetID1(User user);
    // 插入用户并获取自增长的ID(方式二)
    int insertUserGetID2(User user);
    // 传递表名
    Integer getCount(String tableName);
    // 批量删除
    Integer deleteMore(String ids);
    // 批量删除
    Integer deleteMore2(@Param("ids") List<Integer> ids);
}


