package com.example.Spring.LMS.DTO;

import java.time.LocalDateTime;

public record ErrorMessages(
        String message,
        String detailedMessage,
        LocalDateTime date
) {
}
