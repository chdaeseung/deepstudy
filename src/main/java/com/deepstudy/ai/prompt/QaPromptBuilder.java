package com.deepstudy.ai.prompt;

import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.understanding.domain.ConceptUnderstanding;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QaPromptBuilder {

    public String build(String topicName, String learningContent, String uesrQuestion, List<LessonMessageResponseDto> messages, ConceptUnderstanding understanding) {
        String messageHistory = messages.stream()
                .map(m -> m.role() + ": " + m.content())
                .reduce("", (a, b) -> a + "\n" + b);

        String understandingInfo = understanding == null ? "이해도 정보가 없습니다." :
                """
                이해도: %d
                요약: %s
                약한 부분: %s
                개선된 부분: %s
                """.formatted(
                        understanding.getUnderstandingLevel(),
                        understanding.getSummary(),
                        understanding.getWeakPoints(),
                        understanding.getImprovedPoints()
                );

        return """
            너는 사용자의 학습을 돕는 Q&A 튜터다.
            
            반드시 아래 학습 컨텐츠와 이전 대화 맥락을 기반으로 답변해라.
            사용자의 이해도에 있어서 약한 부분이 있으면 보완해서 설명해라.
            
            [주제]
            %s
            
            [학습 컨텐츠]
            %s
            
            [사용자 이해도 정보]
            %s
            
            [이전 대화]
            %s
            
            [사용자 질문]
            %s
            
            답변은 친절하게 하되, 너무 길지 않게 작성해라.
            """.formatted(
                topicName,
                learningContent,
                understandingInfo,
                messageHistory,
                uesrQuestion
        );
    }
}
