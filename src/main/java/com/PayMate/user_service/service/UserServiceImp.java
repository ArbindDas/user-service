package com.PayMate.user_service.service;

import com.PayMate.user_service.dto.UserRequestDto;
import com.PayMate.user_service.dto.UserResponseDto;
import com.PayMate.user_service.entity.User;
import com.PayMate.user_service.exception.UserNotFoundException;
import com.PayMate.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

   @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        logger.info("Attempting to create user with email: {}", userRequest.email());

        if (userRepository.existsByEmail(userRequest.email())) {
            logger.warn("Email already exists: {}", userRequest.email());
            throw new UserNotFoundException("Email already exists: " + userRequest.email());
        }

        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());

        User saved = userRepository.save(user);
        logger.info("User created successfully with ID: {}", saved.getId());

        return toResponseDto(saved);
    }

    private UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail() ,user.getCreatedAt());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        logger.info("Fetching user by ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User Not Found With id: " + id);
                });

        logger.info("User found: {}", user.getEmail());
        return toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequest) {
        logger.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });

        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());

        User updated = userRepository.save(user);
        logger.info("User updated successfully with ID: {}", updated.getId());

        return toResponseDto(updated);
    }

    @Override
    public void deleteUserById(Long id) {
        logger.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.error("User not found for deletion with ID: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        logger.info("User deleted successfully with ID: {}", id);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        logger.info("Fetching all users");

        List<UserResponseDto> users = userRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();

        logger.info("Total users fetched: {}", users.size());
        return users;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        logger.info("Fetching user by email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new UserNotFoundException("User not found with email: " + email);
                });

        logger.info("User found: {}", user.getId());
        return toResponseDto(user);
    }
}
