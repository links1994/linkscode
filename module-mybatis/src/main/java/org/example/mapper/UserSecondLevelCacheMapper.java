package org.example.mapper;

import org.example.pojo.User;

public interface UserSecondLevelCacheMapper {
    // 依据id查询用户
    User queryUserById(int id);
    // 更新用户
    int updateUser(User user);
}
