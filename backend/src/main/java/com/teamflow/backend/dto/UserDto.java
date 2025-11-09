package com.teamflow.backend.dto;

import com.teamflow.backend.model.Role;

import java.time.Instant;

public record UserDto(
        Long id,
        String username,
        String email,
        Role role,
        Instant createdAt,
        Instant updatedAt
) {
}
