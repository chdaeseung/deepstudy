package com.deepstudy.understanding.dto;

import com.deepstudy.understanding.domain.ConceptUnderstanding;

import java.time.LocalDateTime;

public record ConceptUnderstandingResponseDto(
        Long id,
        Long userId,
        Long topicId,
        String topicName,
        Integer understandingLevel,
        String summary,
        String weakPoints,
        String improvedPoints,
        Integer lastQuizScore,
        LocalDateTime lastStudiedAt
) {
    public static ConceptUnderstandingResponseDto from(ConceptUnderstanding understanding) {
        return new ConceptUnderstandingResponseDto(
                understanding.getId(),
                understanding.getUserId(),
                understanding.getTopic().getId(),
                understanding.getTopic().getName(),
                understanding.getUnderstandingLevel(),
                understanding.getSummary(),
                understanding.getWeakPoints(),
                understanding.getImprovedPoints(),
                understanding.getLastQuizScore(),
                understanding.getLastStudiedAt()
        );
    }
}
