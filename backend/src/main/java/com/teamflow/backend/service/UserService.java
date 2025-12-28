package com.teamflow.backend.service;

import com.teamflow.backend.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    void deleteUser(Long id);
}
