package com.teamflow.backend.mapper;

import com.teamflow.backend.dto.UserDto;
import com.teamflow.backend.model.User;

public class UserMapper {
    public static UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
