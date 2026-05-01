package com.deepstudy.learningcontent.dto;

import com.deepstudy.learningcontent.domain.Difficulty;
import com.deepstudy.learningcontent.domain.LearningContent;
import com.deepstudy.learningcontent.domain.LearningContentStatus;

import java.time.LocalDateTime;

public record LearningContentResponseDto(
        Long id,
        Long topicId,
        String title,
        String content,
        Difficulty difficulty,
        Integer orderIndex,
        LearningContentStatus status,
        Integer version,
        String sourceUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static LearningContentResponseDto from(LearningContent lc) {
        return new LearningContentResponseDto(
                lc.getId(),
                lc.getTopic().getId(),
                lc.getTitle(),
                lc.getContent(),
                lc.getDifficulty(),
                lc.getOrderIndex(),
                lc.getStatus(),
                lc.getVersion(),
                lc.getSourceUrl(),
                lc.getCreatedAt(),
                lc.getUpdatedAt()
        );
    }
}
