package com.deepstudy.learningcontent.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.learningcontent.domain.LearningContent;
import com.deepstudy.learningcontent.domain.LearningContentStatus;
import com.deepstudy.learningcontent.dto.LearningContentCreateRequestDto;
import com.deepstudy.learningcontent.dto.LearningContentResponseDto;
import com.deepstudy.learningcontent.dto.LearningContentUpdateRequestDto;
import com.deepstudy.learningcontent.repository.LearningContentRepository;
import com.deepstudy.topic.domain.Topic;
import com.deepstudy.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LearningContentService {

    private final LearningContentRepository learningContentRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public LearningContentResponseDto createContent(Long topicId, LearningContentCreateRequestDto requestDto) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPIC_NOT_FOUND));

        LearningContent content = LearningContent.builder()
                .topic(topic)
                .title(requestDto.title())
                .content(requestDto.content())
                .difficulty(requestDto.difficulty())
                .orderIndex(requestDto.orderIndex())
                .status(LearningContentStatus.DRAFT)
                .version(1)
                .sourceUrl(requestDto.sourceUrl())
                .build();

        return LearningContentResponseDto.from(learningContentRepository.save(content));
    }

    public List<LearningContentResponseDto> getContentByTopic(Long topicId) {
        if(!topicRepository.existsById(topicId)) {
            throw new CustomException(ErrorCode.TOPIC_NOT_FOUND);
        }

        return learningContentRepository.findByTopicIdOrderByOrderIndexAsc(topicId)
                .stream()
                .map(lc -> LearningContentResponseDto.from(lc))
                .toList();
    }

    public LearningContentResponseDto getContent(Long contentId) {
        LearningContent content = learningContentRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_NOT_FOUND));

        return LearningContentResponseDto.from(content);
    }

    @Transactional
    public LearningContentResponseDto updateContent(Long contentId, LearningContentUpdateRequestDto requestDto) {
        LearningContent content = learningContentRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_NOT_FOUND));

        content.update(
                requestDto.title(),
                requestDto.content(),
                requestDto.difficulty(),
                requestDto.orderIndex(),
                requestDto.status(),
                requestDto.sourceUrl()
        );

        return LearningContentResponseDto.from(content);
    }

    @Transactional
    public void deleteContent(Long contentId) {
        LearningContent content = learningContentRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_NOT_FOUND));

        learningContentRepository.delete(content);
    }

}
