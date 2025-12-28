package com.teamflow.backend.service.impl;

import com.teamflow.backend.dto.UserDto;
import com.teamflow.backend.mapper.UserMapper;
import com.teamflow.backend.repository.UserRepository;
import com.teamflow.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", id);
                    return new IllegalArgumentException("User not found");
                });
    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}