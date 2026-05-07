package com.deepstudy.quiz.dto;

public record QuizParseResult(
        String question,
        String answerCriteria,
        String explanation
) {
}
