package com.deepstudy.ai.service;

import com.deepstudy.ai.client.GeminiClient;
import com.deepstudy.ai.prompt.QuizEvaluatePromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizEvaluateAiService {

    private final GeminiClient geminiClient;
    private final QuizEvaluatePromptBuilder promptBuilder;

    public String evaluate(String question, String answerCriteria, String userAnswer) {
        String prompt = promptBuilder.build(question, answerCriteria, userAnswer);

        return geminiClient.generate(prompt);
    }
}
