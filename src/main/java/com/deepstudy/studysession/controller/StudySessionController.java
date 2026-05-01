package com.deepstudy.studysession.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.studysession.dto.StudySessionResponseDto;
import com.deepstudy.studysession.service.StudySessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudySessionController {

    private final StudySessionService studySessionService;

    @PostMapping("/topics/{topicId}/study-sessions")
    public ApiResponse<StudySessionResponseDto> createSession(@PathVariable Long topicId) {
        return ApiResponse.success("세션 생성 성공", studySessionService.createSession(topicId));
    }

    @GetMapping("/study-sessions/{sessionId}")
    public ApiResponse<StudySessionResponseDto> getSession(@PathVariable Long sessionId) {
        return ApiResponse.success("세션 조회 성공", studySessionService.getSession(sessionId));
    }

    @GetMapping("/study-sessions")
    public ApiResponse<List<StudySessionResponseDto>> getMySessions() {
        return ApiResponse.success("세션 목록 조회 성공", studySessionService.getMySessions());
    }

    @PatchMapping("/study-sessions/{sessionId}/complete")
    public ApiResponse<StudySessionResponseDto> completeSession(@PathVariable Long sessionId) {
        return ApiResponse.success("학습 세션 완료", studySessionService.completeSession(sessionId));
    }

}
