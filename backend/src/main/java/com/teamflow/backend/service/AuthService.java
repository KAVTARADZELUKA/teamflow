package com.teamflow.backend.service;

import com.teamflow.backend.dto.auth.AuthRequest;
import com.teamflow.backend.dto.auth.AuthResponse;
import com.teamflow.backend.dto.user.CreateUserRequest;

public interface AuthService {
    AuthResponse register(CreateUserRequest request);

    AuthResponse login(AuthRequest request);
}
