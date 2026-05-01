package com.deepstudy.studysession.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.domain.StudySessionStatus;
import com.deepstudy.studysession.dto.StudySessionResponseDto;
import com.deepstudy.studysession.repository.StudySessionRepository;
import com.deepstudy.topic.domain.Topic;
import com.deepstudy.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudySessionService {

    private static final Long Temp_User_ID = 1L;

    private final StudySessionRepository studySessionRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public StudySessionResponseDto createSession(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPIC_NOT_FOUND));

        LocalDateTime now = LocalDateTime.now();

        StudySession session = StudySession.builder()
                .userId(Temp_User_ID)
                .topic(topic)
                .status(StudySessionStatus.IN_PROGRESS)
                .currentStep(1)
                .summary("")
                .startedAt(now)
                .lastActivityAt(now)
                .build();

        return StudySessionResponseDto.from(studySessionRepository.save(session));
    }

    public StudySessionResponseDto getSession(Long sessionId) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        return StudySessionResponseDto.from(session);
    }

    public List<StudySessionResponseDto> getMySessions() {
        return studySessionRepository.findByUserId(Temp_User_ID)
                .stream()
                .map(session -> StudySessionResponseDto.from(session))
                .toList();
    }

    @Transactional
    public StudySessionResponseDto completeSession(Long sessionId) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        session.complete();

        return StudySessionResponseDto.from(session);
    }
}
