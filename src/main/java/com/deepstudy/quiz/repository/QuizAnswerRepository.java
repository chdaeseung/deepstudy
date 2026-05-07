package com.deepstudy.quiz.repository;

import com.deepstudy.quiz.domain.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    List<QuizAnswer> findByQuizQuestionId(Long questionId);

    List<QuizAnswer> findByUserId(Long userId);
}
