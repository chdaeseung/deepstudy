package com.deepstudy.studysession.dto;

import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.domain.StudySessionStatus;

import java.time.LocalDateTime;

public record StudySessionResponseDto(
        Long id,
        Long userId,
        Long topicId,
        StudySessionStatus status,
        Integer currentStep,
        String summary,
        LocalDateTime startedAt,
        LocalDateTime lastActivityAt
) {
    public static StudySessionResponseDto from(StudySession session) {
        return new StudySessionResponseDto(
                session.getId(),
                session.getUserId(),
                session.getTopic().getId(),
                session.getStatus(),
                session.getCurrentStep(),
                session.getSummary(),
                session.getStartedAt(),
                session.getLastActivityAt()
        );
    }
}
