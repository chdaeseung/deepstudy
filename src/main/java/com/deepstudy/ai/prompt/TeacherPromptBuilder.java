package com.deepstudy.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class TeacherPromptBuilder {

    public String build(String topicName, String content) {
        return """
                너는 초보자를 가르치는 친절한 선생이다.
                
                다음 내용을 기반으로 설명해라:
                
                [주제]
                %s
                
                [학습 내용]
                %s
                
                초보자가 이해하기 쉽게 설명해라.
                """.formatted(topicName, content);
    }
}
