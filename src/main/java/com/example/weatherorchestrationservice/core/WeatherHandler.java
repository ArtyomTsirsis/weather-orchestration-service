package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.client.WeatherClient;
import com.example.weatherorchestrationservice.domain.*;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import com.example.weatherorchestrationservice.exception.MappingFailedException;
import com.example.weatherorchestrationservice.utilites.UrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherHandler {

    private final UrlGenerator generator;
    private final WeatherClient client;
    private final WeatherCachingService cachingService;

    public WeatherResponse getWeather(double lat, double lon) {
        String url = generator.generateUrl(lat, lon);
        CachingServiceResponse cashingResponse = cachingService.checkCache(url);
        if (cashingResponse.isCacheValid()) {
            return new WeatherResponse(cashingResponse.getTemperature());
        }
        ResponseEntity<WeatherFromYrResponse> responseEntity = client.getWeather(url);
        double temperature = getTemperatureFromYrResponse(responseEntity.getBody());
        cachingService.saveOrUpdate(url, temperature);
        return new WeatherResponse(temperature);
    }

    private double getTemperatureFromYrResponse(WeatherFromYrResponse response) {
        return Optional.ofNullable(response)
                .map(WeatherFromYrResponse::getProperties)
                .map(Properties::getTimeSeries)
                .filter(t -> t.length != 0)
                .map(t -> t[0])
                .map(TimeSeries::getDataSet)
                .map(DataSet::getInstant)
                .map(Instant::getDetails)
                .map(Details::getTemperature)
                .orElseThrow(() -> new MappingFailedException("Mapping from Yr ResponseEntity failed"));
    }

}
