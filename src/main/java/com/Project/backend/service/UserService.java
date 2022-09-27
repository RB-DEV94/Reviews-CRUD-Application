package com.Project.backend.service;

import com.Project.backend.Dto.UserDto;
import com.Project.backend.Model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void saveAdmin(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}