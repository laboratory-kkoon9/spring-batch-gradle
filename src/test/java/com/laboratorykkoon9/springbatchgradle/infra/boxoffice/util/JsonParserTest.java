package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto.BoxOfficeRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class JsonParserTest {
    JsonParser<BoxOfficeRequestDto> jsonParser;

    @DisplayName("전달 받은 DTO의 JsonProperty 어노테이션 내 value값으로 치환된다.")
    @Test
    void test_1() throws JsonProcessingException {
        // given
        BoxOfficeRequestDto dto = BoxOfficeRequestDto.builder()
                .key("Test_key")
                .date("20230101")
                .row("100")
                .nation("K")
                .isMulti("N")
                .area("101010")
                .build();

        // when
        jsonParser = new JsonParser<>();
        String result = jsonParser.parse(dto);

        // then
        assertAll(
                () -> assertThat(result.contains("targetDt")).isTrue(),
                () -> assertThat(result.contains("itemPerPage")).isTrue(),
                () -> assertThat(result.contains("multiMovieYn")).isTrue(),
                () -> assertThat(result.contains("repNationCd")).isTrue(),
                () -> assertThat(result.contains("wideAreaCd")).isTrue()
        );
    }
}
