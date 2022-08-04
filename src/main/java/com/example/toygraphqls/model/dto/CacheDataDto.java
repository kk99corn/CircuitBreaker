package com.example.toygraphqls.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
public class CacheDataDto {

    private String key;
    private String value;
}
