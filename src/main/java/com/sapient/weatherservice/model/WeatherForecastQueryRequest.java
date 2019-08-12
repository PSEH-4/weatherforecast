package com.sapient.weatherservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastQueryRequest {

    private String city;
    private String countryCode;

}
