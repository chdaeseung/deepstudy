package com.deepstudy.quiz.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.quiz.dto.QuizQuestionResponseDto;
import com.deepstudy.quiz.service.QuizQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-sessions")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @PostMapping("/{sessionId}/quiz/questions")
    public ApiResponse<QuizQuestionResponseDto> generateQuiz(@PathVariable Long sessionId) {
        return ApiResponse.success("퀴즈 생성 성공", quizQuestionService.generateQuiz(sessionId));
    }
}
