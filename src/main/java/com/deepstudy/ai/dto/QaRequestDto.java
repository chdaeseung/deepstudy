package com.deepstudy.ai.dto;

import jakarta.validation.constraints.NotBlank;

public record QaRequestDto(
        @NotBlank(message = "질문사항을 입력해주세요.")
        String question
) {
}
