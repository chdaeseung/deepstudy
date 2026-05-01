package com.deepstudy.topic.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.topic.domain.Topic;
import com.deepstudy.topic.dto.TopicCreateRequestDto;
import com.deepstudy.topic.dto.TopicResponseDto;
import com.deepstudy.topic.dto.TopicUpdateRequestDto;
import com.deepstudy.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicResponseDto createTopic(TopicCreateRequestDto requestDto) {
        topicRepository.findByName(requestDto.name())
                .ifPresent(t -> {
                    throw new CustomException(ErrorCode.DUPLICATE_TOPIC_NAME);
                });

        Topic topic = Topic.builder()
                .name(requestDto.name())
                .description(requestDto.description())
                .build();

        return TopicResponseDto.from(topicRepository.save(topic));
    }

    public List<TopicResponseDto> getAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(topic -> TopicResponseDto.from(topic))
                .toList();
    }

    public TopicResponseDto getTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPIC_NOT_FOUND));

        return TopicResponseDto.from(topic);
    }

    @Transactional
    public TopicResponseDto updateTopic(Long topicId, TopicUpdateRequestDto requestDto) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPIC_NOT_FOUND));

        topicRepository.findByName(requestDto.name())
                .filter(t -> !t.getId().equals(topicId))
                .ifPresent(t -> {
                    throw new CustomException(ErrorCode.DUPLICATE_TOPIC_NAME);
                });

        topic.update(requestDto.name(), requestDto.description());

        return TopicResponseDto.from(topic);
    }

    public void deleteTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPIC_NOT_FOUND));

        topicRepository.delete(topic);
    }
}
