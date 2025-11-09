package com.teamflow.backend.service;

import com.teamflow.backend.dto.CreateUserRequest;
import com.teamflow.backend.dto.UserDto;
import com.teamflow.backend.mapper.UserMapper;
import com.teamflow.backend.model.Role;
import com.teamflow.backend.model.User;
import com.teamflow.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }
}
