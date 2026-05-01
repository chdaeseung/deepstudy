package com.deepstudy.learningcontent.domain;

import com.deepstudy.global.domain.BaseEntity;
import com.deepstudy.topic.domain.Topic;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LearningContent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Difficulty difficulty;

    @Column(nullable = false)
    private Integer orderIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LearningContentStatus status;

    @Column(nullable = false)
    private Integer version;

    @Column(length = 1000)
    private String sourceUrl;

    public void update(
            String title,
            String content,
            Difficulty difficulty,
            Integer orderIndex,
            LearningContentStatus status,
            String sourceUrl
    ) {
        this.title = title;
        this.content = content;
        this.difficulty = difficulty;
        this.orderIndex = orderIndex;
        this.status = status;
        this.sourceUrl = sourceUrl;
    }
}
