package com.deepstudy.learningcontent.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.learningcontent.dto.LearningContentCreateRequestDto;
import com.deepstudy.learningcontent.dto.LearningContentResponseDto;
import com.deepstudy.learningcontent.dto.LearningContentUpdateRequestDto;
import com.deepstudy.learningcontent.service.LearningContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LearningContentController {

    private final LearningContentService learningContentService;

    @PostMapping("/api/topics/{topicId}/contents")
    public ApiResponse<LearningContentResponseDto> createContent(@PathVariable Long topicId,
                                                                 @Valid @RequestBody LearningContentCreateRequestDto requestDto) {
        return ApiResponse.success("학습 컨텐츠 생성 성공", learningContentService.createContent(topicId, requestDto));
    }

    @GetMapping("/api/topics/{topicId}/contents")
    public ApiResponse<List<LearningContentResponseDto>> getContentsByTopic(@PathVariable Long topicId) {
        return ApiResponse.success("학습 컨텐츠 목록 조회 성공", learningContentService.getContentByTopic(topicId));
    }

    @GetMapping("/api/contents/{contentsId}")
    public ApiResponse<LearningContentResponseDto> getContent(@PathVariable Long topidId) {
        return ApiResponse.success("조회 성공", learningContentService.getContent(topidId));
    }

    @PatchMapping("/api/contents/{contentId}")
    public ApiResponse<LearningContentResponseDto> updateContent(@PathVariable Long contentId,
                                                                 @Valid @RequestBody LearningContentUpdateRequestDto requestDto) {
        return ApiResponse.success("수정 성공", learningContentService.updateContent(contentId, requestDto));
    }

    @DeleteMapping("/api/contents/{contentId}")
    public ApiResponse<Void> deleteContent(@PathVariable Long contentId) {
        learningContentService.deleteContent(contentId);
        return ApiResponse.success("삭제 성공", null);
    }
}
