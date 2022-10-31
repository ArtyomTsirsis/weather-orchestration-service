package com.example.weatherorchestrationservice.client;

import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final HttpClient httpClient;

    public ResponseEntity<WeatherFromYrResponse> getWeather(String url) {
        String sitename = "https://github.com/ArtyomTsirsis";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", sitename);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return httpClient.exchange(url, HttpMethod.GET, entity, WeatherFromYrResponse.class);
    }

}


