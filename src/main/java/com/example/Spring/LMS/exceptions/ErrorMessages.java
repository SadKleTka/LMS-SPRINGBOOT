package com.example.Spring.LMS.exceptions;

import java.time.LocalDateTime;

public record ErrorMessages(
        String message,
        String detailedMessage,
        LocalDateTime date
) {
}
