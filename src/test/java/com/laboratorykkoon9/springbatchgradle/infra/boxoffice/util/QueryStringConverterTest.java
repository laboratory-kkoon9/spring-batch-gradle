package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto.BoxOfficeRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.EMPTY_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueryStringConverterTest {
    @DisplayName("파라미터명과 값을 받아서 쿼리스트링 문자열로 컨버팅해준다.")
    @Test
    void test_1() throws JsonProcessingException {
        // given
        String targetDt = "20230224";
        String key = "key123123123";
        String nationCode = "10";

        BoxOfficeRequestDto dto = BoxOfficeRequestDto.builder()
                .key(key)
                .nation(nationCode)
                .date(targetDt)
                .build();
        QueryStringConverter<BoxOfficeRequestDto> queryStringConverter = new QueryStringConverter<>();

        // when
        String result = queryStringConverter.convert(dto);
        System.out.println(result);
        String expected = "?key=key123123123&targetDt=20230224&repNationCd=10";

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("null인 dto가 파라미터로 오면 NPE를 던진다.")
    @Test
    void test_2() {
        // given
        BoxOfficeRequestDto dto = null;
        QueryStringConverter<BoxOfficeRequestDto> queryStringConverter = new QueryStringConverter<>();

        // then
        assertThatThrownBy(() -> queryStringConverter.convert(dto))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(EMPTY_ERROR_MESSAGE);
    }

    @DisplayName("빈 Dto가 파라미터로 오면 빈 스트링을 던진다.")
    @Test
    void test_3() throws JsonProcessingException {
        // given
        BoxOfficeRequestDto dto = BoxOfficeRequestDto.builder()
                .build();
        QueryStringConverter<BoxOfficeRequestDto> queryStringConverter = new QueryStringConverter<>();

        // when
        String result = queryStringConverter.convert(dto);

        // then
        assertThat(result).isEmpty();
    }

    @DisplayName("Dto 내의 파라미터가 null이면 제외한 쿼리스트링 결과를 리턴한다.")
    @Test
    void test_4() throws JsonProcessingException {
        // given
        String targetDt = "20230224";
        String key = "key123123123";
        String nationCode = "10";
        String row = null;

        BoxOfficeRequestDto dto = BoxOfficeRequestDto.builder()
                .key(key)
                .nation(nationCode)
                .row(row)
                .date(targetDt)
                .build();
        QueryStringConverter<BoxOfficeRequestDto> queryStringConverter = new QueryStringConverter<>();

        // when
        String result = queryStringConverter.convert(dto);

        // then
        assertThat(result).doesNotContain("row");
    }
}
