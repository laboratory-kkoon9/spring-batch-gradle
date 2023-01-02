package com.laboratorykkoon9.springbatchgradle.infra.boxoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoxOfficeRequestDto {
    @JsonProperty("key")
    private String key;
    @JsonProperty("targetDt")
    private String date;
    @JsonProperty("itemPerPage")
    private String row;
    @JsonProperty("multiMovieYn")
    private String isMulti;
    @JsonProperty("repNationCd")
    private String nation;
    @JsonProperty("wideAreaCd")
    private String area;

    @Builder
    public BoxOfficeRequestDto(String key, String date, String row, String isMulti, String nation, String area) {
        this.key = key;
        this.date = date;
        this.row = row;
        this.isMulti = isMulti;
        this.nation = nation;
        this.area = area;
    }
}
