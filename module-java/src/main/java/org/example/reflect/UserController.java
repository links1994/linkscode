package org.example.reflect;

import org.example.pojo.UserVo;

public class UserController implements IUserController {
    @Override
    public UserVo login(String telephone, String password) {
        System.out.println("login............");
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        System.out.println("register...........");
        return null;
    }
}
