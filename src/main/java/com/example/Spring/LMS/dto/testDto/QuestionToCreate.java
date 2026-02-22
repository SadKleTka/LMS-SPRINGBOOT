package com.example.Spring.LMS.dto.testDto;

import jakarta.validation.constraints.NotNull;

public record QuestionToCreate(
        @NotNull
        String name
) {
}
