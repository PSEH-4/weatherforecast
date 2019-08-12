package com.sapient.weatherservice.controller;

import com.sapient.weatherservice.model.WeatherForecastQueryRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weatherf")
public class PingController {

    @RequestMapping(path = "/test")
    public String getTest(){
        return "hi";
    }
}
