package com.PayMate.user_service.controller;

import com.PayMate.user_service.dto.UserRequestDto;
import com.PayMate.user_service.dto.UserResponseDto;
import com.PayMate.user_service.entity.User;
import com.PayMate.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequest){
      UserResponseDto createdUser = userService.createUser(userRequest);
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(createdUser);
    }
}
