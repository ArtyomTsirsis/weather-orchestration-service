package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.client.WeatherClient;
import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import com.example.weatherorchestrationservice.utilites.UrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherHandler {

    private final UrlGenerator generator;
    private final WeatherClient client;

    public WeatherResponse getWeather(double lat, double lon) {
        ResponseEntity<WeatherFromYrResponse> responseEntity = client.getWeather(generator.generateUrl(lat, lon));
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            WeatherFromYrResponse yrResponse = responseEntity.getBody();
            WeatherResponse response = new WeatherResponse();
            response.setTemperature(yrResponse.getProperties().getTimeSeries()[0]
                    .getDataSet().getInstant().getDetails().getTemperature());
            return response;
        }
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setTemperature(500);
        return weatherResponse;
    }

}
