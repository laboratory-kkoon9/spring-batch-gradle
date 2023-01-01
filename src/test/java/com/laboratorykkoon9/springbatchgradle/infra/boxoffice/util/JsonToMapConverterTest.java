package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Map;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.EMPTY_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class JsonToMapConverterTest {
    @DisplayName("json 형태의 string을 Map으로 변환한다.")
    @Test
    void test_1() throws JsonProcessingException {
        // given
        String json = "{\"name\":\"kkoon9\", \"age\":\"27\"}";

        // when
        Map<String, Object> result = JsonToMapConverter.convert(json);

        // then
        assertAll(
                () -> assertThat(result.get("name")).isEqualTo("kkoon9"),
                () -> assertThat(result.get("age")).isEqualTo("27")
        );
    }

    @DisplayName("json 형태의 string을 Map으로 변환한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void test_2(String json) {
        // then
        assertThatThrownBy(() -> JsonToMapConverter.convert(json))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(EMPTY_ERROR_MESSAGE);
    }
}
