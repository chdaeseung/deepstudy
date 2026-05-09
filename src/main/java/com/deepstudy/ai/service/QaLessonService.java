package com.deepstudy.ai.service;

import com.deepstudy.ai.dto.QaRequestDto;
import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.learningcontent.domain.LearningContent;
import com.deepstudy.learningcontent.repository.LearningContentRepository;
import com.deepstudy.lessonmessage.domain.LessonMessage;
import com.deepstudy.lessonmessage.domain.MessageRole;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.lessonmessage.repository.LessonMessageRepository;
import com.deepstudy.lessonmessage.service.LessonMessageService;
import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.repository.StudySessionRepository;
import com.deepstudy.understanding.domain.ConceptUnderstanding;
import com.deepstudy.understanding.repository.ConceptUnderstandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QaLessonService {

    private static final Long TEMP_USER_ID = 1L;

    private final StudySessionRepository studySessionRepository;
    private final LearningContentRepository learningContentRepository;
    private final LessonMessageRepository lessonMessageRepository;
    private final LessonMessageService lessonMessageService;
    private final ConceptUnderstandingRepository conceptUnderstandingRepository;
    private final QaAiService qaAiService;

    @Transactional
    public LessonMessageResponseDto ask(Long sessionId, QaRequestDto requestDto) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        Long topicId = session.getTopic().getId();

        LearningContent content = learningContentRepository.findByTopicIdAndOrderIndex(topicId, session.getCurrentStep())
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_STEP_NOT_FOUND));

        lessonMessageService.saveMessage(sessionId, MessageRole.USER, requestDto.question());

        List<LessonMessageResponseDto> messages = lessonMessageRepository
                .findByStudySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(message -> LessonMessageResponseDto.from(message))
                .toList();

        ConceptUnderstanding understanding = conceptUnderstandingRepository
                .findByUserIdAndTopicId(TEMP_USER_ID, topicId)
                .orElse(null);

        String aiResponse = qaAiService.answer(
                session.getTopic().getName(),
                content.getContent(),
                requestDto.question(),
                messages,
                understanding
        );

        LessonMessage aiMessage = LessonMessage.builder()
                .studySession(session)
                .role(MessageRole.QA_AI)
                .content(aiResponse)
                .build();

        return LessonMessageResponseDto.from(lessonMessageRepository.save(aiMessage));
    }
}
