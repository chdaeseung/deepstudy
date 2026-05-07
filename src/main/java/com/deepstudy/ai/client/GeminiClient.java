package com.deepstudy.ai.client;

import com.deepstudy.ai.config.GeminiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final GeminiConfig config;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generate(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                + config.getModel()
                + ":generateContent?key="
                + config.getApiKey();

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

        return extractText(response);
    }

    private String extractText(Map<String, Object> response) {
        if(response == null) {
            return "";
        }

        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");

        if(candidates == null || candidates.isEmpty()) {
            return "";
        }

        Map<String, Object> firstCandidate = candidates.get(0);

        Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");

        if(content == null) {
            return "";
        }

        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

        if(parts == null || parts.isEmpty()) {
            return "";
        }

        Map<String, Object> firstPart = parts.get(0);

        Object text = firstPart.get("text");

        return text != null ? text.toString() : "";
    }
}
