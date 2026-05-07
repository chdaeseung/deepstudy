package com.deepstudy.quiz.repository;

import com.deepstudy.quiz.domain.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    List<QuizQuestion> findByStudySessionId(Long sessionId);
}
