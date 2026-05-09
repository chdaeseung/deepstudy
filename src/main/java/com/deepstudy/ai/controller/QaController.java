package com.deepstudy.ai.controller;

import com.deepstudy.ai.dto.QaRequestDto;
import com.deepstudy.ai.service.QaLessonService;
import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-sessions")
public class QaController {

    private final QaLessonService qaLessonService;

    @PostMapping("/{sessionId}/questions")
    public ApiResponse<LessonMessageResponseDto> ask(@PathVariable Long sessionId, @Valid @RequestBody QaRequestDto requestDto) {
        return ApiResponse.success("질문 답변 생성 성공", qaLessonService.ask(sessionId, requestDto));
    }
}
