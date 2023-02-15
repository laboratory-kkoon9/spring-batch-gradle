package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.EMPTY_ERROR_MESSAGE;

public class QueryStringConverter<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    public String convert(T object) throws JsonProcessingException {
        Map<String, Object> messages = parse(object);
        if (messages == null) {
            throw new NullPointerException(EMPTY_ERROR_MESSAGE);
        }
        UriComponentsBuilder queryString = UriComponentsBuilder.newInstance();
        for (String key : messages.keySet()) {
            if(messages.get(key) == null) {
                continue;
            }
            queryString.queryParam(key, messages.get(key).toString());
        }

        return queryString.toUriString();
    }

    private Map<String, Object> parse(T object) throws JsonProcessingException {
        String json = mapper.writeValueAsString(object);
        if(ObjectUtils.isEmpty(json)) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
        return mapper.readValue(json, Map.class);
    }
}
