package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
public class BoxOfficeDailyResponseDto {
    @JsonProperty(value = "boxOfficeResult")
    private BoxOfficeDailyDto boxOfficeDailyDto;

}
