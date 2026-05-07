package com.deepstudy.quiz.dto;

import com.deepstudy.quiz.domain.QuizQuestion;

public record QuizQuestionResponseDto(
        Long id,
        Long sessionId,
        String question,
        String explanation
) {
    public static QuizQuestionResponseDto from(QuizQuestion quiz) {
        return new QuizQuestionResponseDto(
                quiz.getId(),
                quiz.getStudySession().getId(),
                quiz.getQuestion(),
                quiz.getExplanation()
        );
    }
}
