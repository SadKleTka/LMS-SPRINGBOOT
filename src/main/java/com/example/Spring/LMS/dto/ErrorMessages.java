package com.example.Spring.LMS.dto;

import java.time.LocalDateTime;

public record ErrorMessages(
        String message,
        String detailedMessage,
        LocalDateTime date
) {
}
