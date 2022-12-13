package com.laboratorykkoon9.springbatchgradle.infra.boxoffice;

import antlr.collections.*;
import com.fasterxml.jackson.databind.*;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.constants.*;
import com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.web.reactive.function.client.*;

import java.io.*;

import static com.laboratorykkoon9.springbatchgradle.infra.boxoffice.constants.BoxOfficeConstants.DAILY_SEARCH_REST_URL;
import static com.laboratorykkoon9.springbatchgradle.infra.boxoffice.constants.BoxOfficeConstants.KEY_VALUE;
import static com.laboratorykkoon9.springbatchgradle.infra.boxoffice.constants.BoxOfficeConstants.TARGET_DATE;
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
    @DisplayName("유효하지 않은 key로 요청을 하면 ErrorCode [320010]를 리턴한다.")
    void invalid_key_return_error() {
        // given
        String invalidAccessKey = "invalidAccessKey";

        // when
        WebClient.ResponseSpec result = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment(DAILY_SEARCH_REST_URL)
                                .queryParam(KEY_VALUE, invalidAccessKey)
                                .build()
                )
                .retrieve();

        BoxOfficeFailureDto failureDto = result
                .bodyToMono(BoxOfficeFailureDto.class)
                .block();

        // then
        assertAll(
                () -> assertThat(failureDto.getFaultInfo().getMessage()).isEqualTo(ErrorCode.INVALID_KEY.getMessage()),
                () -> assertThat(failureDto.getFaultInfo().getErrorCode()).isEqualTo(ErrorCode.INVALID_KEY.getCode())
        );
    }

    @Test
    @DisplayName("필수 파라미터가 없으면 ErrorCode [320102]를 리턴한다.")
    void invalid_key_return_error2() {
        // when
        WebClient.ResponseSpec result = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment(DAILY_SEARCH_REST_URL)
                                .queryParam(KEY_VALUE, boxOfficeAccessKey)
                                .build()
                )
                .retrieve();

        BoxOfficeFailureDto failureDto = result
                .bodyToMono(BoxOfficeFailureDto.class)
                .block();

        // then
        assertAll(
                () -> assertThat(failureDto.getFaultInfo().getMessage()).isEqualTo(ErrorCode.DATE_NOT_EMPTY_BY_SEARCH_API.getMessage()),
                () -> assertThat(failureDto.getFaultInfo().getErrorCode()).isEqualTo(ErrorCode.DATE_NOT_EMPTY_BY_SEARCH_API.getCode())
        );
    }

    @Test
    @DisplayName("일별 박스오피스 API를 호출하면 '일별 박스오피스' 값을 가지는 type을 리턴한다.")
    void get_daily_search() {
        // given
        String targetDate = "20221213";

        // when
        WebClient.ResponseSpec result = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment(DAILY_SEARCH_REST_URL)
                                .queryParam(KEY_VALUE, boxOfficeAccessKey)
                                .queryParam(TARGET_DATE, targetDate)
                                .build()
                )
                .retrieve();

        BoxOfficeDailyResponseDto responseDto = result
                .bodyToMono(BoxOfficeDailyResponseDto.class)
                .block();

        // then
        assertAll(
                () -> assertThat(responseDto.getBoxOfficeDailyDto().getType()).isEqualTo("일별 박스오피스"),
                () -> assertThat(responseDto.getBoxOfficeDailyDto().getRange()).isEqualTo(targetDate + "~" + targetDate)
        );
    }
}
