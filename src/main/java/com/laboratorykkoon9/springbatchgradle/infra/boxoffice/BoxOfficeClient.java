package com.laboratorykkoon9.springbatchgradle.infra.boxoffice;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto.BoxOfficeDailyResponseDto;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto.BoxOfficeRequestDto;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util.JsonParser;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util.JsonToMapConverter;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util.QueryStringConverter;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoxOfficeClient {
    private final WebClient webClient = WebClient.builder().build();
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Value("${boxoffice.base-url}")
    private String BASE_URL;
    @Value("${boxoffice.key}")
    private String API_KEY;

    private WebClient.ResponseSpec requestGet(String path, String param) {
        return webClient.get()
                .uri(BASE_URL + path + param)
                .retrieve();
    }

    public BoxOfficeDailyResponseDto dailyBoxOffices(BoxOfficeRequestDto dto) {
        BoxOfficeDailyResponseDto result = null;
        JsonParser<BoxOfficeRequestDto> jsonParser = new JsonParser<>();
        try {
            String queryString = QueryStringConverter.convert(JsonToMapConverter.convert(jsonParser.parse(dto)));
            result = requestGet("searchDailyBoxOfficeList.json", queryString)
                    .bodyToMono(BoxOfficeDailyResponseDto.class)
                    .block();
        } catch (Exception e) {
            log.info("dailyBoxOffices error = {}", e.getLocalizedMessage());
            new IllegalArgumentException(e.getLocalizedMessage());
        }
        return result;
    }
}
