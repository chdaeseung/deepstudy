package com.deepstudy.ai.controller;

import com.deepstudy.ai.service.TeacherLessonService;
import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-sessions")
public class TeacherLessonController {

    private final TeacherLessonService teacherLessonService;

    @PostMapping("/{sessionId}/teacher/messages")
    public ApiResponse<LessonMessageResponseDto> generateTeacherMessage(@PathVariable Long sessionId) {
        return ApiResponse.success("선생 AI 응답 생성 성공", teacherLessonService.generateTeacherMessage(sessionId));
    }
}
