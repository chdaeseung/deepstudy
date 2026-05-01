package com.deepstudy.lessonmessage.dto;

import jakarta.validation.constraints.NotBlank;

public record LessonMessageCreateRequestDto(
        @NotBlank(message = "메세지를 입력해주세요.")
        String content
) {
}
