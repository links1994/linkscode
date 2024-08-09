package org.example.reflect;

import org.example.pojo.UserVo;

public interface IUserController {
    UserVo login(String telephone, String password);
    UserVo register(String telephone, String password);
}
