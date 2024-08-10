package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

import java.util.List;

public interface UserLocalCacheMapper {
    // 依据id查询用户
    User queryUserById(int id);
    // 依据id查询用户
    User queryUserByName(@Param("username") String name);
    // 更新用户
    int updateUser(User user);
}
