package com.sapient.weatherservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private String date;
    private String forecast;
}
