package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.util;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static com.laboratorykkoon9.springbatchgradle.global.constant.CommonConstants.NULL_MESSAGE;

public class QueryStringConverter {
    private QueryStringConverter(){

    }

    public static String convert(Map<String, Object> messages) {
        if (messages == null) {
            throw new NullPointerException(NULL_MESSAGE);
        }
        UriComponentsBuilder queryString = UriComponentsBuilder.newInstance();
        for (String key : messages.keySet()) {
            queryString.queryParam(key, messages.get(key).toString());
        }

        return queryString.toUriString();
    }
}
