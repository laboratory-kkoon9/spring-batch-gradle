package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.format.annotation.*;

@Getter
public class BoxOfficeDailyItemDto {
    @JsonProperty(value = "rnum")
    private String sequence;

    @JsonProperty(value = "rank")
    private String rank;

    @JsonProperty(value = "rankInten")
    private String rankIncrement;

    @JsonProperty(value = "rankOldAndNew")
    private String rankType;

    @JsonProperty(value = "movieCd")
    private String movieCode;

    @JsonProperty(value = "movieNm")
    private String movieName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty(value = "openDt")
    private String movieOpenedAt;

    @JsonProperty(value = "salesAmt")
    private String salesDailyAmount;

    @JsonProperty(value = "salesShare")
    private String salesProportion;

    @JsonProperty(value = "salesInten")
    private String salesIncrement;

    @JsonProperty(value = "salesChange")
    private String salesChangeProportion;

    @JsonProperty(value = "salesAcc")
    private String salesAmount;

    @JsonProperty(value = "audiCnt")
    private String attendanceCount;

    @JsonProperty(value = "audiInten")
    private String attendanceIncrement;

    @JsonProperty(value = "audiChange")
    private String attendanceProportion;

    @JsonProperty(value = "audiAcc")
    private String attendanceAmount;

    @JsonProperty(value = "scrnCnt")
    private String screenCount;

    @JsonProperty(value = "showCnt")
    private String showCount;
}
