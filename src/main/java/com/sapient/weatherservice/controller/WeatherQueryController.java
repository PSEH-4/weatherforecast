package com.sapient.weatherservice.controller;

import com.sapient.weatherservice.model.QueryInfo;
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
public class WeatherQueryController {

    @Autowired
    WeatherQueryService weatherQueryService;

    @PostMapping
    public ResponseEntity getWeatherInfo(@RequestBody QueryInfo queryInfo) {
        try {
            return new ResponseEntity(weatherQueryService.getWeatherInfo(queryInfo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
