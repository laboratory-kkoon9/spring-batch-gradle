package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T> {
    public String parse(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
