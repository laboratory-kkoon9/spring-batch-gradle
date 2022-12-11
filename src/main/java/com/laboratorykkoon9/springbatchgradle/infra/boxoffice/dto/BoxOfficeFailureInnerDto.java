package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class BoxOfficeFailureInnerDto {
    private String message;
    private String errorCode;

    public BoxOfficeFailureInnerDto(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
