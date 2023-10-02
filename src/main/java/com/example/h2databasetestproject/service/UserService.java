package com.example.h2databasetestproject.service;

import com.example.h2databasetestproject.dto.UserDto;
import com.example.h2databasetestproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
