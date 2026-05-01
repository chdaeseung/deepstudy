package com.deepstudy.lessonmessage.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.lessonmessage.domain.LessonMessage;
import com.deepstudy.lessonmessage.domain.MessageRole;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.lessonmessage.repository.LessonMessageRepository;
import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.repository.StudySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonMessageService {

    private final LessonMessageRepository messageRepository;
    private final StudySessionRepository sessionRepository;

    public LessonMessageResponseDto saveMessage(Long sessionId, MessageRole role, String content) {
        StudySession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        LessonMessage message = LessonMessage.builder()
                .studySession(session)
                .role(role)
                .content(content)
                .build();

        return LessonMessageResponseDto.from(messageRepository.save(message));
    }

    public List<LessonMessageResponseDto> getMessages(Long sessionId) {
        return messageRepository.findByStudySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(message -> LessonMessageResponseDto.from(message))
                .toList();
    }
}
