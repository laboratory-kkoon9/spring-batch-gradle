package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.EMPTY_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueryStringConverterTest {
    @DisplayName("파라미터명과 값을 받아서 쿼리스트링 문자열로 컨버팅해준다.")
    @Test
    void test_1() {
        // given
        Map<String, Object> messages = new LinkedHashMap<>(); // Test를 위해 LinkedHashMap으로 선언하였습니다.
        messages.put("key", "1234");
        messages.put("targetDt", "20221230");

        // when
        String result = QueryStringConverter.convert(messages);
        String expected = "?key=1234&targetDt=20221230";

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("null인 Map이 파라미터로 오면 NPE를 던진다.")
    @Test
    void test_2() {
        // given
        Map<String, Object> messages = null;

        // then
        assertThatThrownBy(() -> QueryStringConverter.convert(messages))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(EMPTY_ERROR_MESSAGE);
    }

    @DisplayName("빈 Map이 파라미터로 오면 빈 스트링을 던진다.")
    @Test
    void test_3() {
        // given
        Map<String, Object> messages = new HashMap<>();

        // when
        String result = QueryStringConverter.convert(messages);
        String expected = "";

        // then
        assertThat(result).isEqualTo(expected);
    }

}
