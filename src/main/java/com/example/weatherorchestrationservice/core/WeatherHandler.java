package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.client.WeatherClient;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import com.example.weatherorchestrationservice.exception.RequestFailedException;
import com.example.weatherorchestrationservice.utilites.UrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherHandler {

    private final UrlGenerator generator;
    private final WeatherClient client;
    private final WeatherCachingService cachingService;

    public WeatherResponse getWeather(double lat, double lon) {
        String url = generator.generateUrl(lat, lon);
        WeatherResponse response = new WeatherResponse();
        CachingServiceResponse cashingResponse = cachingService.checkCache(url);
        if (cashingResponse.isCacheValid()) {
            response.setTemperature(cashingResponse.getTemperature());
        } else {
            ResponseEntity<WeatherFromYrResponse> responseEntity = client.getWeather(url);
            if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                WeatherFromYrResponse yrResponse = responseEntity.getBody();
                response.setTemperature(yrResponse.getProperties().getTimeSeries()[0]
                        .getDataSet().getInstant().getDetails().getTemperature());
                cachingService.saveOrUpdate(url, response.getTemperature());
            }
            throw new RequestFailedException();
        }
        return response;
    }

}
