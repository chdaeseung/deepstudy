package com.deepstudy.quiz.util;

import com.deepstudy.quiz.dto.QuizParseResult;
import org.springframework.stereotype.Component;

@Component
public class QuizParser {
    public QuizParseResult parse(String aiResponse) {
        String question = extract(aiResponse, "QUESTION:", "ANSWER_CRITERIA");
        String answerCriteria = extract(aiResponse, "ANSWER_CRITERIA:", "EXPLANATION");
        String explanation = extract(aiResponse, "EXPLANATION:", null);

        return new QuizParseResult(
                question.trim(),
                answerCriteria.trim(),
                explanation.trim()
        );
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
}
