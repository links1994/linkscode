package org.example.mapper;

import org.example.pojo.User;

public interface UserEhCacheMapper {
    // 依据id查询用户
    User queryUserById(int id);
}
