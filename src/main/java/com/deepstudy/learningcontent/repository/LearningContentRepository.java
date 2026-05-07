package com.deepstudy.learningcontent.repository;

import com.deepstudy.learningcontent.domain.LearningContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearningContentRepository extends JpaRepository<LearningContent, Long> {
    List<LearningContent> findByTopicIdOrderByOrderIndexAsc(Long topicId);

    Optional<LearningContent> findByTopicIdAndOrderIndex(Long topicId, Integer orderIndex);
}
