package com.deepstudy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    TOPIC_NOT_FOUND("TOPIC_NOT_FOUND", "존재하지 않는 주제입니다."),
    DUPLICATE_TOPIC_NAME("DUPLICATE_TOPIC_NAME", "이미 등록된 주제입니다."),
    LEARNING_CONTENT_NOT_FOUND("LEARNING_CONTENT_NOT_FOUND", "존재하지 않는 학습 컨텐츠입니다."),
    INVALID_INPUT("INVALID_INPUT", "잘못된 입력입니다.");

    private final String code;
    private final String message;
}
