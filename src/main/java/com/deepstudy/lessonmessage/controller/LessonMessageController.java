package com.deepstudy.lessonmessage.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.lessonmessage.domain.MessageRole;
import com.deepstudy.lessonmessage.dto.LessonMessageCreateRequestDto;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.lessonmessage.service.LessonMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-sessions")
public class LessonMessageController {

    private final LessonMessageService lessonMessageService;

    @PostMapping("/{sessionId}/messages")
    public ApiResponse<LessonMessageResponseDto> createMessage(@PathVariable Long sessionId, @Valid @RequestBody LessonMessageCreateRequestDto requestDto) {
        return ApiResponse.success("메세지 저장 성공", lessonMessageService.saveMessage(sessionId, MessageRole.USER, requestDto.content()));
    }

    @GetMapping("/{sessionId}/messages")
    public ApiResponse<List<LessonMessageResponseDto>> getMessages(@PathVariable Long sessionId) {
        return ApiResponse.success(lessonMessageService.getMessages(sessionId));
    }
}
