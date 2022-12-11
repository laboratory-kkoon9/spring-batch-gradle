package com.laboratorykkoon9.springbatchgradle.infra.movie;

import com.fasterxml.jackson.databind.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

import java.io.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        initializers = {ConfigDataApplicationContextInitializer.class},   // application.yml 파일을 읽어온다.
        classes = {MovieClient.class} // @value 사용 변수에 할당을 위해 사용함.
)
class MovieClientTest {
    @Autowired
    private MovieClient movieClient;
    private static MockWebServer mockWebServer;
    private ObjectMapper objectMapper;

    @Value("${movie.key}")
    private String movieAccessKey;

    @BeforeEach
    void init() throws IOException {
        objectMapper = new ObjectMapper();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }
}
