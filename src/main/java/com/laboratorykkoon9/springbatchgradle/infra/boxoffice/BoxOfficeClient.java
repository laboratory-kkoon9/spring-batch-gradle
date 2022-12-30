package com.laboratorykkoon9.springbatchgradle.infra.boxoffice;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.reactive.function.*;
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

    public WebClient.ResponseSpec requestGet(String path, String param) {
        return webClient.get()
                .uri(BASE_URL + path + param)
                .retrieve();
    }
}
