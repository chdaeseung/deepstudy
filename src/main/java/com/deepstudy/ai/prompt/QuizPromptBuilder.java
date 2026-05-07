package com.deepstudy.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class QuizPromptBuilder {
    public String build(String topicName, String content) {

        return """
                너는 학습 평가 전문가다.
                
                다음 주어지는 학습 내용을 기반으로
                초보자용 주관식 문제를 1개 만들어내라.
                
                [주제]
                %s
                
                [학습 내용]
                %s
                
                아래 형식으로 답변해라.
                
                QUESTION:
                ANSWER_CRITERIA:
                EXPLANATION:
                """.formatted(topicName, content);
    }
}
