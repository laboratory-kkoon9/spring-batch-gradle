package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    public String parse(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
