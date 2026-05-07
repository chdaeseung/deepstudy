package com.deepstudy.understanding.domain;

import com.deepstudy.global.domain.BaseEntity;
import com.deepstudy.topic.domain.Topic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ConceptUnderstanding extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false)
    private Integer understandingLevel;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String summary;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String weakPoints;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String improvedPoints;

    private Integer lastQuizScore;

    private LocalDateTime lastStudiedAt;

    public void updateByQuizResult(Integer score, String feedback) {
        this.understandingLevel = score;
        this.lastQuizScore = score;
        this.summary = feedback;
        this.weakPoints = feedback;
        this.lastStudiedAt = LocalDateTime.now();
    }
}
