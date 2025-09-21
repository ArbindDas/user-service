package com.PayMate.user_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant createdAt

) {
}
