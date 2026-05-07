package com.deepstudy.quiz.service;

import com.deepstudy.ai.service.QuizAiService;
import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.learningcontent.domain.LearningContent;
import com.deepstudy.learningcontent.repository.LearningContentRepository;
import com.deepstudy.quiz.domain.QuizQuestion;
import com.deepstudy.quiz.dto.QuizParseResult;
import com.deepstudy.quiz.dto.QuizQuestionResponseDto;
import com.deepstudy.quiz.repository.QuizQuestionRepository;
import com.deepstudy.quiz.util.QuizParser;
import com.deepstudy.studysession.domain.StudySession;
import com.deepstudy.studysession.repository.StudySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizQuestionService {

    private final StudySessionRepository studySessionRepository;
    private final LearningContentRepository learningContentRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizAiService quizAiService;
    private final QuizParser quizParser;

    @Transactional
    public QuizQuestionResponseDto generateQuiz(Long sessionId) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_SESSION_NOT_FOUND));

        Long topicId = session.getTopic().getId();
        Integer currentStep = session.getCurrentStep();

        LearningContent content = learningContentRepository.findByTopicIdAndOrderIndex(topicId, currentStep)
                .orElseThrow(() -> new CustomException(ErrorCode.LEARNING_CONTENT_STEP_NOT_FOUND));

        String aiResponse = quizAiService.generateQuiz(session.getTopic().getName(), content.getContent());

        QuizParseResult result = quizParser.parse(aiResponse);

        QuizQuestion quizQuestion = QuizQuestion.builder()
                .studySession(session)
                .question(result.question())
                .answerCriteria(result.answerCriteria())
                .explanation(result.explanation())
                .build();

        return QuizQuestionResponseDto.from(quizQuestionRepository.save(quizQuestion));
    }
}
