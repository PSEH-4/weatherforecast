package com.sapient.weatherservice.service;

import com.sapient.weatherservice.constants.Constants;
import com.sapient.weatherservice.model.Weather;
import com.sapient.weatherservice.model.WeatherForecastQueryRequest;
import com.sapient.weatherservice.model.WeatherInfo;
import com.sapient.weatherservice.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherQueryService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${appkey}")
    private String appKey;

    @Value("${weather_forecast_url}")
    private String weatherForecastUrlLink;

    @Value("${defaultCity}")
    private String defaultCity;

    public String getWeatherForecast(WeatherForecastQueryRequest weatherForecastQueryRequest){
        Weather responseInfo = restTemplate.getForObject(constructForecastUrl(weatherForecastQueryRequest), Weather.class);
        StringBuilder sb = new StringBuilder();

        String responseSunscreen = "Use sunscreen lotion";
        String responseUmbrella = "Carry umbrella";

        for(WeatherInfo weatherInfo : responseInfo.getList()){
            sb.append(weatherInfo.getDt());
            sb.append("::");
            if(weatherInfo.getMain().getTempMax() > 40){
                sb.append(responseUmbrella);
            }else{
                sb.append(responseSunscreen);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<WeatherResponse> getWeatherForecastJson(WeatherForecastQueryRequest weatherForecastQueryRequest){
        Weather responseInfo = restTemplate.getForObject(constructForecastUrl(weatherForecastQueryRequest), Weather.class);
        StringBuilder sb = new StringBuilder();

        String responseSunscreen = "Use sunscreen lotion";
        String responseUmbrella = "Carry umbrella";
        List<WeatherResponse> responseList = new ArrayList<WeatherResponse>();
        for(WeatherInfo weatherInfo : responseInfo.getList()){
            WeatherResponse response = new WeatherResponse();
            response.setDate(weatherInfo.getDt());
            if(weatherInfo.getMain().getTempMax() > 40){
                response.setForecast(responseUmbrella);
            }else{
                response.setForecast(responseSunscreen);
            }
            responseList.add(response);
        }
        return responseList;
    }

    private String constructForecastUrl(WeatherForecastQueryRequest weatherForecastQueryRequest) {
        UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(weatherForecastUrlLink)
                .queryParam(Constants.APP_ID, appKey)
                .queryParam(Constants.QUERY_CITY, getCityInfo(weatherForecastQueryRequest))
                .queryParam(Constants.UNITS,Constants.CELSIUS)
                .queryParam(Constants.CNT,Constants.CNTMAX);
        return queryBuilder.toUriString();
    }

    private String getCityInfo(WeatherForecastQueryRequest weatherForecastQueryRequest) {
        return (weatherForecastQueryRequest.getCity() == null ||
                weatherForecastQueryRequest.getCity().isEmpty()) ?
                defaultCity : weatherForecastQueryRequest.getCity();
    }
}
