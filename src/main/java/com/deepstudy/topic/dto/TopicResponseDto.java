package com.deepstudy.topic.dto;

import com.deepstudy.topic.domain.Topic;

import java.time.LocalDateTime;

public record TopicResponseDto(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static TopicResponseDto from(Topic topic) {
        return new TopicResponseDto(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }
}
