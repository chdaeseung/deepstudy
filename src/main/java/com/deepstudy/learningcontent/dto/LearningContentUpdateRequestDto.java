package com.deepstudy.learningcontent.dto;

import com.deepstudy.learningcontent.domain.Difficulty;
import com.deepstudy.learningcontent.domain.LearningContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LearningContentUpdateRequestDto(
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @NotNull(message = "난이도를 입력해주세요.")
        Difficulty difficulty,

        @NotNull(message = "학습 순서를 입력해주세요.")
        Integer orderIndex,

        @NotNull(message = "상태를 입력해주세요.")
        LearningContentStatus status,

        String sourceUrl
) {
}
