package com.deepstudy.quiz.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.quiz.dto.QuizAnswerCreateRequestDto;
import com.deepstudy.quiz.dto.QuizAnswerResponseDto;
import com.deepstudy.quiz.service.QuizAnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz-questions")
public class QuizAnswerController {

    private final QuizAnswerService quizAnswerService;

    @PostMapping("/{questionId}/answers")
    public ApiResponse<QuizAnswerResponseDto> submitAnswer(@PathVariable Long questionId, @Valid @RequestBody QuizAnswerCreateRequestDto requestDto) {
        return ApiResponse.success("답변 제출 및 채점 완료", quizAnswerService.submitAnswer(questionId, requestDto));
    }
}
