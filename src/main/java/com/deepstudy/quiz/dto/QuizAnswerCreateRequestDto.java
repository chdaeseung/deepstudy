package com.deepstudy.quiz.dto;

import jakarta.validation.constraints.NotBlank;

public record QuizAnswerCreateRequestDto(
        @NotBlank(message = "답변을 입력해주세요.")
        String userAnswer
) {
}
