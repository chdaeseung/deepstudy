package com.deepstudy.understanding.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.understanding.dto.ConceptUnderstandingResponseDto;
import com.deepstudy.understanding.service.ConceptUnderstandingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me/understandings")
public class ConceptUnderstandingController {

    private final ConceptUnderstandingService conceptUnderstandingService;

    @GetMapping
    public ApiResponse<List<ConceptUnderstandingResponseDto>> getMyUnderstandings() {
        return ApiResponse.success("이해도 목록 조회 성공", conceptUnderstandingService.getMyUnderstandings());
    }

    @GetMapping("/{topicId}")
    public ApiResponse<ConceptUnderstandingResponseDto> getMyUnderstanding(@PathVariable Long topicId) {
        return ApiResponse.success("이해도 조회 성공", conceptUnderstandingService.getMyUnderStanding(topicId));
    }
}
