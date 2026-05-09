package com.deepstudy.ai.service;

import com.deepstudy.ai.client.GeminiClient;
import com.deepstudy.ai.prompt.QaPromptBuilder;
import com.deepstudy.lessonmessage.dto.LessonMessageResponseDto;
import com.deepstudy.understanding.domain.ConceptUnderstanding;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QaAiService {

    private final GeminiClient geminiClient;
    private final QaPromptBuilder promptBuilder;

    public String answer(String topicName, String learningContent, String userQuestion, List<LessonMessageResponseDto> messages, ConceptUnderstanding understanding) {
        String prompt = promptBuilder.build(topicName, learningContent, userQuestion, messages, understanding);

        return geminiClient.generate(prompt);
    }
}
