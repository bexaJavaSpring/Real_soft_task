package com.example.real_soft_task.repository;

import com.example.real_soft_task.dto.UserDto;
import com.example.real_soft_task.model.User;

import java.util.List;

public interface UserRepository {
    int addUser(UserDto user);

    int updateUser(UserDto user, Integer id);

    User findById(Integer id);

    List<User> findAll();

    int deleteUser(Integer id);
}
