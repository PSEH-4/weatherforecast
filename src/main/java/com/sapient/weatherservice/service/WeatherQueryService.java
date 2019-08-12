package com.sapient.weatherservice.service;

import com.sapient.weatherservice.model.QueryInfo;
import com.sapient.weatherservice.model.WeatherResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherQueryService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather_url}")
    private String weatherForecastUrl;

    @Value("${appkey}")
    private String appKey;

    public String getWeatherInfo(QueryInfo queryInfo){
        WeatherResponseInfo responseInfo = restTemplate.getForObject(constructUrl(queryInfo), WeatherResponseInfo.class);
        String response = "Use sunscreen lotion";
        if(responseInfo.getMain().getTempMax() > 40){
            response = "Carry umbrella";
        }
        return response;
    }

    private String constructUrl(QueryInfo queryInfo) {
        UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(weatherForecastUrl)
                .queryParam("appid", appKey)
                .queryParam("q", queryInfo.getCity() == null ? "" : queryInfo.getCity())
                .queryParam("units","metric")
                .queryParam("cnt","3");
        return queryBuilder.toUriString();
    }
}
