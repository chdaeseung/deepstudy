package com.deepstudy.ai.service;

import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.learningcontent.domain.LearningContent;
import com.deepstudy.learningcontent.repository.LearningContentRepository;
import com.deepstudy.lessonmessage.domain.LessonMessage;
import com.deepstudy.lessonmessage.domain.MessageRole;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.lessonmessage.repository.LessonMessageRepository;
import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.repository.StudySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherLessonService {

    private final StudySessionRepository studySessionRepository;
    private final LearningContentRepository learningContentRepository;
    private final LessonMessageRepository lessonMessageRepository;
    private final TeacherAiService teacherAiService;

    @Transactional
    public LessonMessageResponseDto generateTeacherMessage(Long sessionId) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        Long topicId = session.getTopic().getId();
        Integer currentStep = session.getCurrentStep();

        LearningContent content = learningContentRepository.findByTopicIdAndOrderIndex(topicId, currentStep)
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_STEP_NOT_FOUND));

        String aiResponse = teacherAiService.askTeacher(
                session.getTopic().getName(),
                content.getContent()
        );

        LessonMessage message = LessonMessage.builder()
                .studySession(session)
                .role(MessageRole.TEACHER_AI)
                .content(aiResponse)
                .build();

        return LessonMessageResponseDto.from(lessonMessageRepository.save(message));
    }
}
