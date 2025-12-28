package com.teamflow.backend.service;

import com.teamflow.backend.dto.AuthRequest;
import com.teamflow.backend.dto.AuthResponse;
import com.teamflow.backend.dto.CreateUserRequest;

public interface AuthService {
    AuthResponse register(CreateUserRequest request);

    AuthResponse login(AuthRequest request);
}
