package com.gutkoski.trustscore.dto;

import java.time.LocalDateTime;

public record ReviewRequestDTO(Integer rating,
                               String comment,
                               LocalDateTime date,
                               Long productId,
                               Long userId) {
}
