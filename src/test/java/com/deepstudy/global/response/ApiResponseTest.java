package com.deepstudy.global.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void success() {
        // given
        String data = "서버 정상 작동";

        // when
        ApiResponse<String> response = ApiResponse.success(data);

        // then
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("요청 성공");
        assertThat(response.getData()).isEqualTo("서버 정상 작동");
    }

    @Test
    void testSuccess() {
        // when
        ApiResponse<String> response = ApiResponse.success("조회 성공", "JWT");

        // then
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("조회 성공");
        assertThat(response.getData()).isEqualTo("JWT");
    }
}