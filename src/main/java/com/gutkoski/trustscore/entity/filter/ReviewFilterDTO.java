package com.gutkoski.trustscore.entity.filter;

import java.time.LocalDate;

public record ReviewFilterDTO(
        String title,
        Integer rating,
        LocalDate createdAfter,
        LocalDate createdBefore
){}