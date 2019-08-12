package com.sapient.weatherservice.model;

import lombok.Data;

@Data
public class WeatherResponseInfo {

    private String name;
    private MainInfo main;
}
