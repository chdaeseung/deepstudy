package com.deepstudy.learningcontent.dto;

import com.deepstudy.learningcontent.domain.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LearningContentCreateRequestDto(
        @NotBlank
        String title,

        @NotBlank
        String content,

        @NotNull
        Difficulty difficulty,

        @NotNull
        Integer orderIndex,

        String sourceUrl
) {
}
