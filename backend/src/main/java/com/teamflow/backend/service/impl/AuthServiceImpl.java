package com.teamflow.backend.service.impl;

import com.teamflow.backend.dto.AuthRequest;
import com.teamflow.backend.dto.AuthResponse;
import com.teamflow.backend.dto.CreateUserRequest;
import com.teamflow.backend.exception.BadRequestException;
import com.teamflow.backend.model.enums.Role;
import com.teamflow.backend.model.User;
import com.teamflow.backend.repository.UserRepository;
import com.teamflow.backend.security.JwtTokenProvider;
import com.teamflow.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(CreateUserRequest request) {
        log.info("Attempting to register user with email: {}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            log.warn("Registration failed: email {} already in use", request.email());
            throw new BadRequestException("Email already in use");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        log.info("User registered successfully: {}", user.getEmail());

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        log.info("Login attempt for email: {}", request.email());

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        log.debug("Authentication successful, generating token for {}", request.email());
        String token = jwtTokenProvider.generateToken(request.email());
        return new AuthResponse(token);
    }
}
