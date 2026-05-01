package com.deepstudy.lessonmessage.repository;

import com.deepstudy.lessonmessage.domain.LessonMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonMessageRepository extends JpaRepository<LessonMessage, Long> {
    List<LessonMessage> findByStudySessionIdOrderByIdAsc(Long sessionId);
}
