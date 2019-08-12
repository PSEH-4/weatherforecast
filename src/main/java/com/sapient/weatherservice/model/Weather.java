package com.sapient.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Weather {

    private City city;

    @JsonProperty("list")
    private List<WeatherInfo> list;


}
