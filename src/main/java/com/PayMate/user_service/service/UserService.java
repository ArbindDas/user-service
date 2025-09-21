package com.PayMate.user_service.service;

import com.PayMate.user_service.dto.UserRequestDto;
import com.PayMate.user_service.dto.UserResponseDto;
import com.PayMate.user_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto userRequest);
    void deleteUserById(Long id);
    List<UserResponseDto>getAllUsers();
    UserResponseDto getUserByEmail(String email);


}
