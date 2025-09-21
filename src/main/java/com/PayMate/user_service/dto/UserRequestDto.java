package com.PayMate.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank @Size(min = 3, max = 100) String name,
        @NotBlank
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum|co|io|ai|[a-z]{2})$",
                message = "Invalid email format"
        )
        String email,
        @NotBlank @Size(min = 8, max = 100) String password
) {}
