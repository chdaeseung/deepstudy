package com.deepstudy.learningcontent.repository;

import com.deepstudy.learningcontent.domain.LearningContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningContentRepository extends JpaRepository<LearningContent, Long> {
    List<LearningContent> findByTopicIdOrderByOrderIndexAsc(Long topicId);
}
