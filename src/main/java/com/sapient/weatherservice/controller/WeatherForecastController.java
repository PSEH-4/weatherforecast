package com.sapient.weatherservice.controller;

import com.sapient.weatherservice.model.WeatherForecastQueryRequest;
import com.sapient.weatherservice.service.WeatherQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

    @Autowired
    WeatherQueryService weatherQueryService;

    @PostMapping("/forecastRaw")
    public ResponseEntity getWeatherForecast(@RequestBody WeatherForecastQueryRequest weatherForecastQueryRequest) {
        try {
            return new ResponseEntity(weatherQueryService.getWeatherForecast(weatherForecastQueryRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/forecast")
    public ResponseEntity getWeatherForecastJson(@RequestBody WeatherForecastQueryRequest weatherForecastQueryRequest) {
        try {
            return new ResponseEntity(weatherQueryService.getWeatherForecastJson(weatherForecastQueryRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
