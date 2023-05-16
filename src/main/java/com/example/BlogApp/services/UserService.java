package com.example.BlogApp.services;

import com.example.BlogApp.entities.User;
import com.example.BlogApp.payloads.UserDto;

import java.util.List;

public interface UserService {

        UserDto createUser(UserDto user);
        UserDto updateUser(UserDto user, Integer userId);
        UserDto getUserById(Integer userId);
        List<UserDto> getAllUsers();
        void deleteUser(Integer userId);

        UserDto usertoDto(User savedUser);
}
