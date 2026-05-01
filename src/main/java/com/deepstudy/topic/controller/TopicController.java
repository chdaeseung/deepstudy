package com.deepstudy.topic.controller;

import com.deepstudy.global.response.ApiResponse;
import com.deepstudy.topic.dto.TopicCreateRequestDto;
import com.deepstudy.topic.dto.TopicResponseDto;
import com.deepstudy.topic.dto.TopicUpdateRequestDto;
import com.deepstudy.topic.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ApiResponse<TopicResponseDto> createTopic(@Valid @RequestBody TopicCreateRequestDto requestDto) {
        return ApiResponse.success("생성 성공", topicService.createTopic(requestDto));
    }

    @GetMapping
    public ApiResponse<List<TopicResponseDto>> getTopics() {
        return ApiResponse.success("목록 조회 성공", topicService.getAllTopics());
    }

    @GetMapping("/{topicId}")
    public ApiResponse<TopicResponseDto> getTopic(@PathVariable Long topicId) {
        return ApiResponse.success("조회 성공", topicService.getTopic(topicId));
    }

    @PatchMapping("/{topicId}")
    public ApiResponse<TopicResponseDto> updateTopic(
            @PathVariable Long topicId,
            @Valid @RequestBody TopicUpdateRequestDto requestDto
            ) {
        return ApiResponse.success("수정 성공", topicService.updateTopic(topicId, requestDto));
    }

    @DeleteMapping("/{topicId}")
    public ApiResponse<Void> deleteTopic(@PathVariable Long topicId) {
        topicService.deleteTopic(topicId);

        return ApiResponse.success("삭제 성공", null);
    }
}
