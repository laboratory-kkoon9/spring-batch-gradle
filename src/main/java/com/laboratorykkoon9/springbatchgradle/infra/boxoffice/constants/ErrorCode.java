package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.constants;

import com.fasterxml.jackson.annotation.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    INVALID_KEY("320010", "유효하지않은 키값입니다."),
    DATE_NOT_EMPTY_BY_SEARCH_API("320102", "날짜는 필수항목입니다.[parameterName=targetDt,parameterValue=null]");

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }
}
