package com.deepstudy.studysession.domain;

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
public class StudySession extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StudySessionStatus status;

    @Column(nullable = false)
    private Integer currentStep;

    @Lob
    private String summary;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime lastActivityAt;

    public void updateProgress(Integer currentStep, String summary) {
        this.currentStep = currentStep;
        this.summary = summary;
        this.lastActivityAt = LocalDateTime.now();
    }

    public void complete() {
        this.status = StudySessionStatus.COMPLETED;
        this.lastActivityAt = LocalDateTime.now();
    }

    public void pause() {
        this.status = StudySessionStatus.PAUSED;
        this.lastActivityAt = LocalDateTime.now();
    }

    public void resume() {
        this.status = StudySessionStatus.IN_PROGRESS;
        this.lastActivityAt = LocalDateTime.now();
    }
}
