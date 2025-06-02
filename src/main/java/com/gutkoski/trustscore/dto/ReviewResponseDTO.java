package com.gutkoski.trustscore.dto;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
    Long id,
    Integer rating,
    String comment,
    LocalDateTime date,
    ProductResponseDTO productResponseDTO,
    UserResponseDTO userResponseDTO) {}
