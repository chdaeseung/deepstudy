package com.deepstudy.lessonmessage.dto;

import com.deepstudy.lessonmessage.domain.LessonMessage;
import com.deepstudy.lessonmessage.domain.MessageRole;

import java.time.LocalDateTime;

public record LessonMessageResponseDto(
        Long id,
        Long sessionId,
        MessageRole role,
        String content,
        LocalDateTime createdAt
) {
    public static LessonMessageResponseDto from(LessonMessage message) {
        return new LessonMessageResponseDto(
                message.getId(),
                message.getStudySession().getId(),
                message.getRole(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}
