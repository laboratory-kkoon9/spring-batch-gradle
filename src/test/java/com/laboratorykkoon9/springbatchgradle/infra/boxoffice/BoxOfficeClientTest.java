package com.laboratorykkoon9.springbatchgradle.infra.boxoffice;

import com.fasterxml.jackson.databind.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.web.reactive.function.client.*;

import java.io.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        initializers = {ConfigDataApplicationContextInitializer.class},   // application.yml 파일을 읽어온다.
        classes = {BoxOfficeClient.class} // @value 사용 변수에 할당을 위해 사용함.
)
class BoxOfficeClientTest {
    @Autowired
    private BoxOfficeClient boxOfficeClient;
    private static MockWebServer mockWebServer;
    private ObjectMapper objectMapper;
    private WebClient webClient;

    @Value("${boxoffice.key}")
    private String boxOfficeAccessKey;

    @Value("${boxoffice.base-url}")
    private String boxOfficeUrl;

    @BeforeEach
    void init() throws IOException {
        objectMapper = new ObjectMapper();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        webClient = WebClient
                .builder()
                .baseUrl(boxOfficeUrl)
                .build();
    }

    @Test
    @DisplayName("유효하지 않은 key로 요청을 하면 faultInfo를 리턴한다.")
    void invalid_key_return_error() {
        // given
        String invalidAccessKey = "invalidAccessKey";

        // when
        WebClient.ResponseSpec result = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment("searchDailyBoxOfficeList.json")
                                .queryParam("key", invalidAccessKey)
                                .build()
                )
                .retrieve();
        BoxOfficeFailureDto failureDto = result.bodyToMono(BoxOfficeFailureDto.class);

        // then
        assertAll(
                () -> assertThat(failureDto.getMessage()).isEqualTo("유효하지않은 키값입니다."),
                () -> assertThat(failureDto.getErrorCode).isEqualTo("320010")
        );
    }
}
