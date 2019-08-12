package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Weather {

    private City city;

    @JsonProperty("list")
    private List<WeatherInfo> list;
}
