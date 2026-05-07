package com.deepstudy.quiz.service;

import com.deepstudy.ai.service.QuizEvaluateAiService;
import com.deepstudy.global.exception.CustomException;
import com.deepstudy.global.exception.ErrorCode;
import com.deepstudy.quiz.domain.QuizAnswer;
import com.deepstudy.quiz.domain.QuizQuestion;
import com.deepstudy.quiz.dto.QuizAnswerCreateRequestDto;
import com.deepstudy.quiz.dto.QuizAnswerResponseDto;
import com.deepstudy.quiz.dto.QuizEvaluateResult;
import com.deepstudy.quiz.repository.QuizAnswerRepository;
import com.deepstudy.quiz.repository.QuizQuestionRepository;
import com.deepstudy.quiz.util.QuizEvaluateParser;
import com.deepstudy.understanding.domain.ConceptUnderstanding;
import com.deepstudy.understanding.repository.ConceptUnderstandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizAnswerService {

    private static final Long TEMP_USER_ID = 1L;

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizAnswerRepository quizAnswerRepository;
    private final QuizEvaluateAiService quizEvaluateAiService;
    private final QuizEvaluateParser quizEvaluateParser;
    private final ConceptUnderstandingRepository conceptUnderstandingRepository;

    @Transactional
    public QuizAnswerResponseDto submitAnswer(Long questionId, QuizAnswerCreateRequestDto requestDto) {
        QuizQuestion question = quizQuestionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(ErrorCode.QUIZ_QUESTION_NOT_FOUND));

        String aiResponse = quizEvaluateAiService.evaluate(
                question.getQuestion(),
                question.getAnswerCriteria(),
                requestDto.userAnswer()
        );

        QuizEvaluateResult result = quizEvaluateParser.parse(aiResponse);


        QuizAnswer answer = QuizAnswer.builder()
                .quizQuestion(question)
                .userId(TEMP_USER_ID)
                .userAnswer(requestDto.userAnswer())
                .score(result.score())
                .feedback(result.feedback())
                .build();

        QuizAnswer savedAnswer = quizAnswerRepository.save(answer);

        Long topicId = question.getStudySession().getTopic().getId();

        ConceptUnderstanding understanding = conceptUnderstandingRepository.findByUserIdAndTopicId(TEMP_USER_ID, topicId)
                .orElseGet(() -> ConceptUnderstanding.builder()
                        .userId(TEMP_USER_ID)
                        .topic(question.getStudySession().getTopic())
                        .understandingLevel(0)
                        .summary("")
                        .weakPoints("")
                        .improvedPoints("")
                        .lastQuizScore(0)
                        .build()
                );

        understanding.updateByQuizResult(result.score(), result.feedback());

        conceptUnderstandingRepository.save(understanding);

        return QuizAnswerResponseDto.from(savedAnswer);
    }
}
