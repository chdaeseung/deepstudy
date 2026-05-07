package com.deepstudy.ai.controller;

import com.deepstudy.ai.service.TeacherAiService;
import com.deepstudy.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiTestController {

    private final TeacherAiService teacherAiService;

    @GetMapping("/api/ai/test")
    public ApiResponse<String> test() {
        String response = teacherAiService.askTeacher(
                "JWT",
                "JWT는 토큰 기반 인증 방식이다."
        );

        return ApiResponse.success(response);
    }
}
