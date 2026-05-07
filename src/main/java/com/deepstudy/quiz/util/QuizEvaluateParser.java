package com.deepstudy.quiz.util;

import com.deepstudy.quiz.dto.QuizEvaluateResult;
import org.springframework.stereotype.Component;

@Component
public class QuizEvaluateParser {
    public QuizEvaluateResult parse(String aiResponse) {
        String scoreText = extract(aiResponse, "SCORE:", "FEEDBACK:");
        String feedback = extract(aiResponse, "FEEDBACK:", null);

        Integer score = parseScore(scoreText);

        return new QuizEvaluateResult(score, feedback.trim());
    }

    private String extract(String text, String startMarker, String endMarker) {
        int start = text.indexOf(startMarker);

        if(start == -1) {
            return "";
        }

        start += startMarker.length();

        int end = endMarker == null ? text.length() : text.indexOf(endMarker);

        if(end == -1 || end < start) {
            end = text.length();
        }

        return text.substring(start, end);
    }

    private Integer parseScore(String scoreText) {
        try {
            return Integer.parseInt(scoreText.trim().replace("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
