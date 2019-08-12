package com.sapient.weatherservice.model;

import lombok.Data;

@Data
public class WeatherInfo {

    private String dt;
    private Main main;
}
