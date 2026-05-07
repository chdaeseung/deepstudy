package com.deepstudy.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class QuizEvaluatePromptBuilder {
    public String build(String question, String answerCriteria, String userAnswer) {
        return """
                너는 학습 내용을 기반으로 낸 문제를 보고 사용자가 낸 답변을 평가하는 튜터다.
                
                아래 문제, 채점 기준, 사용자의 답변을 보고 0 ~ 100 점으로 평가해라.
                사용자가 납득할 수 있는 구체적인 피드백을 작성해라.
                
                [문제]
                %s
                
                [채점 기준]
                %s
                
                [사용자 답변]
                %s
                
                반드시 아래 형식으로만 답변해라.
                
                SCORE:
                FEEDBACK:
                """.formatted(question, answerCriteria, userAnswer);
    }
}
