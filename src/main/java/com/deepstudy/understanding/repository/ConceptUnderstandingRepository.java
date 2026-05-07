package com.deepstudy.understanding.repository;

import com.deepstudy.understanding.domain.ConceptUnderstanding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConceptUnderstandingRepository extends JpaRepository<ConceptUnderstanding, Long> {
    Optional<ConceptUnderstanding> findByUserIdAndTopicId(Long userId, Long topicId);

    List<ConceptUnderstanding> findByUserId(Long userId);
}
