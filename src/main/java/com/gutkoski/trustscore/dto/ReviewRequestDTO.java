package com.gutkoski.trustscore.dto;

import java.time.LocalDateTime;

public record ReviewRequestDTO(
    Integer rating, String comment, LocalDateTime createdAt, Long productId, Long userId) {}
