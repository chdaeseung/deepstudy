package com.deepstudy.understanding.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.understanding.domain.ConceptUnderstanding;
import com.deepstudy.understanding.dto.ConceptUnderstandingResponseDto;
import com.deepstudy.understanding.repository.ConceptUnderstandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConceptUnderstandingService {

    private static final Long TEMP_USER_ID = 1L;

    private final ConceptUnderstandingRepository conceptUnderstandingRepository;

    public List<ConceptUnderstandingResponseDto> getMyUnderstandings() {
        return conceptUnderstandingRepository.findByUserId(TEMP_USER_ID).stream()
                .map(understanding -> ConceptUnderstandingResponseDto.from(understanding))
                .toList();
    }

    public ConceptUnderstandingResponseDto getMyUnderStanding(Long topicId) {
        return conceptUnderstandingRepository.findByUserIdAndTopicId(TEMP_USER_ID, topicId)
                .map(understanding -> ConceptUnderstandingResponseDto.from(understanding))
                .orElseThrow(() -> new CustomException(ErrorCode.CONCEPT_UNDERSTANDING_NOT_FOUND));
    }
}
