package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;

import java.util.Map;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.EMPTY_ERROR_MESSAGE;

public class JsonToMapConverter {
    private static final ObjectMapper mapper = new ObjectMapper();
    private JsonToMapConverter() {}

    public static Map<String, Object> convert(String json) throws JsonProcessingException {
        if(ObjectUtils.isEmpty(json)) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
        return mapper.readValue(json, Map.class);
    }
}
