package com.deepstudy.quiz.dto;

import com.deepstudy.quiz.domain.QuizAnswer;

import java.time.LocalDateTime;

public record QuizAnswerResponseDto(
        Long id,
        Long questionId,
        Long userId,
        String userAnswer,
        Integer score,
        String feedback,
        LocalDateTime createdAt
) {
    public static QuizAnswerResponseDto from(QuizAnswer answer) {
        return new QuizAnswerResponseDto(
                answer.getId(),
                answer.getQuizQuestion().getId(),
                answer.getUserId(),
                answer.getUserAnswer(),
                answer.getScore(),
                answer.getFeedback(),
                answer.getCreatedAt()
        );
    }
}
