package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Getter
public class BoxOfficeDailyDto {
    @JsonProperty(value = "boxofficeType")
    private String type;

    @JsonProperty(value = "showRange")
    private String range;

    @JsonProperty(value = "dailyBoxOfficeList")
    private List<BoxOfficeDailyItemDto> boxOfficeDailyItems;

}
