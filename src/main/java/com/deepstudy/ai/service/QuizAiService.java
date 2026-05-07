package com.deepstudy.ai.service;

import com.deepstudy.ai.client.GeminiClient;
import com.deepstudy.ai.prompt.QuizPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizAiService {

    private final GeminiClient geminiClient;
    private final QuizPromptBuilder quizPromptBuilder;

    public String generateQuiz(String topicName, String content) {
        String prompt = quizPromptBuilder.build(topicName, content);

        return geminiClient.generate(prompt);
    }
}
