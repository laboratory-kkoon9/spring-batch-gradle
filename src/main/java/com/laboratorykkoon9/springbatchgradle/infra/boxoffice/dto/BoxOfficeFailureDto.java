package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class BoxOfficeFailureDto {
    private BoxOfficeFailureInnerDto faultInfo;

    public BoxOfficeFailureDto(BoxOfficeFailureInnerDto faultInfo) {
        this.faultInfo = faultInfo;
    }
}
