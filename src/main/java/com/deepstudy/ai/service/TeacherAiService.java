package com.deepstudy.ai.service;

import com.deepstudy.ai.client.GeminiClient;
import com.deepstudy.ai.prompt.TeacherPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherAiService {

    private final GeminiClient geminiClient;
    private final TeacherPromptBuilder promptBuilder;

    public String askTeacher(String topicName, String content) {
        String prompt = promptBuilder.build(topicName, content);

        return geminiClient.generate(prompt);
    }
}
