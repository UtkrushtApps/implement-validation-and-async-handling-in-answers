package com.teamboard.user.service;

import com.teamboard.user.dto.RegisterUserRequest;
import com.teamboard.user.dto.UserResponse;
import com.teamboard.user.entity.User;
import com.teamboard.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final WelcomeEmailService welcomeEmailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponse registerUser(RegisterUserRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email is already registered");
                });

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .email(request.getEmail())
                .fullName(StringUtils.capitalize(request.getFullName()))
                .password(hashedPassword)
                .build();
        userRepository.save(user);

        // Async welcome email
        welcomeEmailService.sendWelcomeEmail(user.getEmail(), user.getFullName());

        logger.info("User [{}] registered successfully", user.getEmail());
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}
