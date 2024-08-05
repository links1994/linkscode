package org.example.mapper;

import org.example.pojo.User;

public interface UserMapper {

    User selectUserById(int id);
}
